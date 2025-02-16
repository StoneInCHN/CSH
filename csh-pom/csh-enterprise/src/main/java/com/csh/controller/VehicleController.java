package com.csh.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.beans.Message;
import com.csh.beans.Setting;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.DeviceInfo;
import com.csh.entity.MessageInfo;
import com.csh.entity.Vehicle;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.request.MsgRequest;
import com.csh.json.response.VehicleDailyReport;
import com.csh.json.response.VehicleStatus;
import com.csh.service.DeviceInfoService;
import com.csh.service.EndUserService;
import com.csh.service.FaultCodeService;
import com.csh.service.TenantAccountService;
import com.csh.service.VehicleBrandDetailService;
import com.csh.service.VehicleOilService;
import com.csh.service.VehicleService;
import com.csh.utils.ApiUtils;
import com.csh.utils.DateTimeUtils;
import com.csh.utils.LatLonUtil;
import com.csh.utils.SettingUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 车辆管理
 * 
 * @author huyong
 *
 */
@Controller("vehicleController")
@RequestMapping("console/vehicle")
public class VehicleController extends BaseController {

  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;
  @Resource(name = "deviceInfoServiceImpl")
  private DeviceInfoService deviceInfoService;
  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;
  @Resource(name = "vehicleBrandDetailServiceImpl")
  private VehicleBrandDetailService vehicleBrandDetailService;
  @Resource(name = "vehicleOilServiceImpl")
  private VehicleOilService vehicleOilService;
  @Resource(name = "faultCodeServiceImpl")
  private FaultCodeService faultCodeService;
  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  private Setting setting = SettingUtils.get();

  @RequestMapping(value = "/vehicle", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "vehicle/vehicle";
  }

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Vehicle> list(Pageable pageable, ModelMap model, Date beginDate,
      Date endDate, String plateSearch, String userNameSearch, String mobileNumSearch,
      Integer bindStatus) {
    return searchVehicleList(pageable, model, beginDate, endDate, plateSearch, userNameSearch,
        mobileNumSearch, bindStatus);
  }

