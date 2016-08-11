package com.csh.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.json.base.ResponseOne;
import com.csh.json.request.DeviceRequest;
import com.csh.service.DeviceInfoService;
import com.csh.service.EndUserService;
import com.csh.service.FaultCodeService;
import com.csh.service.VehicleOilService;
import com.csh.utils.ApiUtils;
import com.csh.utils.LatLonUtil;
import com.csh.utils.TokenGenerator;
import com.csh.utils.ToolsUtils;
import com.csh.utils.VehicleUtil;

/**
 * Control odb设备
 * 
 * @author Andrea
 *
 */
@Controller("ObdController")
@RequestMapping("/obd")
public class ObdController extends MobileBaseController {

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "deviceInfoServiceImpl")
  private DeviceInfoService deviceInfoService;

  @Resource(name = "vehicleOilServiceImpl")
  private VehicleOilService vehicleOilService;

  @Resource(name = "faultCodeServiceImpl")
  private FaultCodeService faultCodeService;


  /**
   * 车辆动态
   * 
   * @return
   */
  @RequestMapping(value = "/vehicleTrends", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseOne<Map<String, Object>> vehicleTrends(
      @RequestBody DeviceRequest deviceReq) {

    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();

    Long userId = deviceReq.getUserId();
    String token = deviceReq.getToken();
    String deviceNo = deviceReq.getDeviceNo();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    try {
      String url =
          setting.getObdServerUrl() + "/appVehicleData/vehicleTrends.jhtml?deviceId=" + deviceNo;
      String res = ApiUtils.post(url);
      Map<String, Object> map = ToolsUtils.convertStrToJson(res);
      Map<String, Object> msg = (Map<String, Object>) map.get("msg");
      if (LogUtil.isDebugEnabled(ObdController.class)) {
        LogUtil.debug(ObdController.class, "vehicleTrends",
            "Receive the msg from obd. deviceNo: %s, Msg: %s", deviceNo, msg);
      }
      if (msg != null) {
        Boolean flag = (Boolean) msg.get("isNeedToAddInitMileAge");
        if (flag) {
          DeviceInfo deviceInfo = deviceInfoService.getDeviceByDeviceNo(deviceNo);
          Double mileAge = (Double) msg.get("mileAge");
          if (deviceInfo.getVehicle() != null && deviceInfo.getVehicle().getDriveMileage() != null) {
            msg.put("mileAge", mileAge + deviceInfo.getVehicle().getDriveMileage());
          }

        }
        Map<String, Object> xy =
            LatLonUtil.convertCoordinate(msg.get("lon").toString(), msg.get("lat").toString());
        msg.putAll(xy);
      }

      response.setMsg(msg);
    } catch (Exception e) {
      e.printStackTrace();
    }
    String newtoken = TokenGenerator.generateToken(deviceReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  /**
   * 一键检测
   * 
   * @return
   */
  @RequestMapping(value = "/oneKeyDetection", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseOne<Map<String, Object>> oneKeyDetection(
      @RequestBody DeviceRequest deviceReq) {

    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();

    Long userId = deviceReq.getUserId();
    String token = deviceReq.getToken();
    String deviceNo = deviceReq.getDeviceNo();
    String searchDate = deviceReq.getSearchDate();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    try {
      String url =
          setting.getObdServerUrl() + "/appVehicleData/oneKeyDetection.jhtml?deviceId=" + deviceNo
              + "&date=" + searchDate;
      String res = ApiUtils.post(url);
      Map<String, Object> map = ToolsUtils.convertStrToJson(res);
      Map<String, Object> msg = (Map<String, Object>) map.get("msg");
      if (LogUtil.isDebugEnabled(ObdController.class)) {
        LogUtil.debug(ObdController.class, "oneKeyDetection",
            "Receive the msg from obd. deviceNo: %s,Date: %s, Msg: %s", deviceNo, searchDate, msg);
      }
      if (msg != null) {
        Integer score =
            VehicleUtil.getScore((Integer) msg.get("rapidlyspeedupcount"),
                (Integer) msg.get("emergencybrakecount"), (Integer) msg.get("suddenturncount"),
                (Integer) msg.get("fatiguedrivingcount"), (Double) msg.get("mileAge"));
        if (score != -1) {
          msg.put("drivingScore", score);
        } else {
          msg.put("drivingScore", "-");
        }
        Boolean flag = (Boolean) msg.get("isNeedToAddInitMileAge");
        if (flag) {
          DeviceInfo deviceInfo = deviceInfoService.getDeviceByDeviceNo(deviceNo);
          Double mileAge = (Double) msg.get("totalMileAge");
          if (deviceInfo.getVehicle() != null && deviceInfo.getVehicle().getDriveMileage() != null) {
            msg.put("totalMileAge", mileAge + deviceInfo.getVehicle().getDriveMileage());
          }
        }
        BigDecimal cost = new BigDecimal(0);
        DeviceInfo deviceInfo = deviceInfoService.getDeviceByDeviceNo(deviceNo);
        cost =
            vehicleOilService.calOilCost(new BigDecimal((Double) msg.get("fuelConsumption")),
                deviceInfo);
        msg.put("cost", cost);
      }
      response.setMsg(msg);
    } catch (Exception e) {
      e.printStackTrace();
    }

    String newtoken = TokenGenerator.generateToken(deviceReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  /**
   * 车辆详情
   * 
   * @return
   */
  @RequestMapping(value = "/vehicleScan", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseOne<Map<String, Object>> vehicleScan(
      @RequestBody DeviceRequest deviceReq) {

    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();

    Long userId = deviceReq.getUserId();
    String token = deviceReq.getToken();
    String deviceNo = deviceReq.getDeviceNo();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    try {
      String url =
          setting.getObdServerUrl() + "/appVehicleData/vehicleScan.jhtml?deviceId=" + deviceNo;
      String res = ApiUtils.post(url);
      Map<String, Object> map = ToolsUtils.convertStrToJson(res);
      Map<String, Object> msg = (Map<String, Object>) map.get("msg");
      if (LogUtil.isDebugEnabled(ObdController.class)) {
        LogUtil.debug(ObdController.class, "vehicleScan",
            "Receive the msg from obd. deviceNo: %s,Msg: %s", deviceNo, msg);
      }
      if (msg != null) {
        Boolean flag = (Boolean) msg.get("isNeedToAddInitMileAge");
        if (flag) {
          DeviceInfo deviceInfo = deviceInfoService.getDeviceByDeviceNo(deviceNo);
          Double mileAge = (Double) msg.get("totalMileAge");
          if (deviceInfo.getVehicle() != null && deviceInfo.getVehicle().getDriveMileage() != null) {
            msg.put("totalMileAge", mileAge + deviceInfo.getVehicle().getDriveMileage());
          }

        }
      }
      response.setMsg(msg);
    } catch (Exception e) {
      e.printStackTrace();
    }

    String newtoken = TokenGenerator.generateToken(deviceReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 获取故障码信息
   * 
   * @return
   */
  @RequestMapping(value = "/getObdCode", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseOne<Map<String, Object>> getObdCode(
      @RequestBody DeviceRequest deviceReq) {

    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();

    Long userId = deviceReq.getUserId();
    String token = deviceReq.getToken();
    String faultCode = deviceReq.getFaultCode();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter("codeKey", Operator.eq, faultCode);
    filters.add(filter);
    List<FaultCode> codes = faultCodeService.findList(null, filters, null);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("codeKey", faultCode);
    if (!CollectionUtils.isEmpty(codes)) {
      map.put("codeDesc", codes.get(0).getCodeValue());
    } else {
      map.put("codeDesc", Message.success("csh.obd.code.noexist").getContent());
    }
    response.setMsg(map);
    String newtoken = TokenGenerator.generateToken(deviceReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }
}
