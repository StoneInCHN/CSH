package com.csh.controller.intf;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.CommonAttributes;
import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.EndUser;
import com.csh.entity.SmsToken;
import com.csh.entity.TenantInfo;
import com.csh.entity.Vehicle;
import com.csh.entity.commonenum.CommonEnum.SmsTokenType;
import com.csh.json.base.ResponseOne;
import com.csh.json.request.UserRegRequest;
import com.csh.service.EndUserService;
import com.csh.service.SmsTokenService;
import com.csh.service.TenantInfoService;
import com.csh.service.VehicleService;
import com.csh.utils.KeyGenerator;
import com.csh.utils.RSAHelper;
import com.csh.utils.TokenGenerator;



@Controller("endUserIntfController")
@RequestMapping("/endUserIntf")
public class EndUserIntfController extends MobileBaseController {

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "smsTokenServiceImpl")
  private SmsTokenService smsTokenService;

  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;

  @Resource(name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;



  /**
   * 终端用户注册(对外提供)
   * 
   * @param request
   * @param userReg
   * @return
   */
  @RequestMapping(value = "/reg", method = RequestMethod.POST)
  public @ResponseBody ResponseOne<Map<String, Object>> reg(@RequestBody UserRegRequest userReg) {
    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
    String serverPrivateKey = setting.getServerPrivateKey();
    String userName = userReg.getUserName();
    String smsToken = userReg.getSmsToken();
    String password = userReg.getPassword();
    String password_confirm = userReg.getPassword_confirm();
    String orgCode = userReg.getOrgCode();

    // 手机号码格式验证
    if (StringUtils.isEmpty(userName) || !isMobileNumber(userName)) {
      response.setCode(CommonAttributes.FAIL_REG);
      response.setDesc(Message.error("csh.mobile.invaliable").getContent());
      return response;
    }
    // 账号重复验证
    EndUser user = endUserService.findByUserName(userName);
    if (user != null) {
      response.setCode(CommonAttributes.FAIL_REG);
      response.setDesc(Message.error("csh.mobile.used").getContent());
      return response;

    }
    if (password != null || password_confirm != null) {
      if (!password.equals(password_confirm)) {
        response.setCode(CommonAttributes.FAIL_REG);
        response.setDesc(Message.error("csh.pwd.no.same").getContent());
        return response;
      }
      try {
        password = KeyGenerator.decrypt(password, RSAHelper.getPrivateKey(serverPrivateKey));
      } catch (Exception e) {
        e.printStackTrace();
      }
      if (password.length() < setting.getPasswordMinlength()
          || password.length() > setting.getPasswordMaxlength()) {
        response.setCode(CommonAttributes.FAIL_REG);
        response.setDesc(Message.error("csh.nameorpwd.invaliable").getContent());
        return response;
      }

      if (LogUtil.isDebugEnabled(EndUserIntfController.class)) {
        LogUtil.debug(EndUserIntfController.class, "reg", "EndUser Reg. UserName: %s", userName);
      }
      EndUser regUser = endUserService.userReg(userName, password);
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("isGetCoupon", regUser.getIsGetCoupon());
      response.setMsg(map);

      /**
       * 添加虚拟车辆
       */
      Vehicle vehicle = new Vehicle();
      vehicle.setPlate("0000000");// 车牌号为7个0表示默认车辆，不显示
      vehicle.setEndUser(regUser);
      TenantInfo tenantInfo = tenantInfoService.findTenantWithOrgCode(orgCode);
      vehicle.setTenantID(tenantInfo.getId());
      vehicleService.save(vehicle);

      response.setCode(CommonAttributes.SUCCESS);
      response.setDesc(regUser.getId().toString());
      String token = TokenGenerator.generateToken();
      endUserService.createEndUserToken(token, regUser.getId());
      response.setToken(token);
      return response;
    } else {
      // 短信验证码验证
      SmsToken userSmsToken = smsTokenService.findByUserMobile(userName, SmsTokenType.REG);
      if (userSmsToken == null) {
        response.setCode(CommonAttributes.FAIL_REG);
        response.setDesc(Message.error("csh.mobile.invaliable").getContent());
        return response;
      } else {
        String timeOutToken = userSmsToken.getTimeoutToken();
        String smsCode = userSmsToken.getSmsToken();
        if (timeOutToken != null
            && !TokenGenerator.tokenTimeOut(timeOutToken, setting.getSmsCodeTimeOut())) {
          if (!smsCode.equals(smsToken)) {
            response.setCode(CommonAttributes.FAIL_REG);
            response.setDesc(Message.error("csh.sms.token.error").getContent());
            return response;
          } else {
            smsTokenService.delete(userSmsToken);
            response.setCode(CommonAttributes.SUCCESS);
            return response;
          }
        } else {
          response.setCode(CommonAttributes.FAIL_REG);
          response.setDesc(Message.error("csh.sms.token.timeout").getContent());
          return response;
        }
      }
    }
  }

}
