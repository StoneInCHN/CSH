package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.CommonAttributes;
import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.CarService;
import com.csh.entity.CarServiceRecord;
import com.csh.entity.EndUser;
import com.csh.json.base.BaseResponse;
import com.csh.json.request.CarServiceRequest;
import com.csh.service.CarServiceRecordService;
import com.csh.service.CarServiceService;
import com.csh.service.EndUserService;
import com.csh.utils.TokenGenerator;


@Controller("CarServiceController")
@RequestMapping("/carService")
public class CarServiceController extends MobileBaseController {


  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "carServiceRecordServiceImpl")
  private CarServiceRecordService carServiceRecordService;
  
  @Resource(name = "carServiceServiceImpl")
  private CarServiceService carServiceService;


  /**
   * 用户购买汽车服务
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/buyService", method = RequestMethod.POST)
  public @ResponseBody BaseResponse buyService(@RequestBody CarServiceRequest serviceReq) {

    BaseResponse response = new BaseResponse();
    Long userId = serviceReq.getUserId();
    String token = serviceReq.getToken();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    CarService carService = carServiceService.find(serviceReq.getServiceId());
    
    CarServiceRecord carServiceRecord = new CarServiceRecord();
    carServiceRecord.setCarService(carService);
    carServiceRecord.setEndUser(endUser);
    carServiceRecord.setChargeStatus(serviceReq.getChargeStatus());
    carServiceRecord.setPaymentType(serviceReq.getPaymentType());
    carServiceRecord.setPrice(serviceReq.getPrice());
    carServiceRecordService.save(carServiceRecord);
    if (LogUtil.isDebugEnabled(CarServiceController.class)) {
        LogUtil.debug(CarServiceController.class, "Save",
            "User buy Car Service. UserName: %s, Tenant: %s, Service: %s, price: %s, paymentType: %s, chargeStatus: %s", 
            endUser.getUserName(),carService.getTenantInfo().getTenantName(),carService.getServiceName(),carServiceRecord.getPrice(),carServiceRecord.getPaymentType(),carServiceRecord.getChargeStatus());
    }
    
    String newtoken = TokenGenerator.generateToken(serviceReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  
}
