package com.csh.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.hibernate.c3p0.internal.StrategyRegistrationProviderImpl;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.beans.Setting;
import com.csh.controller.base.BaseController;
import com.csh.entity.DeviceInfo;
import com.csh.entity.Vehicle;
import com.csh.entity.VehicleOil;
import com.csh.entity.commonenum.CommonEnum.OilType;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.json.request.VehicleReportListRequest;
import com.csh.json.response.VehicleReport;
import com.csh.json.response.VehicleReportList;
import com.csh.json.response.VehicleStatus;
import com.csh.service.DeviceInfoService;
import com.csh.service.TenantAccountService;
import com.csh.service.VehicleOilService;
import com.csh.service.VehicleService;
import com.csh.utils.ApiUtils;
import com.csh.utils.DateTimeUtils;
import com.csh.utils.ExportExcel;
import com.csh.utils.SettingUtils;
import com.csh.utils.SpringUtils;
import com.csh.utils.VehicleReportExportUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Controller - 保养报表
 * 
 * @author yohu
 *
 */
@Controller("ReportVehicleMileageController")
@RequestMapping("console/vehicleMileageReport")
public class ReportVehicleMileageController extends BaseController {
  private Setting setting = SettingUtils.get();

  @Resource(name = "vehicleOilServiceImpl")
  private VehicleOilService vehicleOilService;

  @Resource(name = "deviceInfoServiceImpl")
  private DeviceInfoService deviceInfoService;
  
  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;
  
  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  
  @Resource(name = "threadPoolExecutor")
  private Executor threadPoolExecutor;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/vehicleMileageReport", method = RequestMethod.GET)
  public String list(ModelMap model) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    int year = calendar.get(Calendar.YEAR);//默认查询年份(当年)
    model.put("year", year); 
    int month = calendar.get(Calendar.MONTH)+1;//默认查询月份（当月）
    model.put("month", month); 
    
