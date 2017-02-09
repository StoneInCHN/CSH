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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.beans.Setting;
import com.csh.dao.EndUserDao;
import com.csh.dao.VehicleDao;
import com.csh.entity.EndUser;
import com.csh.entity.MessageInfo;
import com.csh.entity.MsgEndUser;
import com.csh.entity.Vehicle;
import com.csh.entity.commonenum.CommonEnum.MessageType;
import com.csh.entity.commonenum.CommonEnum.OilType;
import com.csh.entity.commonenum.CommonEnum.SendType;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.json.response.RealTimeCarCondition;
import com.csh.json.response.VehicleDailyReport;
import com.csh.service.DeviceInfoService;
import com.csh.service.MessageInfoService;
import com.csh.service.MsgEndUserService;
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
public class VehicleServiceImpl extends BaseServiceImpl<Vehicle, Long> implements VehicleService {

  @Resource(name = "vehicleDaoImpl")
  VehicleDao vehicleDao;
  @Resource(name = "endUserDaoImpl")
  EndUserDao endUserDao;
  @Resource(name = "tenantAccountServiceImpl")
  TenantAccountService tenantAccountService;
  @Resource(name = "vehicleOilServiceImpl")
  private VehicleOilService vehicleOilService;
  @Resource(name = "deviceInfoServiceImpl")
  private DeviceInfoService deviceInfoService;
  @Resource(name ="messageInfoServiceImpl")
  private MessageInfoService messageInfoService;
  @Resource(name ="msgEndUserServiceImpl")
  private MsgEndUserService msgEndUserService;
  
  private Setting setting = SettingUtils.get();


  private static double avgJS = 0.110198205D;
  private static double avgSC = 0.307525734D;
  private static double avgZW = 0.194117877D;

  @Resource
  public void setBaseDao(VehicleDao vehicleDao) {
    super.setBaseDao(vehicleDao);
  }

  @Override
  public List<Map<String, Object>> findVehicleUnderUser(Long userId) {
    EndUser user = endUserDao.find(userId);

    List<Filter> filters = new ArrayList<Filter>();

    Filter userFilter = new Filter("endUser", Operator.eq, user);
    filters.add(userFilter);
    Filter plateFilter = new Filter("plate", Operator.ne, "0000000");
    filters.add(plateFilter);
    List<Vehicle> vehicleList = vehicleDao.findList(null, null, filters, null);
    String[] propertys = {"id", "plate", "dashboardMileage", "vehicleFullBrand"};
    return FieldFilterUtils.filterCollectionMap(propertys, vehicleList);

  }

  @Override
  public List<Map<String, Object>> findVehicleUserInfoUnderTenant(String endUserFilter) {
    List<Vehicle> vehicleList = new ArrayList<Vehicle>();
    if (endUserFilter != null) {
      IKAnalyzer analyzer = new IKAnalyzer();
      analyzer.setMaxWordLength(true);
      BooleanQuery query = new BooleanQuery();

      QueryParser nameParser = new QueryParser(Version.LUCENE_35, "endUser.userName", analyzer);
      QueryParser plateParser = new QueryParser(Version.LUCENE_35, "plate", analyzer);
      QueryParser mobileNumParser =
          new QueryParser(Version.LUCENE_35, "endUser.mobileNum", analyzer);
      Query plateQuery = null;
      Query userNameQuery = null;
      Query mobileNumQuery = null;
      Query plateNotQuery = null;
      String text = QueryParser.escape(endUserFilter);
      try {
        // 通配符查询，开启*开头，但影响效率
        plateParser.setAllowLeadingWildcard(true);
        plateQuery = plateParser.parse("*" + text + "*");
        nameParser.setAllowLeadingWildcard(true);
        userNameQuery = nameParser.parse("*" + text + "*");
        mobileNumParser.setAllowLeadingWildcard(true);
        mobileNumQuery = mobileNumParser.parse("*" + text + "*");

        plateNotQuery = plateParser.parse("0000000");

        query.add(plateQuery, Occur.SHOULD);
        query.add(userNameQuery, Occur.SHOULD);
        query.add(mobileNumQuery, Occur.SHOULD);
        query.add(plateNotQuery, Occur.MUST_NOT);
      } catch (ParseException e) {
        e.printStackTrace();
      }

      vehicleList = vehicleDao.searchList(query, analyzer, null);
    } else {
      // 查询全部
      Long tenantID = tenantAccountService.getCurrentTenantID();
      List<Filter> filters = new ArrayList<Filter>();

      Filter tenantIDFilter = new Filter("tenantID", Operator.eq, tenantID);
      Filter bindStatusFilter = new Filter("device", Operator.isNotNull);
      Filter plateFilter = new Filter("plate", Operator.ne, "0000000");
      filters.add(plateFilter);
      filters.add(tenantIDFilter);
      filters.add(bindStatusFilter);

      vehicleList = vehicleDao.findList(null, null, filters, null);
    }

    String[] propertys = {"endUser.id", "plate", "endUser.userName", "endUser.mobileNum"};
    return FieldFilterUtils.filterCollectionMap(propertys, vehicleList);
  }

