package com.csh.controller;

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
import com.csh.controller.base.MobileBaseController;
import com.csh.json.base.ResponseOne;
import com.csh.json.request.DeviceRequest;
import com.csh.service.EndUserService;
import com.csh.utils.ApiUtils;
import com.csh.utils.TokenGenerator;
import com.csh.utils.ToolsUtils;

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
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    try {
      String url =
          setting.getObdServerUrl() + "/appVehicleData/vehicleTrends.jhtml?deviceId="
              + deviceReq.getDeviceId();
      String res = ApiUtils.post(url);
      Map<String, Object> map = ToolsUtils.convertStrToJson(res);
      response.setMsg((Map<String, Object>) map.get("msg"));
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
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    try {
      String url =
          setting.getObdServerUrl() + "/appVehicleData/oneKeyDetection.jhtml?deviceId="
              + deviceReq.getDeviceId() + "&date=" + deviceReq.getDate();
      String res = ApiUtils.post(url);
      Map<String, Object> map = ToolsUtils.convertStrToJson(res);
      response.setMsg((Map<String, Object>) map.get("msg"));
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
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    try {
      String url =
          setting.getObdServerUrl() + "/appVehicleData/vehicleScan.jhtml?deviceId="
              + deviceReq.getDeviceId();
      String res = ApiUtils.post(url);
      Map<String, Object> map = ToolsUtils.convertStrToJson(res);
      response.setMsg((Map<String, Object>) map.get("msg"));
    } catch (Exception e) {
      e.printStackTrace();
    }

    String newtoken = TokenGenerator.generateToken(deviceReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


}