  /**
   * 私有查询车辆信息方法
   * 
   * @param pageable
   * @param model
   * @param beginDate
   * @param endDate
   * @param plateSearch
   * @param userNameSearch
   * @param mobileNumSearch
   * @return
   */
  private Page<Vehicle> searchVehicleList(Pageable pageable, ModelMap model, Date beginDate,
      Date endDate, String plateSearch, String userNameSearch, String mobileNumSearch,
      Integer bindStatus) {


    Page<Vehicle> vehiclePage;
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    BooleanQuery query = new BooleanQuery();

    QueryParser plateParser = new QueryParser(Version.LUCENE_35, "plate", analyzer);
    QueryParser userNameParser = new QueryParser(Version.LUCENE_35, "endUser.userName", analyzer);
    QueryParser mobileNumParser = new QueryParser(Version.LUCENE_35, "endUser.mobileNum", analyzer);
    Query plateQuery = null;
    Query plateNotQuery = null;
    Query userNameQuery = null;
    Query mobileNumQuery = null;
    Query delFlagQuery = null;
    TermRangeQuery rangeQuery = null;


    Filter filter = null;
    if (beginDate != null) {
      startDateStr = DateTimeUtils.convertDateToString(beginDate, null);
    }
    if (endDate != null) {
      endDateStr = DateTimeUtils.convertDateToString(endDate, null);
    }
    if (plateSearch != null) {
      String text = QueryParser.escape(plateSearch);
      try {
        // 通配符查询，开启*开头，但影响效率
        plateParser.setAllowLeadingWildcard(true);

        plateQuery = plateParser.parse("*" + text + "*");

        query.add(plateQuery, Occur.MUST);

        if (LogUtil.isDebugEnabled(VehicleController.class)) {
          LogUtil.debug(VehicleController.class, "search", "Search plate: " + plateSearch);
        }
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    if (userNameSearch != null) {
      String text = QueryParser.escape(userNameSearch);
      try {
        userNameQuery = userNameParser.parse(text);
        query.add(userNameQuery, Occur.MUST);

        if (LogUtil.isDebugEnabled(VehicleController.class)) {
          LogUtil.debug(VehicleController.class, "search", "Search user name: " + userNameSearch);
        }
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    if (mobileNumSearch != null) {

      String text = QueryParser.escape(mobileNumSearch);
      try {
        mobileNumParser.setAllowLeadingWildcard(true);
        mobileNumQuery = mobileNumParser.parse("*" + text + "*");
        query.add(mobileNumQuery, Occur.MUST);

        if (LogUtil.isDebugEnabled(VehicleController.class)) {
          LogUtil.debug(VehicleController.class, "search", "Search user name: " + userNameSearch);
        }
      } catch (ParseException e) {
        e.printStackTrace();
      }

    }
    if (startDateStr != null || endDateStr != null) {
      rangeQuery = new TermRangeQuery("createDate", startDateStr, endDateStr, true, true);
      query.add(rangeQuery, Occur.MUST);

      if (LogUtil.isDebugEnabled(VehicleController.class)) {
        LogUtil.debug(VehicleController.class, "search", "Search start date: " + startDateStr
            + " end date: " + endDateStr);
      }
    }
    plateNotQuery = new TermQuery(new Term("plate", "0000000"));
    query.add(plateNotQuery, Occur.MUST_NOT);

    delFlagQuery = new TermQuery(new Term("delFlag", "false"));
    query.add(delFlagQuery, Occur.MUST);
    if (plateQuery != null || userNameQuery != null || mobileNumQuery != null || rangeQuery != null) {
      vehiclePage = vehicleService.search(query, pageable, analyzer, filter, true);
    } else {
      List<com.csh.framework.filter.Filter> filters =
          new ArrayList<com.csh.framework.filter.Filter>();
      com.csh.framework.filter.Filter plateFilter =
          new com.csh.framework.filter.Filter("plate", Operator.ne, "0000000");
      com.csh.framework.filter.Filter delFilter =
          new com.csh.framework.filter.Filter("delFlag", Operator.eq, false);
      if (bindStatus != null) {
        if (bindStatus == 1) {// 已绑定
          vehiclePage =
              vehicleService.listVehicleBindDeviceByTenant(pageable,
                  tenantAccountService.getCurrentTenantID(), null);
        } else if (bindStatus == 2) {// 未绑定
          vehiclePage = vehicleService.listUnBuindVehicle(null, null, null, pageable);
        } else {
          filters.add(plateFilter);
          filters.add(delFilter);
          pageable.setFilters(filters);
          vehiclePage = vehicleService.findPage(pageable, true);
        }
      } else {
        filters.add(plateFilter);
        filters.add(delFilter);
        pageable.setFilters(filters);
        vehiclePage = vehicleService.findPage(pageable, true);
      }
    }
    prepareVehicleList(vehiclePage.getRows());
    return vehiclePage;

  }

  /**
   * get data for vendor edit page
   * 
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    Vehicle vehicle = vehicleService.find(id);
    model.put("vehicle", vehicle);
    return "vehicle/edit";
  }

  // @RequestMapping (value = "/add", method = RequestMethod.POST)
  // public @ResponseBody Message add (Vehicle vehicle,Long endUserID,Long deviceInfoID, Long
  // vehicleBrandDetailId)
  // {
  // EndUser endUser = endUserService.find (endUserID);
  // DeviceInfo deviceInfo = deviceInfoService.find (deviceInfoID);
  // deviceInfo.setDeviceStatus (DeviceStatus.BINDED);
  // deviceInfo.setBindTime (new Date ());
  // VehicleBrandDetail vehicleBrandDetail = vehicleBrandDetailService.find (vehicleBrandDetailId);
  // vehicle.setDevice (deviceInfo);
  // vehicle.setEndUser (endUser);
  // vehicle.setVehicleBrandDetail (vehicleBrandDetail);
  // vehicleService.save (vehicle,true);
  // return SUCCESS_MESSAGE;
  // }
  //
  // @RequestMapping (value = "/update", method = RequestMethod.POST)
  // public @ResponseBody Message update (Vehicle vehicle,Long endUserID,Long deviceInfoID, Long
  // vehicleBrandDetailId)
  // {
  // EndUser endUser = endUserService.find (endUserID);
  // DeviceInfo deviceInfo = deviceInfoService.find (deviceInfoID);
  // deviceInfo.setDeviceStatus (DeviceStatus.BINDED);
  // deviceInfo.setBindTime (new Date ());
  // VehicleBrandDetail vehicleBrandDetail = vehicleBrandDetailService.find (vehicleBrandDetailId);
  // vehicle.setDevice (deviceInfo);
  // vehicle.setEndUser (endUser);
  // vehicle.setVehicleBrandDetail (vehicleBrandDetail);
  // vehicleService.update (vehicle);
  // return SUCCESS_MESSAGE;
  // }


  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      // 检查是否能被删除
      List<Vehicle> vehicles = vehicleService.findList(ids);
      for (Vehicle v : vehicles) {
        if (v.getDeviceNo() != null) {
          return Message.error("csh.vehicle.delete.invalid", v.getPlate(), v.getDeviceNo());
        }
        v.setDelFlag(true);
      }
      vehicleService.update(vehicles);
    }
    return SUCCESS_MESSAGE;
  }

  /**
   * 获取数据进入详情页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {
    Vehicle vehicle = vehicleService.find(id);
    model.addAttribute("vehicle", vehicle);
    return "vehicle/details";
  }

  /**
   * 导出列表数据，即用户已经查询出来的数据
   * 
   * @param withDays
   */
  @RequestMapping(value = "/exportData", method = {RequestMethod.GET, RequestMethod.POST})
  public void exportData(HttpServletResponse response, Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String plateSearch, String userNameSearch,
      String mobileNumSearch, Integer bindStatus) {
    pageable.setPage(1);
    pageable.setRows(1000);
    List<Vehicle> vehicleList = null;
    if (plateSearch != null || beginDate != null || endDate != null || userNameSearch != null
        || mobileNumSearch != null) {
      vehicleList =
          searchVehicleList(pageable, model, beginDate, endDate, plateSearch, userNameSearch,
              mobileNumSearch, bindStatus).getRows();
    } else {
      List<com.csh.framework.filter.Filter> filters =
          new ArrayList<com.csh.framework.filter.Filter>();
      com.csh.framework.filter.Filter plateFilter =
          new com.csh.framework.filter.Filter("plate", Operator.ne, "0000000");
      com.csh.framework.filter.Filter delFilter =
          new com.csh.framework.filter.Filter("delFlag", Operator.eq, false);
      if (bindStatus != null) {
        if (bindStatus == 1) {// 已绑定
          vehicleList =
              vehicleService.listVehicleBindDeviceByTenant(pageable,
                  tenantAccountService.getCurrentTenantID(), null).getRows();
        } else if (bindStatus == 2) {// 未绑定
          vehicleList = vehicleService.listUnBuindVehicle(null, null, null, pageable).getRows();
        } else {
          filters.add(plateFilter);
          filters.add(delFilter);
          pageable.setFilters(filters);
          vehicleList = vehicleService.findPage(pageable, true).getRows();
        }
      } else {
        filters.add(plateFilter);
        filters.add(delFilter);
        pageable.setFilters(filters);
        vehicleList = vehicleService.findPage(pageable, true).getRows();
      }
    }

    if (vehicleList != null && vehicleList.size() > 0) {
      String title = "车辆信息"; // 工作簿标题，同时也是excel文件名前缀
      String[] headers =
          {"user", "mobileNum", "plate", "vehicleBrandDetail", "color", "bindingDevice"}; // 需要导出的字段
      String[] headersName = {"所有人", "电话号码", "车牌号", "车型", "颜色", "绑定的设备"}; // 字段对应列的列名
      // 导出数据到Excel
      List<Map<String, String>> eventRecordMapList = prepareMap(vehicleList);
      if (eventRecordMapList.size() > 0) {
        exportListToExcel(response, eventRecordMapList, title, headers, headersName);
      }
    }
  }

  /**
   * 准备excel需要的map数据
   * 
   * @param vehicleList
   * @return
   */
  private List<Map<String, String>> prepareMap(List<Vehicle> vehicleList) {
    List<Map<String, String>> resultMapList = new ArrayList<Map<String, String>>();
    for (Vehicle vehicle : vehicleList) {
      Map<String, String> vehicleMap = new HashMap<String, String>();

      vehicleMap.put("user", vehicle.getEndUser().getUserName());
      vehicleMap.put("mobileNum", vehicle.getEndUser().getMobileNum());
      vehicleMap.put("plate", vehicle.getPlate());
      if (vehicle.getVehicleBrandDetail() != null) {
        vehicleMap.put("vehicleBrandDetail", vehicle.getVehicleBrandDetail().getName());
      }

      vehicleMap.put("color", vehicle.getColor());
      vehicleMap.put("bindingDevice", vehicle.getDeviceNo());
      resultMapList.add(vehicleMap);
    }
    return resultMapList;
  }

  /**
   * 查询用户名下所有车辆
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/findVehicleUnderUser", method = RequestMethod.GET)
  public @ResponseBody List<Map<String, Object>> findVehicleUnderUser(ModelMap model, Long userId) {

    return vehicleService.findVehicleUnderUser(userId);
  }

  /**
   * 查询用户名下所有车辆
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/findVehicleUserInfoUnderTenant", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findVehicleUserInfoUnderTenant(ModelMap model,
      String endUserFilter) {

    return vehicleService.findVehicleUserInfoUnderTenant(endUserFilter);
  }

  /**
   * 查询车辆实时数据
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/realTimeCarCondition", method = RequestMethod.GET)
  public String getRealTimeCarCondition(ModelMap model, Long deviceId) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("deviceId", deviceId);
    
    model.put("realTimeCarCondition", vehicleService.getRealTimeCarCondition(params));
    return "vehicle/realTimeCarCondition";
  }

  /**
   * 查询车辆实时数据
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/vehicleDailyReport", method = RequestMethod.GET)
  public String getVehicleDailyReport(ModelMap model, Long vehicleId) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_MONTH, -1);
    VehicleDailyReport report =
        vehicleService.callVehicleDailyData(new Date(calendar.getTimeInMillis()), vehicleId);
    if (report != null) {
      model.put("vehicleDailyReport", report);
      model.put("vehicleReportDate",
          DateTimeUtils.convertDateToString(report.getReportDate(), "yyyy-MM-dd"));
    }

    return "vehicle/vehicleDailyReport";
  }

  @RequestMapping(value = "/getVehicleDailyData", method = RequestMethod.POST)
  public @ResponseBody VehicleDailyReport getVehicleDailyData(ModelMap model, Date date,
      Long vehicleId) {
    return vehicleService.callVehicleDailyData(date, vehicleId);

  }

  private List<Vehicle> prepareVehicleList(List<Vehicle> vehicleList) {
    ObjectMapper objectMapper = new ObjectMapper();
    List<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
    for (Vehicle vehicle : vehicleList) {
      DeviceInfo deviceInfo = vehicle.getDevice();
      if (deviceInfo != null) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("deviceId", deviceInfo.getDeviceNo());
        map.put("rowId", deviceInfo.getId());
        paramList.add(map);
      }

    }
    try {
      String params = objectMapper.writeValueAsString(paramList);

      String response =
          ApiUtils.postJson(setting.getObdServiceUrl()
              + "tenantVehicleData/vehicleOnlineStatus.jhtml", "UTF-8", "UTF-8", params);
      // String response =
      // "{\"msg\": [{\"deviceId\": \"1\",\"rowId\": \"1\",\"mileage\": 100,\"online\": true,\"remaininggas\": 10, \"bv\": 12.5},{\"deviceId\": \"2\",\"rowId\": \"1\",\"mileage\": 20,\"online\": true,\"remaininggas\": 20,\"bv\": 10.5}]}";
      if (response != null && !response.equals("")) {
        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode msgNode = rootNode.path("msg");
        String msg = objectMapper.writeValueAsString(msgNode);
        List<VehicleStatus> vehicleStatusList =
            objectMapper.readValue(msg, new TypeReference<List<VehicleStatus>>() {});
        for (Vehicle vehicle : vehicleList) {
          for (VehicleStatus vehicleStatus : vehicleStatusList) {
            if (vehicle.getDevice() != null
                && vehicle.getDevice().getId().toString().equals(vehicleStatus.getRowId())) {
              vehicle.setDashboardBV(vehicleStatus.getBv());
              if (vehicleStatus.getMileage() != null && vehicleStatus.getMileage() != 0) {
                vehicle.setDashboardMileage(vehicleStatus.getMileage());
              } else {
                vehicle.setDashboardMileage(vehicleStatus.getGpsMileage()
                    + vehicle.getDriveMileage());
              }
              vehicle.setDashboradOil(vehicleStatus.getRemaininggas());
              vehicle.setIsOnline(vehicleStatus.getOnline());
              vehicle.setLat(vehicleStatus.getLat());
              vehicle.setLon(vehicleStatus.getLon());
              vehicle.setObdStatusTime(vehicleStatus.getCreatetime());
              vehicle.setSpeed(vehicleStatus.getSpeed());
              // 解析故障码
              if (vehicleStatus.getFaultcode() != null) {
                String[] faultCodes = vehicleStatus.getFaultcode().split(",");
                Set<String> faultCodeSet = new HashSet<String>();
                for (String faultCode : faultCodes) {
                  String code = faultCode.split(":")[0].trim();
                  if (!faultCodeSet.contains(code)) {
                    faultCodeSet.add(code);
                  }
                }
                vehicle.setFaultCodeSet(faultCodeSet);
              }
              //判断是否需要保养提醒
              if (!vehicle.getIsMaintainReminder () 
                  && vehicle.getDashboardMileage ()-vehicle.getLastMaintainMileage () >= vehicle.getMileagePerMaintain ())
              {
                vehicle.setIsMaintainReminder (true);
              }
            }
          }
        }
        vehicleService.update(vehicleList);
      }
    }

    catch (Exception e) {
      e.printStackTrace();
    }
    return vehicleList;
  }

  /**
   * 接受警告消息
   * 
   * @return
   */
  @RequestMapping(value = "/pushVehicleWainingInfo", method = RequestMethod.POST)
  public @ResponseBody String pushVehicleWainingInfo(@RequestBody List<MsgRequest> msgRequestList) {
    MsgRequest msgRequest = msgRequestList.get(0);
    Vehicle vehicle =
        vehicleService.findVehicleByDeviceId(Long.parseLong(msgRequest.getDeviceNo()));

    if (vehicle != null) {
      vehicle.setWainingInfo(msgRequest.getMsgContent());
      vehicleService.update(vehicle);
    }
    return "success";
  }

  @RequestMapping(value = "/listUnBuindVehicle", method = RequestMethod.POST)
  public @ResponseBody Page<Vehicle> listUnBuindVehicle(String vehicleFullBrandSearch,
      String motorcadeSearch, String vehiclePlateSearch, Pageable pageable) {

    return vehicleService.listUnBuindVehicle(vehiclePlateSearch, motorcadeSearch,
        vehicleFullBrandSearch, pageable);
  }

  @RequestMapping(value = "/showFaultDetail", method = RequestMethod.GET)
  public String showFaultDetail(ModelMap model, String faultCode) {

    model.put("faultCode", faultCodeService.findByCode(faultCode));
    return "vehicle/faultCode";
  }

  @RequestMapping(value = "/allVehicleStatus", method = RequestMethod.GET)
  public String allVehicleStatus(ModelMap model) {
    model.put("tenantInfo", tenantAccountService.getCurrentTenantInfo());
    return "vehicle/allVehicleStatus";
  }

  @RequestMapping(value = "/allVehicleStatus", method = RequestMethod.POST)
  public @ResponseBody List<Vehicle> allVehicleStatus() {
    // List<com.csh.framework.filter.Filter> filters =
    // new ArrayList<com.csh.framework.filter.Filter>();
    // com.csh.framework.filter.Filter exceptFilter =
    // new com.csh.framework.filter.Filter("plate", Operator.ne, "0000000");
    // filters.add(exceptFilter);
    List<Vehicle> vehicleList =
        prepareVehicleList(vehicleService.listVehicleBindDeviceByTenant(tenantAccountService
            .getCurrentTenantID()));
    List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
    for (Vehicle vehicle : vehicleList) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("lon", vehicle.getLon());
      map.put("lat", vehicle.getLat());
      map.put("vehicleId", vehicle.getId());
      map.put("speed", vehicle.getSpeed());
      mapList.add(map);
    }
    List<Map<String, Object>> newMapList = LatLonUtil.convertCoordinates(mapList);
    for (int i = 0; i < newMapList.size(); i++) {
      for (int j = 0; j < vehicleList.size(); j++) {
        if (vehicleList.get(j).getId().equals(newMapList.get(i).get("vehicleId"))) {
          vehicleList.get(j).setLon(Float.parseFloat(newMapList.get(i).get("x").toString()));
          vehicleList.get(j).setLat(Float.parseFloat(newMapList.get(i).get("y").toString()));
          break;
        }
      }

    }
    return vehicleList;
  }

  @RequestMapping(value = "/listVehicleHasDevice", method = RequestMethod.POST)
  public @ResponseBody Page<Vehicle> listVehicleHasDevice(Pageable pageable, String plateSearch) {

    return vehicleService.listVehicleBindDeviceByTenant(pageable,
        tenantAccountService.getCurrentTenantID(), plateSearch);
  }
  @RequestMapping(value = "/updateMaintainReminder", method = RequestMethod.POST)
  public @ResponseBody Message updateMaintainReminder(Boolean maintainRequired,Long vehicleId) {

    Vehicle vehicle = vehicleService.find (vehicleId);
    MessageInfo messageInfo = vehicleService.updateMaintainReminder (maintainRequired,vehicle);
    if (messageInfo != null )
    {
      Setting setting = SettingUtils.get();
      String params = "[{\"msgId\":"+messageInfo.getId ()+"}]";
      ApiUtils.postJson (setting.getMsgPushUrl (),"UTF-8", "UTF-8", params);
    }
    return SUCCESS_MESSAGE;
  }  
  @RequestMapping(value = "/updateMaintainMileage", method = RequestMethod.POST)
  public @ResponseBody Message updateMaintainMileage(Vehicle newVehicle) {

    Vehicle vehicle = vehicleService.find (newVehicle.getId ());
    if (vehicle.getLastMaintainMileage () != newVehicle.getLastMaintainMileage ())
    {
      vehicle.setLastMaintainMileage (newVehicle.getLastMaintainMileage ());
    }
    if (vehicle.getMileagePerMaintain () != newVehicle.getMileagePerMaintain ())
    {
      vehicle.setMileagePerMaintain (newVehicle.getMileagePerMaintain ());
    }
    vehicleService.update (vehicle);
    return SUCCESS_MESSAGE;
  } 
}