    //可选年份
    List<Integer> selectYears = new ArrayList<Integer>();
    for (int i = year; i >= 2016; i--) {
      selectYears.add(i);
    }
    model.put("selectYears", selectYears);
    return "/report/vehicleMileageReport";
  }

  /**
   * 获取车辆所在地的当日油价,默认93号汽油
   * 
   * @param deviceId
   * @param fromDate
   * @param toDate
   * @return
   */
  @RequestMapping(value = "/getOilPrice", method = RequestMethod.POST)
  public @ResponseBody String oilPrice(String deviceId) {
    List<Filter> filters = new ArrayList<Filter>();
    Filter deviceNoFilter = new Filter("deviceNo", Operator.eq, deviceId);
    filters.add(deviceNoFilter);
    List<DeviceInfo> deviceInfos = deviceInfoService.findList(null, filters, null);
    if (deviceInfos != null && deviceInfos.size() == 1) {
      Vehicle vehicle = deviceInfos.get(0).getVehicle();
      String plate = vehicle.getPlate();
      if (plate == null) {
        return "0";
      }
      plate = plate.substring(0, 1);
      OilType oilType = vehicle.getVehicleBrandDetail().getOilType();
      if (oilType == null) {
        // 默认93号汽油
        oilType = OilType.P93;
      }
      List<Filter> oilFilters = new ArrayList<Filter>();
      Filter plateFilter = new Filter("shortPlate", Operator.eq, plate);
      Filter oilTypeFilter = new Filter("oilType", Operator.eq, oilType);
      oilFilters.add(plateFilter);
      oilFilters.add(oilTypeFilter);
      List<VehicleOil> oils = vehicleOilService.findList(null, oilFilters, null);
      if (oils == null || oils.size() != 1) {
        return "0";
      }
      VehicleOil oil = oils.get(0);
      return oil.getPrice().toString();
    }
    return "0";
  }
 
  /**
   * @param params 参数：deviceId=8801001667&fromDate=2016-4-1&toDate=2016-4-30
   */
  @RequestMapping(value = "/monthlyVehicleStatus", method = RequestMethod.POST)
  public @ResponseBody String monthlyVehicleStatus(String deviceId, String fromDate, String toDate) {
    // String mileageJson =
    // "{'msg':[{'dailyMileage': 10,'averageFuelConsumption': 17,'fuelConsumption': 16,'cost': null,'averageSpeed': 19,'emergencybrakecount': 2,'suddenturncount': 0,'rapidlyspeedupcount': 4,'createdate': 1459872000000,'day': 6},{'dailyMileage': 23,'averageFuelConsumption': 7,'fuelConsumption': 9,'cost': null,'averageSpeed': 8,'emergencybrakecount': 7,'suddenturncount': 10,'rapidlyspeedupcount': 13,'createdate': 1459958400000,'day': 7}]}";
    // return mileageJson.replaceAll("'", "\"");
    String mileageJson = null;
    if (deviceId != null && fromDate != null && toDate != null) {
      StringBuffer params = new StringBuffer();
      params.append("deviceId=");
      params.append(deviceId);
      params.append("&fromDate=");
      params.append(fromDate);
      params.append("&toDate=");
      params.append(toDate);
      mileageJson =
          ApiUtils.post(
              setting.getObdServiceUrl() + "tenantVehicleData/monthlyVehicleStatus.jhtml",
              params.toString());
      return mileageJson;
    } else {
      return "";
    }
  }
  /**
   * 一次性导出所有车辆一个月报表数据
   * @param items
   * @param year
   * @param month
   * @return
   */
  @RequestMapping(value = "/exportAllVehicleReport", method = RequestMethod.POST)
  public void export(HttpServletResponse response, String[] exportItems, Date fromDate, Date toDate) {
    Assert.notNull(fromDate);
    Assert.notNull(toDate);
    Assert.notEmpty(exportItems);
    String[] items = null; 
    List<String> itmeList = Arrays.asList(exportItems);
    if (itmeList.contains("drivingBehavior")) {//需要导出驾驶行为
      exportItems[exportItems.length-1] = "emergencybrakecount";
      String[] append = {"suddenturncount","rapidlyspeedupcount"};
      items = (String[]) ArrayUtils.addAll(exportItems, append);
    }else {
      items = exportItems;
    }
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      List<Vehicle> vehicleList = vehicleService.listVehicleBindDeviceByTenant(tenantAccountService
          .getCurrentTenantID());
      VehicleReportListRequest request = constructRequstParam(vehicleList, fromDate, toDate);
      String params = objectMapper.writeValueAsString(request);
      String result =
          ApiUtils.postJson(setting.getObdServiceUrl()
              + "tenantVehicleData/monthlyVehicleStatusByDeviceIds.jhtml", "UTF-8", "UTF-8", params);
      if (result != null && !result.equals("")) {
        JsonNode msgNode = objectMapper.readTree(result).path("msg");
        String msg = objectMapper.writeValueAsString(msgNode);
        List<VehicleReportList> vehicleReportLists =
            objectMapper.readValue(msg, new TypeReference<List<VehicleReportList>>() {});
        
        String yearMonth = (request.getFromDate().getYear()+1900)+ "年"+ (request.getFromDate().getMonth()+1)+"月";
        int days = request.getToDate().getDate();//本月多少天
        Map<String, Map<String, String[]>> sheetMap = new HashMap<String, Map<String,String[]>>();
        Map<String, String> keyMethod = new HashMap<String, String>();
        for (int i = 0; i < items.length; i++) {
          sheetMap.put(items[i], new HashMap<String, String[]>());
          keyMethod.put(items[i], getProterty(items[i]));
        }
        boolean exportCost = itmeList.contains("cost");//是否要导出油费金额
        Map<String, Float> oilMap = null;
        if (exportCost) {
          oilMap = getOilPriceMap(vehicleList);
        }
        
        for (int i = 0; i < vehicleReportLists.size(); i++) {
          VehicleReportList vehicleReportList = vehicleReportLists.get(i);
          List<VehicleReport> list = vehicleReportList.getList();
          for (int k = 0; k < items.length; k++) {
            sheetMap.get(items[k]).put(vehicleReportList.getDeviceId(), new String[days]);
          }
          for (int j = 0; j < list.size(); j++) {
            VehicleReport report = list.get(j);
            if (exportCost && oilMap != null) {
              float price = oilMap.get(vehicleReportList.getDeviceId());
              report.setCost(Double.valueOf(report.getFuelConsumption() * price));
            }
            for (int h = 0; h < items.length; h++) {
              Method m = VehicleReport.class.getMethod(keyMethod.get(items[h]));
              sheetMap.get(items[h]).get(vehicleReportList.getDeviceId())[report.getDay() - 1]  
                  = m.invoke(report).toString();
            }
          }
        }
        
        response.setContentType("octets/stream");
        //导出文件名
        String filename = "Vehicle Report Data" + "_" + DateTimeUtils.getSimpleFormatString(DateTimeUtils.filePostfixFormat, new Date());
        response.addHeader("Content-Disposition", "attachment;filename=" + filename + ".xls");
        OutputStream out = response.getOutputStream();// 获得输出流  
        
        Object locker = new Object();//当前主线程的一把锁
        VehicleReportExportUtil ex = new VehicleReportExportUtil(days, yearMonth, vehicleList, items, sheetMap, out, locker);// 开启一个导出数据的线程

        synchronized (locker) {
          threadPoolExecutor.execute(ex); // 加入到线程池中执行
          locker.wait();//释放对象锁的控制，主线程等待，当且仅当其他线程notify该对象锁，主线程才可以继续执行下去
        }
        out.flush();
        out.close();
      }
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
  private String getProterty(String property) {
    return "get"+property.substring(0,1).toUpperCase()+property.substring(1);
  }
  /**
   * （设备号 - 油费） Map
   */
  private Map<String, Float> getOilPriceMap(List<Vehicle> vehicleList){
    Map<String, Float> oilMap = new HashMap<String, Float>();
    if (vehicleList == null || vehicleList.size() == 0) {
      return null;
    }
    for (Vehicle vehicle : vehicleList) {
      if (vehicle.getPlate() == null) {
        oilMap.put(vehicle.getDeviceNo(), 0f);
        continue;
      }
      List<Filter> filters = new ArrayList<Filter>();
      filters.add(Filter.eq("shortPlate", vehicle.getPlate().substring(0, 1)));
      if (vehicle.getVehicleBrandDetail() != null) {
        filters.add(Filter.eq("oilType", vehicle.getVehicleBrandDetail().getOilType()));
      }
      List<VehicleOil> oils = vehicleOilService.findList(null, filters, null);
      if (oils == null || oils.size() == 0) {
        oilMap.put(vehicle.getDeviceNo(), 0f);
        continue;
      }
      if (oils.get(0).getPrice() != null) {
        oilMap.put(vehicle.getDeviceNo(), oils.get(0).getPrice().floatValue());
      }else {
        oilMap.put(vehicle.getDeviceNo(), 0f);
      }
    }
    return oilMap;
  }
  /**
   * 组装请求参数
   */
  private VehicleReportListRequest constructRequstParam(List<Vehicle> vehicleList, Date fromDate, Date toDate) throws Exception{
    VehicleReportListRequest request = new VehicleReportListRequest();
    request.setFromDate(fromDate);
    request.setToDate(toDate);
    List<String> deviceIds = new ArrayList<String>();
    for (int i = 0; i < vehicleList.size(); i++) {
      Vehicle vehicle = vehicleList.get(i);
      deviceIds.add(vehicle.getDeviceNo());
    }
    request.setDeviceIds(deviceIds);
    return request;
  }


}
