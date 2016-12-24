package com.csh.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
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
import com.csh.entity.TenantAccount;
import com.csh.entity.TenantInfo;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;
import com.csh.json.base.BaseRequest;
import com.csh.json.base.BaseResponse;
import com.csh.json.base.ResponseOne;
import com.csh.json.request.TenantInfoRequest;
import com.csh.json.request.TenantLoginRequest;
import com.csh.service.EndUserService;
import com.csh.service.TenantAccountService;
import com.csh.service.TenantInfoService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.KeyGenerator;
import com.csh.utils.RSAHelper;
import com.csh.utils.TokenGenerator;


/**
 * 租户用户
 * 
 * @author sujinxuan
 *
 */
@Controller("tenantAccountController")
@RequestMapping("/tenantAccount")
public class TenantAccountController extends MobileBaseController {

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;


  /**
   * 获取公匙
   * 
   * @param request
   * @return
   */
  @RequestMapping(value = "/rsa", method = RequestMethod.POST)
  public @ResponseBody BaseResponse rsa(HttpServletRequest request) {
    BaseResponse response = new BaseResponse();
    String key = setting.getServerPublicKey();
    response.setCode(CommonAttributes.SUCCESS);
    response.setDesc(key);
    return response;
  }

  /**
   * 用户注销
   * 
   * @return
   */
  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse logout(@RequestBody BaseRequest req) {
    BaseResponse response = new BaseResponse();
    Long userId = req.getUserId();
    String token = req.getToken();

    String userToken = tenantAccountService.getTenantUserToken(userId);
    // token验证
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    // TenantAccount tenantAccount = tenantAccountService.find(userId);
    tenantAccountService.deleteTenantUserToken(userId);

    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 用户登录
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public @ResponseBody ResponseOne<Map<String, Object>> login(HttpServletRequest requesthttp,
      @RequestBody TenantLoginRequest userLoginReq) {
    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
    String serverPrivateKey = setting.getServerPrivateKey();
    String userName = userLoginReq.getUserName();
    String password = userLoginReq.getPassword();
    String orgCode = userLoginReq.getOrgCode();

    if (StringUtils.isEmpty(orgCode) || StringUtils.isEmpty(userName)
        || StringUtils.isEmpty(password)) {
      response.setCode(CommonAttributes.FAIL_LOGIN);
      response.setDesc(Message.error("csh.login.param.invalid").getContent());
      return response;
    }
    if (LogUtil.isDebugEnabled(TenantAccountController.class)) {
      LogUtil.debug(TenantAccountController.class, "login",
          "Fetching TenantUser from database with UserName: %s", userName);
    }
    TenantInfo tenantInfo = tenantAccountService.findTenantWithOrgCode(orgCode);

    if (tenantInfo == null) {
      response.setCode(CommonAttributes.FAIL_LOGIN);
      response.setDesc(Message.error("csh.tenant.noexist").getContent());
      return response;
    }
    if (AccountStatus.LOCKED.equals(tenantInfo.getAccountStatus())
        || AccountStatus.DELETE.equals(tenantInfo.getAccountStatus())) {
      response.setCode(CommonAttributes.FAIL_LOGIN);
      response.setDesc(Message.error("csh.tenant.locked").getContent());
      return response;
    }
    TenantAccount tenantAccount = tenantAccountService.findByNameAndOrgCode(userName, orgCode);
    if (tenantAccount.getAccoutStatus().equals(AccountStatus.LOCKED)
        || tenantAccount.getAccoutStatus().equals(AccountStatus.DELETE)) {
      response.setCode(CommonAttributes.FAIL_LOGIN);
      response.setDesc(Message.error("csh.tenant.account.locked").getContent());
      return response;
    }
    try {
      password = KeyGenerator.decrypt(password, RSAHelper.getPrivateKey(serverPrivateKey));
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (!DigestUtils.md5Hex(password).equals(tenantAccount.getPassword())) {
      response.setCode(CommonAttributes.FAIL_LOGIN);
      response.setDesc(Message.error("csh.nameorpwd.error").getContent());
      return response;
    }

    tenantAccount.setLoginIp(requesthttp.getRemoteAddr());
    tenantAccount.setLoginDate(new Date());
    tenantAccountService.update(tenantAccount);
    if (LogUtil.isDebugEnabled(TenantAccountController.class)) {
      LogUtil.debug(TenantAccountController.class, "login",
          "tenant user login success with userName: %s", userName);
    }

    response.setCode(CommonAttributes.SUCCESS);
    response.setDesc(tenantAccount.getId().toString());
    String[] properties =
        {"id", "tenantName", "systemName", "orgCode", "contactPhone", "address", "email",
            "businessTime", "description", "area.fullName", "ownerName", "accountStatus",
            "versionConfig.versionName", "remark"};
    Map<String, Object> map = FieldFilterUtils.filterEntityMap(properties, tenantInfo);
    response.setMsg(map);
    String token = TokenGenerator.generateToken();
    tenantAccountService.createTenantUserToken(token, tenantAccount.getId());
    response.setToken(token);
    return response;
  }

  /**
   * 修改密码
   *
   * @param request
   * @param resetPwdReq
   * @return
   */
  @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse updatePwd(@RequestBody TenantLoginRequest req) {
    BaseResponse response = new BaseResponse();
    Long userId = req.getUserId();
    String token = req.getToken();
    String userToken = tenantAccountService.getTenantUserToken(userId);
    // token验证
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }
    String serverPrivateKey = setting.getServerPrivateKey();
    String password_confirm = req.getPassword_confirm();
    String password = req.getPassword();
    String password_new = req.getPassword_new();


    if (password == null || password_confirm == null || password_new == null) {
      response.setCode(CommonAttributes.FAIL_UPDATE_PWD);
      response.setDesc(Message.error("csh.pwd.param.invalid").getContent());
      return response;
    }

    if (!password_new.equals(password_confirm)) {
      response.setCode(CommonAttributes.FAIL_UPDATE_PWD);
      response.setDesc(Message.error("csh.pwd.no.same").getContent());
      return response;
    }
    TenantAccount tenantAccount = tenantAccountService.find(userId);
    try {
      password = KeyGenerator.decrypt(password, RSAHelper.getPrivateKey(serverPrivateKey));
      password_confirm =
          KeyGenerator.decrypt(password_confirm, RSAHelper.getPrivateKey(serverPrivateKey));
      password_new = KeyGenerator.decrypt(password_new, RSAHelper.getPrivateKey(serverPrivateKey));
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (password == null || !tenantAccount.getPassword().equals(DigestUtils.md5Hex(password))) {
      response.setCode(CommonAttributes.FAIL_UPDATE_PWD);
      response.setDesc(Message.error("csh.originPwd.error").getContent());
      return response;
    }
    tenantAccount.setPassword(DigestUtils.md5Hex(password_new));
    tenantAccountService.update(tenantAccount);

    if (LogUtil.isDebugEnabled(TenantAccountController.class)) {
      LogUtil.debug(TenantAccountController.class, "updatePwd",
          "Tenant Update Password. UserName: %s，TenantID: %s", tenantAccount.getUserName(),
          tenantAccount.getTenantID());
    }
    response.setCode(CommonAttributes.SUCCESS);
    String newToken = TokenGenerator.generateToken();
    tenantAccountService.createTenantUserToken(newToken, tenantAccount.getId());
    response.setToken(newToken);
    return response;
  }


  /**
   * 修改租户信息
   *
   * @param req
   * @return
   */
  @RequestMapping(value = "/editTenantInfo", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse editTenantInfo(@RequestBody TenantInfoRequest req) {
    BaseResponse response = new BaseResponse();
    Long userId = req.getUserId();
    String token = req.getToken();
    // 验证登录token
    String userToken = tenantAccountService.getTenantUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    Long tenantId = req.getTenantId();
    String systemName = req.getSystemName();
    String remark = req.getRemark();
    String contactPhone = req.getContactPhone();
    String contactPerson = req.getContactPerson();
    String email = req.getEmail();
    String businessTime = req.getBusinessTime();
    String description = req.getDescription();
    TenantInfo tenantInfo = tenantInfoService.find(tenantId);

    tenantInfo.setSystemName(systemName);
    tenantInfo.setRemark(remark);
    tenantInfo.setContactPerson(contactPerson);
    tenantInfo.setContactPhone(contactPhone);
    tenantInfo.setEmail(email);
    tenantInfo.setBusinessTime(businessTime);
    tenantInfo.setDescription(description);
    tenantInfoService.update(tenantInfo);

    if (LogUtil.isDebugEnabled(TenantAccountController.class)) {
      LogUtil
          .debug(
              TenantAccountController.class,
              "editTenantInfo",
              "Edit Tenant Info. systemName: %s, contactPerson: %s, contactPhone: %s, email: %s, businessTime: %s, remark: %s, description: %s",
              systemName, contactPerson, contactPhone, email, businessTime, remark, description);
    }

    response.setCode(CommonAttributes.SUCCESS);

    String newtoken = TokenGenerator.generateToken(req.getToken());
    tenantAccountService.createTenantUserToken(token, userId);
    response.setToken(newtoken);
    return response;
  }

  /**
   * 获取租户信息
   *
   * @param req
   * @return
   */
  @RequestMapping(value = "/getTenantInfo", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseOne<Map<String, Object>> getTenantInfo(
      @RequestBody TenantInfoRequest req) {
    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
    Long userId = req.getUserId();
    String token = req.getToken();
    // 验证登录token
    String userToken = tenantAccountService.getTenantUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    Long tenantId = req.getTenantId();
    TenantInfo tenantInfo = tenantInfoService.find(tenantId);
    String[] properties =
        {"id", "tenantName", "systemName", "orgCode", "contactPhone", "address", "email",
            "businessTime", "description", "area.fullName", "ownerName", "accountStatus",
            "versionConfig.versionName", "remark"};
    Map<String, Object> map = FieldFilterUtils.filterEntityMap(properties, tenantInfo);
    response.setMsg(map);
    response.setCode(CommonAttributes.SUCCESS);

    String newtoken = TokenGenerator.generateToken(req.getToken());
    tenantAccountService.createTenantUserToken(token, userId);
    response.setToken(newtoken);
    return response;
  }


}
