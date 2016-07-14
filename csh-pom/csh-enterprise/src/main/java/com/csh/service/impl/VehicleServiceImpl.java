package com.csh.service.impl; 

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.beans.Setting;
import com.csh.dao.EndUserDao;
import com.csh.dao.VehicleDao;
import com.csh.entity.EndUser;
import com.csh.entity.Vehicle;
import com.csh.entity.commonenum.CommonEnum.OilType;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.json.response.VehicleDailyReport;
import com.csh.service.DeviceInfoService;
import com.csh.service.TenantAccountService;
import com.csh.service.VehicleOilService;
import com.csh.service.VehicleService;
import com.csh.utils.ApiUtils;
import com.csh.utils.DateTimeUtils;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.SettingUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("vehicleServiceImpl")
public class VehicleServiceImpl extends BaseServiceImpl<Vehicle,Long> implements VehicleService {

      @Resource(name="vehicleDaoImpl")
      VehicleDao vehicleDao;
      @Resource(name="endUserDaoImpl")
      EndUserDao endUserDao;
      @Resource(name = "tenantAccountServiceImpl")
      TenantAccountService tenantAccountService;
      @Resource (name = "vehicleOilServiceImpl")
      private VehicleOilService vehicleOilService;
      @Resource(name = "deviceInfoServiceImpl")
      private DeviceInfoService deviceInfoService;
      private Setting setting = SettingUtils.get();
      @Resource
      public void setBaseDao(VehicleDao vehicleDao) {
         super.setBaseDao(vehicleDao);
  }

      @Override
      public List<Map<String, Object>> findVehicleUnderUser (Long userId)
      {
        EndUser user = endUserDao.find (userId);
        
        List<Filter> filters = new ArrayList<Filter>();
        
        Filter userFilter = new Filter ("endUser", Operator.eq, user);
        filters.add (userFilter);
        List<Vehicle> vehicleList=vehicleDao.findList (null, null, filters, null);
        String[] propertys = {"id", "plate","dashboardMileage","vehicleFullBrand"};
        return FieldFilterUtils.filterCollectionMap(propertys, vehicleList);
        
      }

      @Override
      public List<Map<String, Object>> findVehicleUserInfoUnderTenant (String endUserFilter)
      {
        List<Vehicle> vehicleList = new ArrayList<Vehicle> ();
        if (endUserFilter != null){
          IKAnalyzer analyzer = new IKAnalyzer ();
          analyzer.setMaxWordLength (true);
          BooleanQuery query = new BooleanQuery ();
    
          QueryParser nameParser = new QueryParser (Version.LUCENE_35,
              "endUser.userName", analyzer);
          QueryParser plateParser = new QueryParser (Version.LUCENE_35, "plate",
              analyzer);
          QueryParser mobileNumParser = new QueryParser (Version.LUCENE_35,
              "endUser.mobileNum", analyzer);
          Query plateQuery = null;
          Query userNameQuery = null;
          Query mobileNumQuery = null;
    
          String text = QueryParser.escape (endUserFilter);
          try
          {
            //通配符查询，开启*开头，但影响效率
            plateParser.setAllowLeadingWildcard (true);
            plateQuery = plateParser.parse ("*" + text + "*");
            nameParser.setAllowLeadingWildcard (true);
            userNameQuery = nameParser.parse ("*" + text + "*");
            mobileNumParser.setAllowLeadingWildcard (true);
            mobileNumQuery = mobileNumParser.parse ("*" + text + "*");
    
            query.add (plateQuery, Occur.SHOULD);
            query.add (userNameQuery, Occur.SHOULD);
            query.add (mobileNumQuery, Occur.SHOULD);
          }
          catch (ParseException e)
          {
            e.printStackTrace ();
          }
    
          vehicleList = vehicleDao.searchList (query, analyzer, null);
        }
        else {
          //查询全部
          Long tenantID = tenantAccountService.getCurrentTenantID ();
          List<Filter> filters = new ArrayList<Filter>();
          
          Filter tenantIDFilter = new Filter ("tenantID", Operator.eq, tenantID);
          Filter bindStatusFilter = new Filter ("device", Operator.isNotNull);
          filters.add (tenantIDFilter);
          filters.add (bindStatusFilter);
          
          vehicleList=vehicleDao.findList (null, null, filters, null);
        }
        
        String[] propertys = {"endUser.id", "plate","endUser.userName","endUser.mobileNum"};
        return FieldFilterUtils.filterCollectionMap(propertys, vehicleList);
      }

      @Override
      public VehicleDailyReport callVehicleDailyData (Date date, Long vehicleId)
      {
        Vehicle vehicle = vehicleDao.find (vehicleId);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put ("date", DateTimeUtils.convertDateToString (date, "yyyy-MM-dd"));
        params.put ("deviceId", vehicle.getDevice ().getDeviceNo ());
        VehicleDailyReport vehicleDailyReport = new VehicleDailyReport ();
        try
        {
          String response = ApiUtils.post (setting.getObdServiceUrl ()+"tenantVehicleData/dailyVehicleStatus.jhtml" , params);
          
//          String response = "{\"msg\":{\"dailyMileage\":10.0,\"averageFuelConsumption\":10.0,\"fuelConsumption\":0.0,\"cost\":null,\"averageSpeed\":0.0,\"emergencybrakecount\":0,\"suddenturncount\":0,\"rapidlyspeedupcount\":0}}";
          if (response != null)
          {
            OilType oilType = vehicle.getVehicleBrandDetail ().getOilType ();
            String shortPlate = vehicle.getPlate ().substring (0,1);
            
            BigDecimal oilPrice = vehicleOilService.getOidPrice(oilType,shortPlate);
            
            ObjectMapper objectMapper = new ObjectMapper();
           
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode msgNode = rootNode.path ("msg");
            String msg = objectMapper.writeValueAsString(msgNode);
            vehicleDailyReport = objectMapper.readValue (msg, VehicleDailyReport.class);
            
            BigDecimal dailMile = new BigDecimal(vehicleDailyReport.getDailyMileage ().toString ());
            
            vehicleDailyReport.setDeviceId (vehicle.getDevice ().getId ());
            vehicleDailyReport.setReportDate (date);
            vehicleDailyReport.setCost (oilPrice.multiply (dailMile).doubleValue ());
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
        return vehicleDailyReport;
      }

      @Override
      public Vehicle findVehicleByDeviceId (Long deviceId)
      {
        return vehicleDao.findVehicleByDeviceId(deviceId);
        
      }
      
      @Override
      public Page<Vehicle> listUnBuindVehicle (String vehiclePlateSearch,
          String motorcadeSearch, String vehicleFullBrandSearch,
          Pageable pageable)
      {
        return vehicleDao.listUnBuindVehicle(tenantAccountService.getCurrentTenantID (),vehiclePlateSearch,motorcadeSearch,
              vehicleFullBrandSearch,pageable);
      }
}