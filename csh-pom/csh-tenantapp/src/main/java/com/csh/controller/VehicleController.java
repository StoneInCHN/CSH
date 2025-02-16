package com.csh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.aspect.UserValidCheck;
import com.csh.beans.CommonAttributes;
import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.DeviceInfo;
import com.csh.entity.FaultCode;
import com.csh.entity.Vehicle;
import com.csh.entity.commonenum.CommonEnum.OnlineStatus;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Page;
import com.csh.json.base.PageResponse;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.base.ResponseOne;
import com.csh.json.request.VehicleRequest;
import com.csh.json.response.VehiclePageResponse;
import com.csh.json.response.VehicleStatus;
import com.csh.service.DeviceInfoService;
import com.csh.service.FaultCodeService;
import com.csh.service.TenantAccountService;
import com.csh.service.TenantInfoService;
import com.csh.service.VehicleService;
import com.csh.utils.ApiUtils;
import com.csh.utils.DateTimeUtils;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.LatLonUtil;
import com.csh.utils.TokenGenerator;
import com.csh.utils.ToolsUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 车辆查询控制器 Created by zhangye on 2016/12/25.
 */
@Controller("vehicleController")
@RequestMapping("/vehicle")
public class VehicleController extends MobileBaseController {

  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;

  @Resource(name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;

  @Resource(name = "deviceInfoServiceImpl")
  private DeviceInfoService deviceInfoService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @Resource(name = "faultCodeServiceImpl")
  private FaultCodeService faultCodeService;
  
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody VehiclePageResponse list(@RequestBody VehicleRequest vehicleRequest) {

    VehiclePageResponse response = new VehiclePageResponse();
    Long userId = vehicleRequest.getUserId();
    String token = vehicleRequest.getToken();
    // 验证登录token
    String userToken = tenantAccountService.getTenantUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    if (LogUtil.isDebugEnabled(VehicleController.class)) {
      LogUtil
          .debug(
              VehicleController.class,
              "getVehicleList",
              "search Vehicle for vehicle with MobileNum: %s,deviceNo: %s,plate: %s,OnlineStatus: %s,tenantId: %s, userId: %s ,pageNumber %s ,pageSize %s",
              vehicleRequest.getMobileNum(), vehicleRequest.getDeviceNo(),
              vehicleRequest.getPlate(), vehicleRequest.getOnlineStatus(),
              vehicleRequest.getTenantId(), vehicleRequest.getUserId(),
              vehicleRequest.getPageNumber(), vehicleRequest.getPageSize());
    }
    // 查询车辆分页数据
    Page<Vehicle> vehiclePage = vehicleService.findPageForList(vehicleRequest);
    String[] properties =
        {"id", "plate", "device.deviceNo", "endUser.mobileNum", "vehicleBrandDetail.name",
            "faultCode", "wainingInfo", "isOnline", "vehicleFullBrand", "obdStatusTime",
            "createDate"};
    List<Map<String, Object>> resultMaps =
        FieldFilterUtils.filterCollectionMap(properties, vehiclePage.getContent());
    response.setMsg(resultMaps);

    PageResponse pageResponse = new PageResponse();
    pageResponse.setPageNumber(vehiclePage.getPageNumber());
    pageResponse.setPageSize(vehiclePage.getPageSize());
    pageResponse.setTotal((int) vehiclePage.getTotal());
    response.setPage(pageResponse);
    response.setAllCount(vehicleService.getCountByOnlineStatus(vehicleRequest, OnlineStatus.ALL));
    response.setOnlineCount(vehicleService.getCountByOnlineStatus(vehicleRequest,
        OnlineStatus.ONLINE));
    response.setOfflineCount(vehicleService.getCountByOnlineStatus(vehicleRequest,
        OnlineStatus.OFFLINE));
    String newtoken = TokenGenerator.generateToken(vehicleRequest.getToken());
    tenantAccountService.createTenantUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  @RequestMapping(value = "/getVehicleDetails", method = RequestMethod.POST)
  public @ResponseBody ResponseOne<Map<String, Object>> getVehicleDetails(
      @RequestBody VehicleRequest request) {
    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
    Long userId = request.getUserId();
    String token = request.getToken();
    // 验证登录token
    String userToken = tenantAccountService.getTenantUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }
    Vehicle vehicle = vehicleService.find(request.getVehicleId());
    String[] properties =
        {"id", "plate", "endUser.userName", "vehicleBrandDetail.name", "vehicleFullBrand"};
    Map<String, Object> map = FieldFilterUtils.filterEntityMap(properties, vehicle);
    try {
      
      map.put("deviceNo", vehicle.getDeviceNo());
      String vehicleTodayDetailsUrl =
          setting.getObdServerUrl() + "/appVehicleData/vehicleTodayDetails.jhtml?deviceId="
              + vehicle.getDeviceNo();
      String vehicleTodayDetailsRes = ApiUtils.post(vehicleTodayDetailsUrl);
      if (LogUtil.isDebugEnabled(getClass())) {
        LogUtil.debug(getClass(), "vehicleTodayDetailsUrl", "url: %s", vehicleTodayDetailsRes);
        LogUtil.debug(getClass(), "api vehicleTodayDetailsRes", "response data: %s", vehicleTodayDetailsRes);
      }
      Map<String, Object> vehicleTodayDetailsMap = ToolsUtils.convertStrToJson(vehicleTodayDetailsRes);
      Map<String, Object> vehicleTodayDetailsMsg = (Map<String, Object>) vehicleTodayDetailsMap.get("msg");
      if (vehicleTodayDetailsMsg != null) {
        map.put("acc", vehicleTodayDetailsMsg.get("acc")); // ACC状态（启动、熄火）
        map.put("lon", vehicleTodayDetailsMsg.get("lon"));// 经度
        map.put("lat", vehicleTodayDetailsMsg.get("lat"));// 纬度
        map.put("azimuth", vehicleTodayDetailsMsg.get("azimuth"));// 方位角
        map.put("averageOil", vehicleTodayDetailsMsg.get("averageOil"));// 平均油耗
        map.put("createtime", vehicleTodayDetailsMsg.get("createtime"));// 最后上传时间
        map.put("fuelConsumption", vehicleTodayDetailsMsg.get("fuelConsumption"));
        map.put("mileAge", vehicleTodayDetailsMsg.get("mileAge"));
        map.put("runningTime", vehicleTodayDetailsMsg.get("runningTime"));
        map.put("averageSpeed", vehicleTodayDetailsMsg.get("averageSpeed"));
      } else {
        map.put("acc", null); // ACC状态（启动、熄火）
        map.put("lon", null);// 经度
        map.put("lat", null);// 纬度
        map.put("azimuth", null);// 方位角
        map.put("averageOil", null);// 平均油耗
        map.put("createtime", null);// 最后上传时间
        map.put("fuelConsumption", null); // 当日油耗
        map.put("mileAge", null);// 当日里程
        map.put("runningTime", null);// 当日运行时间
        map.put("averageSpeed", null);// 平均速度
      }
    } catch (Exception e) {
      LogUtil.error(getClass(), "getVehicleTodayDetails", "Exception: %s", e.getMessage());
    }
    response.setMsg(map);
    String newtoken = TokenGenerator.generateToken(request.getToken());
    tenantAccountService.createTenantUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  /**
   * 未绑定车辆分页接口
   * 
   * @param request
   * @return
   */
  @RequestMapping(value = "/findUnbindVehiclePage", method = RequestMethod.POST)
  public @ResponseBody ResponseMultiple<Map<String, Object>> findUnbindVehiclePage(
      @RequestBody VehicleRequest request) {
    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Long userId = request.getUserId();
    String token = request.getToken();
    Long tenantId = request.getTenantId();

    if (LogUtil.isDebugEnabled(getClass())) {
      LogUtil.debug(getClass(), "findUnbindVehiclePage", "request param: %s", request.toString());
    }

    // 参数有效性验证
    if (userId == null || tenantId == null) {
      response.setCode(CommonAttributes.MISSING_REQUIRE_PARAM);
      response.setDesc(Message.error("csh.common.param.invalid").getContent());
      return response;
    }
    String userToken = tenantAccountService.getTenantUserToken(userId);
    // token验证
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    // 查询车辆分页数据
    Page<Vehicle> vehiclePage = vehicleService.findUnbindVehiclePage(request);
    String[] properties =
        {"id", "plate", "endUser.realName", "endUser.mobileNum", "vehicleNo",
            "vehicleBrandDetail.name", "vehicleFullBrand", "createDate"};
    List<Map<String, Object>> resultMaps =
        FieldFilterUtils.filterCollectionMap(properties, vehiclePage.getContent());
    response.setMsg(resultMaps);

    // 分页信息
    PageResponse pageResponse = new PageResponse();
    pageResponse.setPageNumber(vehiclePage.getPageNumber());
    pageResponse.setPageSize(vehiclePage.getPageSize());
    pageResponse.setTotal((int) vehiclePage.getTotal());
    response.setPage(pageResponse);

    String newToken = TokenGenerator.generateToken(token);
    response.setToken(newToken);
    response.setCode(CommonAttributes.SUCCESS);
    response.setDesc(SUCCESS_MESSAGE.getContent());

    if (LogUtil.isDebugEnabled(getClass())) {
      LogUtil
          .debug(getClass(), "findUnbindVehiclePage", "response success:tenantId = %s", tenantId);
    }

    return response;
  }

  @RequestMapping(value = "/findAllVehicleGps", method = RequestMethod.POST)
  public @ResponseBody ResponseMultiple<Map<String, Object>> findAllVehicleGps(
      @RequestBody VehicleRequest request) {
    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Long userId = request.getUserId();
    String token = request.getToken();
    Long tenantId = request.getTenantId();

    if (LogUtil.isDebugEnabled(getClass())) {
      LogUtil.debug(getClass(), "findUnbindVehiclePage", "request param: %s", request.toString());
    }

    // 参数有效性验证
    if (userId == null || tenantId == null) {
      response.setCode(CommonAttributes.MISSING_REQUIRE_PARAM);
      response.setDesc(Message.error("csh.login.param.invalid").getContent());
      return response;
    }
    String userToken = tenantAccountService.getTenantUserToken(userId);
    // token验证
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    // 查询车辆分页数据
    List<com.csh.framework.filter.Filter> filters =
        new ArrayList<com.csh.framework.filter.Filter>();
    com.csh.framework.filter.Filter exceptFilter =
        new com.csh.framework.filter.Filter("plate", Operator.ne, "0000000");
    filters.add(exceptFilter);

    List<Vehicle> vehicleList =
        prepareVehicleList(vehicleService.findList(null, filters, null, true, null,
            request.getTenantId()));
    List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
    for (Vehicle vehicle : vehicleList) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("lon", vehicle.getLon());
      map.put("lat", vehicle.getLat());
      map.put("vehicleId", vehicle.getId());
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


    String[] properties = {"id", "plate", "lat", "lon", "deviceNo", "accStatus", "azimuth"};
    List<Map<String, Object>> resultMaps =
        FieldFilterUtils.filterCollectionMap(properties, vehicleList);
    response.setMsg(resultMaps);

    String newToken = TokenGenerator.generateToken(token);
    response.setToken(newToken);
    response.setCode(CommonAttributes.SUCCESS);
    response.setDesc(SUCCESS_MESSAGE.getContent());

    if (LogUtil.isDebugEnabled(getClass())) {
      LogUtil.debug(getClass(), "findUnbindVehiclePage", "response:success");
    }

    return response;
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
          ApiUtils.postJson(setting.getObdServerUrl()
              + "/tenantVehicleData/vehicleOnlineStatus.jhtml", "UTF-8", "UTF-8", params);
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
              vehicle.setAccStatus(vehicleStatus.getAccStatus());
              vehicle.setAzimuth(vehicleStatus.getAzimuth());
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

            }
          }
        }
      }
    }

    catch (Exception e) {
      e.printStackTrace();
    }
    return vehicleList;
  }

  /**
   * 获取故障码信息
   *
   * @return
   */
  @RequestMapping(value = "/getFaultCode", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> getFaultCodeDetails(
      @RequestBody VehicleRequest request) {
    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Long userId = request.getUserId();
    String token = request.getToken();
    String faultCode = request.getFaultCode();
    // 验证登录token
    String userToken = tenantAccountService.getTenantUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }
    if (LogUtil.isDebugEnabled(VehicleController.class)) {
      LogUtil.debug(VehicleController.class, "getFaultCodeDetails", "request param: %s", request.toString());
    }
    List<Filter> filters = new ArrayList<Filter>();
    filters.add(Filter.eq("code", faultCode));
    List<FaultCode> codes = faultCodeService.findList(null, filters, null);
    
    List<Map<String, Object>> resultMaps = new ArrayList<Map<String,Object>>();
    if (!CollectionUtils.isEmpty(codes)) {
      String[] properties = {"id","rangeScope", "code", "defineCh", "defineEn", "scope", "info"};
      resultMaps = FieldFilterUtils.filterCollectionMap(properties, codes);
    }
    response.setMsg(resultMaps);
    String newtoken = TokenGenerator.generateToken(request.getToken());
    tenantAccountService.createTenantUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
    
  }
}
