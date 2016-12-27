package com.csh.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.aspect.UserValidCheck;
import com.csh.beans.CommonAttributes;
import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.Vehicle;
import com.csh.entity.commonenum.CommonEnum.OnlineStatus;
import com.csh.framework.paging.Page;
import com.csh.json.base.PageResponse;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.base.ResponseOne;
import com.csh.json.request.VehicleRequest;
import com.csh.json.response.VehiclePageResponse;
import com.csh.service.DeviceInfoService;
import com.csh.service.TenantAccountService;
import com.csh.service.TenantInfoService;
import com.csh.service.VehicleService;
import com.csh.utils.ApiUtils;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.TokenGenerator;
import com.csh.utils.ToolsUtils;

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
              "search Vsehicle for vehicle with MobileNum: %s,deviceNo: %s,plate: %s,OnlineStatus: %s,userName: %s, userId: %s",
              vehicleRequest.getMobileNum(), vehicleRequest.getDeviceNo(),
              vehicleRequest.getPlate(), vehicleRequest.getOnlineStatus(),
              vehicleRequest.getUserName(), vehicleRequest.getUserId());
    }
    // 查询车辆分页数据
    Page<Vehicle> vehiclePage = vehicleService.findPageForList(vehicleRequest);
    String[] properties =
        {"id", "plate", "endUser.mobileNum", "vehicleBrandDetail.name", "faultCode", "wainingInfo",
            "isOnline", "vehicleFullBrand", "obdStatusTime", "createDate"};
    List<Map<String, Object>> resultMaps =
        FieldFilterUtils.filterCollectionMap(properties, vehiclePage.getContent());
    response.setMsg(resultMaps);

    PageResponse pageResponse = new PageResponse();
    pageResponse.setPageNumber(vehiclePage.getPageNumber());
    pageResponse.setPageSize(vehiclePage.getPageSize());
    pageResponse.setTotal(vehiclePage.getTotalPages());
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
      // 车辆动态接口
      vehicle.setDeviceNo("8856003537");
      map.put("deviceNo", vehicle.getDeviceNo());
      String vehicleTrendsUrl =
          setting.getObdServerUrl() + "/appVehicleData/vehicleTrends.jhtml?deviceId="
              + vehicle.getDeviceNo();
      String vehicleTrendsRes = ApiUtils.post(vehicleTrendsUrl);
      Map<String, Object> vehicleTrendsMap = ToolsUtils.convertStrToJson(vehicleTrendsRes);
      Map<String, Object> vehicleTrendsMsg = (Map<String, Object>) vehicleTrendsMap.get("msg");
      if (vehicleTrendsMsg != null) {
        map.put("acc", vehicleTrendsMsg.get("acc")); // ACC状态（启动、熄火）
        map.put("lon", vehicleTrendsMsg.get("lon"));// 经度
        map.put("lat", vehicleTrendsMsg.get("lat"));// 纬度
        map.put("azimuth", vehicleTrendsMsg.get("azimuth"));// 方位角
        map.put("createtime", vehicleTrendsMsg.get("createtime"));// 方位角
      } else {
        map.put("acc", null); // ACC状态（启动、熄火）
        map.put("lon", null);// 经度
        map.put("lat", null);// 纬度
        map.put("azimuth", null);// 方位角
        map.put("createtime", null);// 方位角
      }

      // 一键检测接口
      String oneKeyDetectionUrl =
          setting.getObdServerUrl() + "/appVehicleData/oneKeyDetection.jhtml?deviceId="
              + vehicle.getDeviceNo() + "&date=" + "2016-12-28";
      String oneKeyDetectionRes = ApiUtils.post(oneKeyDetectionUrl);
      Map<String, Object> oneKeyDetectionMap = ToolsUtils.convertStrToJson(oneKeyDetectionRes);
      Map<String, Object> oneKeyDetectionMsg = (Map<String, Object>) vehicleTrendsMap.get("msg");
      if (oneKeyDetectionMsg != null) {
        map.put("fuelConsumption", vehicleTrendsMsg.get("fuelConsumption")); // 当日油耗
        map.put("mileAge", vehicleTrendsMsg.get("mileAge"));// 当日里程
        map.put("runningTime", vehicleTrendsMsg.get("runningTime"));// 当日运行时间
        map.put("averageSpeed", vehicleTrendsMsg.get("averageSpeed"));// 平均速度
      } else {
        map.put("fuelConsumption", null); // 当日油耗
        map.put("mileAge", null);// 当日里程
        map.put("runningTime", null);// 当日运行时间
        map.put("averageSpeed", null);// 平均速度

      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    response.setMsg(map);
    String newtoken = TokenGenerator.generateToken(request.getToken());
    tenantAccountService.createTenantUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

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
    Page<Vehicle> vehiclePage = vehicleService.findPageByRequest(request);
    String[] properties =
        {"id", "plate", "endUser.realName", "endUser.mobileNum", "vehicleNo", "vehicleBrandDetail",
            "vehicleFullBrand", "createDate"};
    List<Map<String, Object>> resultMaps =
        FieldFilterUtils.filterCollectionMap(properties, vehiclePage.getContent());
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


}