  @Override
  public VehicleDailyReport callVehicleDailyData(Date date, Long vehicleId) {
    Vehicle vehicle = vehicleDao.find(vehicleId);
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("date", DateTimeUtils.convertDateToString(date, "yyyy-MM-dd"));
    params.put("deviceId", vehicle.getDevice().getDeviceNo());
    VehicleDailyReport vehicleDailyReport = new VehicleDailyReport();
    try {

      // String response = ApiUtils.post (setting.getObdServiceUrl
      // ()+"tenantVehicleData/dailyVehicleStatus.jhtml" , params);
      String response =
          ApiUtils.post(setting.getObdServiceUrl() + "/appVehicleData/oneKeyDetection.jhtml",
              params);
      // String response =
      // "{\"msg\":{\"dailyMileage\":10.0,\"averageFuelConsumption\":10.0,\"fuelConsumption\":0.0,\"cost\":null,\"averageSpeed\":0.0,\"emergencybrakecount\":0,\"suddenturncount\":0,\"rapidlyspeedupcount\":0}}";
      if (response != null) {
        OilType oilType = vehicle.getVehicleBrandDetail().getOilType();
        String shortPlate = vehicle.getPlate().substring(0, 1);

        BigDecimal oilPrice = vehicleOilService.getOidPrice(oilType, shortPlate);

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode msgNode = rootNode.path("msg");
        String msg = objectMapper.writeValueAsString(msgNode);
        vehicleDailyReport = objectMapper.readValue(msg, VehicleDailyReport.class);
        if (vehicleDailyReport == null) {
          return vehicleDailyReport;
        }
        BigDecimal fuelConsumption = new BigDecimal(vehicleDailyReport.getFuelConsumption());

        vehicleDailyReport.setDeviceId(vehicle.getDevice().getDeviceNo());
        vehicleDailyReport.setReportDate(date);
        vehicleDailyReport.setCost(oilPrice.multiply(fuelConsumption)
            .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        Integer score =
            getScore(vehicleDailyReport.getRapidlyspeedupcount(),
                vehicleDailyReport.getEmergencybrakecount(),
                vehicleDailyReport.getSuddenturncount(),
                vehicleDailyReport.getFatiguedrivingcount(), vehicleDailyReport.getMileAge()
                    .doubleValue());
        if (score != -1) {
          vehicleDailyReport.setScore(score);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return vehicleDailyReport;
  }

  @Override
  public Vehicle findVehicleByDeviceId(Long deviceId) {
    return vehicleDao.findVehicleByDeviceId(deviceId);

  }

  @Override
  public Page<Vehicle> listUnBuindVehicle(String vehiclePlateSearch, String motorcadeSearch,
      String vehicleFullBrandSearch, Pageable pageable) {
    return vehicleDao.listUnBuindVehicle(tenantAccountService.getCurrentTenantID(),
        vehiclePlateSearch, motorcadeSearch, vehicleFullBrandSearch, pageable);
  }



  /**
   * 计算驾驶得分
   * 
   * @param js 急加速次数
   * @param sc 急刹车次数
   * @param zw 急转弯次数
   * @param pl 疲劳驾驶次数
   * @param mile 里程
   * @return
   */
  public static Integer getScore(Integer js, Integer sc, Integer zw, Integer pl, Double mile) {
    if (mile <= 0) {
      return -1;
    }
    int score = 100;
    if (pl > 0) {
      score -= 25;
    }
    if (js / mile > avgJS) {
      double out = js / mile - avgJS;
      int percent = (int) (out * 100.0D / avgJS);
      if (percent >= 25)
        score -= 25;
      else {
        score -= percent;
      }
    }
    if (sc / mile > avgSC) {
      double out = sc / mile - avgSC;
      int percent = (int) (out * 100.0D / avgSC);
      if (percent >= 25)
        score -= 25;
      else {
        score -= percent;
      }
    }
    if (zw / mile > avgZW) {
      double out = zw / mile - avgZW;
      int percent = (int) (out * 100.0D / avgZW);
      if (percent >= 25)
        score -= 25;
      else {
        score -= percent;
      }
    }
    if (score == 100) {
      score = 99;
    }
    return score;
  }

  @Override
  public List<Vehicle> listVehicleBindDeviceByTenant(Long tenantID) {
    return vehicleDao.listVehicleBindDeviceByTenant(tenantID);
  }
  @Override
  public Page<Vehicle> listVehicleBindDeviceByTenant(Pageable pageable,Long tenantID,String plate) {
    return vehicleDao.listVehicleBindDeviceByTenant(pageable,tenantID,plate);
  }

  @Override
  public RealTimeCarCondition getRealTimeCarCondition (
      Map<String, Object> params)
  {
    RealTimeCarCondition realTimeCarCondition = null;
    try {
      // String response =
      // "{\"msg\":{\"mileAge\":100,\"engineRuntime\":12,\"averageOil\":10,\"speed\":60,\"lon\":104.0637,\"lat\":30.6338,\"azimuth\":null,\"acc\":1}}";
      String response =
          ApiUtils.post(setting.getObdServiceUrl()
              + "tenantVehicleData/realTimeVehicleStatus.jhtml", params);
      ObjectMapper objectMapper = new ObjectMapper();
      if (response != null && !response.equals ("")){
        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode msgNode = rootNode.path("msg");
        String msg = objectMapper.writeValueAsString(msgNode);
        realTimeCarCondition = objectMapper.readValue(msg, RealTimeCarCondition.class);
        if (realTimeCarCondition.getIsNeedToAddInitMileAge()) {
          Vehicle vehicle = this.findVehicleByDeviceId((Long)params.get ("deviceId"));
          if(vehicle != null){
            realTimeCarCondition.setMileAge(realTimeCarCondition.getMileAge()
                + vehicle.getDriveMileage());
            }
        }
      }
      
      return realTimeCarCondition;
    } catch (Exception e) {
      e.printStackTrace();
    }
   return null;
  }

  
  @Override
  @Transactional(propagation=Propagation.REQUIRED)
  public MessageInfo updateMaintainReminder (Boolean maintainRequired,Vehicle vehicle)
  {
    vehicle.setIsMaintainReminder (false);
    vehicle.setLastMaintainMileage (vehicle.getDashboardMileage ().longValue ());
    this.update (vehicle);
    if (maintainRequired)
    {
      MessageInfo msgInfo = new MessageInfo ();
      msgInfo.setMessageTitle ("保养提醒");
      
      msgInfo.setMessageType (MessageType.PERSONALMSG);
      msgInfo.setMessageContent ("车辆需要保养，请尽快前往4S店做保养");
      msgInfo.setSendType (SendType.PUSH);
      msgInfo.setTenantID (vehicle.getTenantID ());
      EndUser owner = vehicle.getEndUser ();
      
      MsgEndUser msgEndUser = new MsgEndUser ();
      msgEndUser.setEndUser (owner);
      msgEndUser.setIsRead (false);
      msgEndUser.setIsPush (false);
      msgEndUser.setMessage (msgInfo);
      msgEndUserService.save (msgEndUser);
      vehicle.setIsMaintainReminder (false);
      messageInfoService.save (msgInfo);
      return msgInfo;
    }
    return null;
  }
}
