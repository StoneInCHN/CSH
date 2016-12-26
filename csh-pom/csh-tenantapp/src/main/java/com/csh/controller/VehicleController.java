package com.csh.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.csh.framework.paging.Pageable;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.request.VehicleRequest;
import com.csh.service.DeviceInfoService;
import com.csh.service.EndUserService;
import com.csh.service.TenantAccountService;
import com.csh.service.TenantInfoService;
import com.csh.service.VehicleService;
import com.csh.utils.TokenGenerator;


@Controller("vehicleController")
@RequestMapping("/vehicle")
public class VehicleController extends MobileBaseController {

  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;

  @Resource(name = "deviceInfoServiceImpl")
  private DeviceInfoService deviceInfoService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> list(HttpServletRequest requesthttp,
      @RequestBody VehicleRequest vehicleRequest) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
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
              "search Vsehicle for vehicle with MobileNum: %s,deviceNo: %s,plate: %s,isOnline: %s,userName: %s, userId: %s",
              vehicleRequest.getMobileNum(), vehicleRequest.getDeviceNo(),
              vehicleRequest.getPlate(), vehicleRequest.isOnline(), vehicleRequest.getUserName(),
              vehicleRequest.getUserId());
    }
    Pageable pageable = new Pageable(vehicleRequest.getPageNumber(), vehicleRequest.getPageSize());



    String newtoken = TokenGenerator.generateToken(vehicleRequest.getToken());
    tenantAccountService.createTenantUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

}
