//package com.ash.controller;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.Assert;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.lb.beans.CommonAttributes;
//import com.lb.beans.Message;
//import com.lb.beans.Setting.ImageType;
//import com.lb.common.enumeration.CommonEnum.AccountStatus;
//import com.lb.common.enumeration.CommonEnum.DonateSource;
//import com.lb.common.enumeration.CommonEnum.DonateType;
//import com.lb.common.enumeration.CommonEnum.Gender;
//import com.lb.common.enumeration.CommonEnum.SmsTokenType;
//import com.lb.common.log.LogUtil;
//import com.lb.controller.base.MobileBaseController;
//import com.lb.entity.EndUser;
//import com.lb.entity.Lawyer;
//import com.lb.entity.LoginStatistics;
//import com.lb.entity.Profession;
//import com.lb.entity.SmsToken;
//import com.lb.framework.filter.Filter;
//import com.lb.framework.filter.Filter.Operator;
//import com.lb.json.base.BaseRequest;
//import com.lb.json.base.BaseResponse;
//import com.lb.json.base.ResponseMap;
//import com.lb.json.base.ResponseMultiple;
//import com.lb.json.base.ResponseOne;
//import com.lb.json.request.EndUserInfoRequest;
//import com.lb.json.request.ResetPwdRequest;
//import com.lb.json.request.SmsTokenRequest;
//import com.lb.json.request.UserLoginRequest;
//import com.lb.json.request.UserRegRequest;
//import com.lb.service.DonateConfigService;
//import com.lb.service.DonateRecordService;
//import com.lb.service.EndUserService;
//import com.lb.service.FileService;
//import com.lb.service.LawyerService;
//import com.lb.service.ProfessionService;
//import com.lb.service.SmsTokenService;
//import com.lb.utils.ApiUtils;
//import com.lb.utils.KeyGenerator;
//import com.lb.utils.RSAHelper;
//import com.lb.utils.TimeUtils;
//import com.lb.utils.TokenGenerator;
//
//
//@Controller("endUserController")
//@RequestMapping("/endUser")
//public class EndUserController extends MobileBaseController {
//
//  @Resource(name = "endUserServiceImpl")
//  private EndUserService endUserService;
//  @Resource(name = "smsTokenServiceImpl")
//  private SmsTokenService smsTokenService;
//  @Resource(name = "lawyerServiceImpl")
//  private LawyerService lawyerService;
//  @Resource(name = "professionServiceImpl")
//  private ProfessionService professionService;
//  @Resource(name = "donateRecordServiceImpl")
//  private DonateRecordService donateRecordService;
//  @Resource(name = "donateConfigServiceImpl")
//  private DonateConfigService donateConfigService;
//  @Resource(name = "fileServiceImpl")
//  private FileService fileService;
//
//
//  /**
//   * 获取公匙
//   * 
//   * @param request
//   * @return
//   */
//  @RequestMapping(value = "/rsa", method = RequestMethod.POST)
//  public @ResponseBody BaseResponse rsa(HttpServletRequest request) {
//    BaseResponse response = new BaseResponse();
//    String key = setting.getServerPublicKey();
//    response.setCode(CommonAttributes.SUCCESS);
//    response.setDesc(key);
//    return response;
//  }
//
//  /**
//   * 用户注销
//   * 
//   * @return
//   */
//  @RequestMapping(value = "/logout", method = RequestMethod.POST)
//  public @ResponseBody BaseResponse logout(BaseRequest req) {
//    BaseResponse response = new BaseResponse();
//    Long userId = req.getUserId();
//    String token = req.getToken();
//
//    String userToken = endUserService.getEndUserToken(userId);
//    // token验证
//    if (!TokenGenerator.isValiableToken(token, userToken)) {
//      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
//      response.setDesc(Message.error("lb.user.token.timeout").getContent());
//      return response;
//    }
//    endUserService.deleteEndUserToken(userId);
//    response.setCode(CommonAttributes.SUCCESS);
//    return response;
//  }
//
//  /**
//   * 更新个推client id
//   * 
//   * @param req
//   * @return
//   */
//  @RequestMapping(value = "/updatePushClientId", method = RequestMethod.POST)
//  public @ResponseBody BaseResponse updatePushClientId(@RequestBody UserLoginRequest req) {
//    BaseResponse response = new BaseResponse();
//    Long userId = req.getUserId();
//
//    String appClientId = req.getAppClientId();
//    List<Filter> filters = new ArrayList<Filter>();
//    Filter appClientIdfilter = new Filter("appClientId", Operator.eq, appClientId);
//    Filter endUserfilter = new Filter("id", Operator.ne, userId);
//    filters.add(appClientIdfilter);
//    filters.add(endUserfilter);
//    List<EndUser> endUsers = endUserService.findList(null, filters, null);
//    if (endUsers != null && endUsers.size() > 0) {
//      for (EndUser user : endUsers) {
//        user.setAppClientId(null);
//        endUserService.update(user);
//      }
//    }
//
//    EndUser endUser = endUserService.find(userId);
//    endUser.setAppClientId(appClientId);
//    endUserService.update(endUser);
//    if (LogUtil.isDebugEnabled(EndUserController.class)) {
//      LogUtil.debug(EndUserController.class, "updatePushClientId",
//          "update User APP GeTui client id. userMobile: %s,clientID:%s", endUser.getMobileNum(),
//          appClientId);
//    }
//    response.setCode(CommonAttributes.SUCCESS);
//    return response;
//  }
//
//  /**
//   * 终端用户登录
//   * 
//   * @param userLoginReq
//   * @param request
//   * @return
//   */
//  @RequestMapping(value = "/login", method = RequestMethod.POST)
//  public @ResponseBody ResponseOne<EndUser> login(HttpServletRequest requesthttp,
//      @RequestBody UserLoginRequest userLoginReq) {
//    ResponseOne<EndUser> response = new ResponseOne<EndUser>();
//    String serverPrivateKey = setting.getServerPrivateKey();
//    String username = userLoginReq.getUserName();
//    String password = userLoginReq.getPassword();
//    if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
//      response.setCode(CommonAttributes.FAIL_LOGIN);
//      response.setDesc(Message.error("lb.nameorpwd.invaliable").getContent());
//      return response;
//    }
//    try {
//      password = KeyGenerator.decrypt(password, RSAHelper.getPrivateKey(serverPrivateKey));
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//
//    if (password == null || password.length() < setting.getPasswordMinlength()
//        || password.length() > setting.getPasswordMaxlength()) {
//      response.setCode(CommonAttributes.FAIL_LOGIN);
//      response.setDesc(Message.error("lb.nameorpwd.invaliable").getContent());
//      return response;
//    }
//    if (LogUtil.isDebugEnabled(EndUserController.class)) {
//      LogUtil.debug(EndUserController.class, "find",
//          "Fetching User from database with UserName: %s", username);
//    }
//    EndUser loginUser = endUserService.findByUserMobileOrName(username);
//    if (loginUser == null) {
//      response.setCode(CommonAttributes.FAIL_LOGIN);
//      response.setDesc(Message.error("lb.user.noexist").getContent());
//      return response;
//    }
//
//    if (!DigestUtils.md5Hex(password).equals(loginUser.getPassword())) {
//      response.setCode(CommonAttributes.FAIL_LOGIN);
//      response.setDesc(Message.error("lb.nameorpwd.error").getContent());
//      return response;
//    }
//
//    if (AccountStatus.disabled.equals(loginUser.getAccountStatus())
//        || AccountStatus.delete.equals(loginUser.getAccountStatus())) {
//      response.setCode(CommonAttributes.USER_INVALID);
//      response.setDesc(Message.error("lb.current.user.invalid").getContent());
//      return response;
//    }
//
//    LoginStatistics loginStatistics = new LoginStatistics();
//    loginStatistics.setEndUser(loginUser);
//    loginStatistics.setLoginDate(new Date());
//    loginStatistics.setLoginIp(requesthttp.getRemoteAddr());
//    loginUser.getLoginStatistics().add(loginStatistics);
//    endUserService.update(loginUser);
//    if (LogUtil.isDebugEnabled(EndUserController.class)) {
//      LogUtil.debug(EndUserController.class, "update", "update enduser login status: %s", username);
//    }
//
//    loginUser.setIsDisplaySignIn(donateConfigService.isDisplaySignIn(loginUser));
//    response.setCode(CommonAttributes.SUCCESS);
//    response.setDesc(loginUser.getId().toString());
//    response.setMsg(loginUser);
//    String token = TokenGenerator.generateToken();
//    endUserService.createEndUserToken(token, loginUser.getId());
//    response.setToken(token);
//    return response;
//  }
//
//
//  /**
//   * 注册验证手机短信验证码
//   * 
//   * @param request
//   * @param userReg
//   * @return
//   */
//  @RequestMapping(value = "/verifySmsTokenForReg", method = RequestMethod.POST)
//  public @ResponseBody BaseResponse verifySmsTokenForReg(HttpServletRequest request,
//      @RequestBody UserRegRequest userReg) {
//    BaseResponse response = new BaseResponse();
//    String serverPrivateKey = setting.getServerPrivateKey();
//    String userName = userReg.getUserName();
//    String password = userReg.getPassword();
//    String smsToken = userReg.getSmsToken();
//    String appClientId = userReg.getAppClientId();
//    if (LogUtil.isDebugEnabled(EndUserController.class)) {
//      LogUtil.debug(EndUserController.class, "find",
//          "Fetching SmsToken from database with mobile no: %s", userName);
//    }
//    // 短信验证码验证
//    SmsToken userSmsToken = smsTokenService.findByUserMobile(userName,SmsTokenType.REG);
//    if (userSmsToken == null) {
//      response.setCode(CommonAttributes.FAIL_SMSTOKEN);
//      response.setDesc(Message.error("lb.mobile.invaliable").getContent());
//      return response;
//    } else {
//      String timeOutToken = userSmsToken.getTimeoutToken();
//      String smsCode = userSmsToken.getSmsToken();
//      if (timeOutToken != null
//          && !TokenGenerator.tokenTimeOut(timeOutToken, setting.getSmsCodeTimeOut())) {
//        if (!smsCode.equals(smsToken)) {
//          response.setCode(CommonAttributes.FAIL_SMSTOKEN);
//          response.setDesc(Message.error("lb.sms.token.error").getContent());
//          return response;
//        } else {
//          smsTokenService.delete(userSmsToken);
//        }
//      } else {
//        response.setCode(CommonAttributes.FAIL_SMSTOKEN);
//        response.setDesc(Message.error("lb.sms.token.timeout").getContent());
//        return response;
//      }
//    }
//
//    try {
//      password = KeyGenerator.decrypt(password, RSAHelper.getPrivateKey(serverPrivateKey));
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//
//    EndUser regUser = new EndUser();
//    regUser.setCreateDate(new Date());
//    regUser.setMobileNum(userName);
//    regUser.setPassword(DigestUtils.md5Hex(password));
//    regUser.setGoldCoin(0);
//    regUser.setBalance(new BigDecimal(0));
//    regUser.setAccountStatus(AccountStatus.actived);
//    regUser.setAppClientId(appClientId);
//    if (LogUtil.isDebugEnabled(EndUserController.class)) {
//      LogUtil.debug(EndUserController.class, "save", "EndUser Reg.UserName: %s,Password: %s",
//          userName, regUser.getPassword());
//    }
//    endUserService.save(regUser);
//
//    response.setCode(CommonAttributes.SUCCESS);
//    response.setDesc(Message.success("lb.reg.success").getContent());
//    return response;
//
//
//  }
//
//
//
//  /**
//   * 终端用户注册
//   * 
//   * @param request
//   * @param userReg
//   * @return
//   */
//  @RequestMapping(value = "/reg", method = RequestMethod.POST)
//  public @ResponseBody BaseResponse reg(HttpServletRequest request,
//      @RequestBody UserRegRequest userReg) {
//    BaseResponse response = new BaseResponse();
//    //String serverPrivateKey = setting.getServerPrivateKey();
//    String username = userReg.getUserName();
//    //String password = userReg.getPassword();
//    // 手机号码格式验证
//    if (StringUtils.isEmpty(username) || !isMobileNumber(username)) {
//      response.setCode(CommonAttributes.FAIL_REG);
//      response.setDesc(Message.error("lb.mobile.invaliable").getContent());
//      return response;
//    }
//    // 账号重复验证
//    EndUser user = endUserService.findByUserMobile(username);
//    if (user != null) {
//      response.setCode(CommonAttributes.FAIL_REG);
//      response.setDesc(Message.error("lb.mobile.used").getContent());
//      return response;
//      // try {
//      // password = KeyGenerator.decrypt(password, RSAHelper.getPrivateKey(serverPrivateKey));
//      // } catch (Exception e) {
//      // e.printStackTrace();
//      // }
//      // // 密码解密后长度验证
//      // if (password == null || password.length() < setting.getPasswordMinlength()
//      // || password.length() > setting.getPasswordMaxlength()) {
//      // response.setCode(CommonAttributes.FAIL_REG);
//      // response.setDesc(Message.error("lb.nameorpwd.invaliable").getContent());
//      // return response;
//      // }
//      // if (DigestUtils.md5Hex(password).equals(user.getPassword())) {
//      // String token = TokenGenerator.generateToken();
//      // endUserService.createEndUserToken(token, user.getId());
//      // user.setModifyDate(new Date());
//      // endUserService.update(user);
//      // response.setCode(CommonAttributes.SUCCESS);
//      // response.setDesc(user.getId().toString());
//      // response.setToken(token);
//      // } else {
//      // response.setCode(CommonAttributes.FAIL_REG);
//      // response.setDesc(Message.error("lb.user.exist").getContent());
//      // }
//    } else {
//      Lawyer lawyer = lawyerService.findByUserMobile(username);
//      if (lawyer != null) {
//        response.setCode(CommonAttributes.FAIL_REG);
//        response.setDesc(Message.error("lb.lawyer.exist").getContent());
//        return response;
//      }
//      // SmsToken userSms = smsTokenService.findByUserMobile(username);
//      // if (userSms != null) {
//      // smsTokenService.delete(userSms);
//      // }
//      // Integer smsToken = (int) ((Math.random() * 9 + 1) * 100000);
//      // String smsTxt = setting.getSmsTxtPrefix() + smsToken+setting.getSmsTxtSuffix();
//      // ApiUtils.sendSmsMsg(username, smsTxt);// 通过第三平台发送短信验证码
//      // SmsToken userSmsToken = new SmsToken();
//      // userSmsToken.setCreateDate(new Date());
//      // userSmsToken.setMobile(username);
//      // userSmsToken.setSmsToken(smsToken.toString());
//      // userSmsToken.setTimeoutToken(TokenGenerator.generateToken());
//      // smsTokenService.save(userSmsToken);
//      // response.setCode(CommonAttributes.SUCCESS);
//      // response.setDesc(smsToken.toString());
//      response.setCode(CommonAttributes.SUCCESS);
//    }
//    return response;
//
//  }
//
//
//  /**
//   * 重置密码
//   * 
//   * @param request
//   * @param resetPwdReq
//   * @return
//   */
//  @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
//  public @ResponseBody BaseResponse resetPwd(HttpServletRequest request,
//      @RequestBody ResetPwdRequest resetPwdReq) {
//    BaseResponse response = new BaseResponse();
//    String serverPrivateKey = setting.getServerPrivateKey();
//    String password_confirm = resetPwdReq.getPassword_confirm();
//    String password = resetPwdReq.getPassword();
//    String smsToken = resetPwdReq.getSmsToken();
//    String mobileNo = resetPwdReq.getMobileNo();
//
//    if (StringUtils.isEmpty(mobileNo) || !isMobileNumber(mobileNo)) {
//      response.setCode(CommonAttributes.FAIL_RESET_PWD);
//      response.setDesc(Message.error("lb.mobile.invaliable").getContent());
//      return response;
//    }
//    if (smsToken != null) {
//      // 短信验证码验证
//      SmsToken userSmsToken = smsTokenService.findByUserMobile(mobileNo,SmsTokenType.FINDPWD);
//      if (userSmsToken == null) {
//        response.setCode(CommonAttributes.FAIL_RESET_PWD);
//        response.setDesc(Message.error("lb.mobile.invaliable").getContent());
//      } else {
//        String timeOutToken = userSmsToken.getTimeoutToken();
//        String smsCode = userSmsToken.getSmsToken();
//        if (timeOutToken != null
//            && !TokenGenerator.tokenTimeOut(timeOutToken, setting.getSmsCodeTimeOut())) {
//          if (!smsCode.equals(smsToken)) {
//            response.setCode(CommonAttributes.FAIL_RESET_PWD);
//            response.setDesc(Message.error("lb.sms.token.error").getContent());
//          } else {
//            smsTokenService.delete(userSmsToken);
//            response.setCode(CommonAttributes.SUCCESS);
//          }
//        } else {
//          response.setCode(CommonAttributes.FAIL_RESET_PWD);
//          response.setDesc(Message.error("lb.sms.token.timeout").getContent());
//        }
//      }
//      return response;
//    }
//    try {
//      password = KeyGenerator.decrypt(password, RSAHelper.getPrivateKey(serverPrivateKey));
//      password_confirm =
//          KeyGenerator.decrypt(password_confirm, RSAHelper.getPrivateKey(serverPrivateKey));
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    if (!password.equals(password_confirm)) {
//      response.setCode(CommonAttributes.FAIL_RESET_PWD);
//      response.setDesc(Message.error("lb.pwd.no.same").getContent());
//    } else {
//      EndUser endUser = endUserService.findByUserMobile(mobileNo);
//      if (endUser == null) {
//        response.setCode(CommonAttributes.FAIL_RESET_PWD);
//        response.setDesc(Message.error("lb.user.noexist").getContent());
//        return response;
//      }
//      endUser.setPassword(DigestUtils.md5Hex(password));
//      endUser.setModifyDate(new Date());
//      endUserService.update(endUser);
//      response.setCode(CommonAttributes.SUCCESS);
//      response.setDesc(Message.success("lb.reset.pwd.success").getContent());
//    }
//    return response;
//  }
//
//  /**
//   * 获取短信验证码
//   * 
//   * @param request
//   * @param smsTokenRequest
//   * @return
//   */
//  @RequestMapping(value = "/getSmsToken", method = RequestMethod.POST)
//  public @ResponseBody BaseResponse getSmsToken(HttpServletRequest request,
//      @RequestBody SmsTokenRequest smsTokenRequest) {
//    BaseResponse response = new BaseResponse();
//    String mobileNo = smsTokenRequest.getMobileNo();
//    Integer tokenType = smsTokenRequest.getTokenType();
//    if (StringUtils.isEmpty(mobileNo) || !isMobileNumber(mobileNo)) {
//      response.setCode(CommonAttributes.FAIL_SMSTOKEN);
//      response.setDesc(Message.error("lb.mobile.invaliable").getContent());
//    } else {
//      if (LogUtil.isDebugEnabled(EndUserController.class)) {
//        LogUtil.debug(EndUserController.class, "find",
//            "Fetching SmsToken from database with mobile no: %s", mobileNo);
//      }
//      if (tokenType == SmsTokenType.FINDPWD.ordinal()) {
//        EndUser endUser = endUserService.findByUserMobile(mobileNo);
//        if (endUser == null) {
//          response.setCode(CommonAttributes.FAIL_SMSTOKEN);
//          response.setDesc(Message.error("lb.user.noexist").getContent());
//          return response;
//        }
//      }
//
//      SmsToken smsToken = smsTokenService.findByUserMobile(mobileNo,SmsTokenType.class.getEnumConstants()[tokenType]);
//      if (smsToken != null) {
//        smsTokenService.delete(smsToken);
//      }
//      Integer smsTokenNo = (int) ((Math.random() * 9 + 1) * 100000);
//      String smsTxt = setting.getSmsTxtPrefix() + smsTokenNo + setting.getSmsTxtSuffix();
//      ApiUtils.sendSmsMsg(mobileNo, smsTxt);// 通过第三平台发送短信验证码
//      SmsToken userSmsToken = new SmsToken();
//      userSmsToken.setCreateDate(new Date());
//      userSmsToken.setMobile(mobileNo);
//      userSmsToken.setSmsToken(smsTokenNo.toString());
//      userSmsToken.setTimeoutToken(TokenGenerator.generateToken());
//      userSmsToken.setSmsTokenType(SmsTokenType.class.getEnumConstants()[tokenType]);
//      smsTokenService.save(userSmsToken);
//      response.setCode(CommonAttributes.SUCCESS);
//      response.setDesc(smsTokenNo.toString());
//    }
//    return response;
//  }
//
//  /**
//   * 修改终端用户信息(不包括头像)
//   * 
//   * @param req
//   * @return
//   */
//  @RequestMapping(value = "/editUserInfo", method = RequestMethod.POST)
//  public @ResponseBody ResponseOne<EndUser> editUserInfo(@RequestBody EndUserInfoRequest req) {
//    ResponseOne<EndUser> response = new ResponseOne<EndUser>();
//    Long userId = req.getUserId();
//    String token = req.getToken();
//    Long professionId = req.getProfessionId();
//    Integer sex = req.getSex();
//    String birthday = req.getBirthdayDay();
//    String address = req.getAddress();
//    // 验证登录token
//    String userToken = endUserService.getEndUserToken(userId);
//    if (!TokenGenerator.isValiableToken(token, userToken)) {
//      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
//      response.setDesc(Message.error("lb.user.token.timeout").getContent());
//      return response;
//    }
//
//    EndUser endUser = endUserService.find(userId);
//    if (sex != null) {// 修改性别
//      if (endUser.getGender() == null) {
//        endUser =
//            endUserService.endUserDonate(endUser, "donateSource", DonateSource.SETGENDER,
//                DonateType.MONEY);
//      }
//      endUser.setGender(Gender.class.getEnumConstants()[sex]);
//    }
//    if (birthday != null) {// 修改出生年月
//      String[] dmy = birthday.split("-");
//      Calendar calendar = Calendar.getInstance();
//      calendar
//          .set(Integer.parseInt(dmy[0]), Integer.parseInt(dmy[1]) - 1, Integer.parseInt(dmy[2]));
//      if (endUser.getBirthDay() == null) {
//        endUser =
//            endUserService.endUserDonate(endUser, "donateSource", DonateSource.SETBIRTHDAY,
//                DonateType.MONEY);
//      }
//      endUser.setBirthDay(calendar.getTime());
//    }
//    if (professionId != null) {// 修改职业
//      if (endUser.getProfession() == null) {
//        endUser =
//            endUserService.endUserDonate(endUser, "donateSource", DonateSource.SETPROFESSION,
//                DonateType.MONEY);
//      }
//      Profession profession = professionService.find(professionId);
//      endUser.setProfession(profession);
//    }
//    if (address != null) {// 修改地址
//      if (endUser.getAddress() == null) {
//        endUser =
//            endUserService.endUserDonate(endUser, "donateSource", DonateSource.SETADDRESS,
//                DonateType.MONEY);
//      }
//      endUser.setAddress(address);
//    }
//    endUserService.update(endUser);
//    if (LogUtil.isDebugEnabled(EndUserController.class)) {
//      LogUtil
//          .debug(
//              EndUserController.class,
//              "Update",
//              "Updating userName for EndUser. Mobile: %s, UserName: %s, Birthday: %s,sex:%s, professionId:%s",
//              endUser.getMobileNum(), endUser.getUserName(), birthday, sex, professionId);
//    }
//
//    response.setCode(CommonAttributes.SUCCESS);
//    response.setMsg(endUser);
//    if (endUser.getDonateAmount() != null) {
//      response.setDesc(Message.success("lb.complete.success", endUser.getDonateAmount())
//          .getContent());
//    }
//    String newtoken = TokenGenerator.generateToken(req.getToken());
//    endUserService.createEndUserToken(newtoken, userId);
//    response.setToken(newtoken);
//    return response;
//  }
//
//  /**
//   * 修改用户头像
//   * 
//   * @param req
//   * @return
//   */
//  @RequestMapping(value = "/editUserPhoto", method = RequestMethod.POST)
//  public @ResponseBody ResponseMap editUserPhoto(EndUserInfoRequest req) {
//    ResponseMap response = new ResponseMap();
//    Map<String, Object> msg = new HashMap<String, Object>();
//    Long userId = req.getUserId();
//    String token = req.getToken();
//    MultipartFile photo = req.getPhoto();
//    Assert.notNull(photo);
//
//    // 验证登录token
//    String userToken = endUserService.getEndUserToken(userId);
//    if (!TokenGenerator.isValiableToken(token, userToken)) {
//      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
//      response.setDesc(Message.error("lb.user.token.timeout").getContent());
//      return response;
//    }
//
//    EndUser endUser = endUserService.find(userId);
//
//    if (endUser.getPhoto() == null) {
//      endUser =
//          endUserService.endUserDonate(endUser, "donateSource", DonateSource.SETPHOTO,
//              DonateType.MONEY);
//    }
//    endUser.setPhoto(fileService.saveImage(photo, ImageType.PHOTO));
//
//    endUserService.update(endUser);
//    if (LogUtil.isDebugEnabled(EndUserController.class)) {
//      LogUtil.debug(EndUserController.class, "Update",
//          "Updating userName for EndUser. Mobile: %s, UserName: %s", endUser.getMobileNum(),
//          endUser.getUserName());
//    }
//
//    response.setCode(CommonAttributes.SUCCESS);
//    if (endUser.getDonateAmount() != null) {
//      response.setDesc(Message.success("lb.complete.success", endUser.getDonateAmount())
//          .getContent());
//    }
//
//    msg.put("endUserPhoto", endUser.getPhoto());
//    response.setMsg(msg);
//    String newtoken = TokenGenerator.generateToken(req.getToken());
//    endUserService.createEndUserToken(newtoken, userId);
//    response.setToken(newtoken);
//    return response;
//  }
//
//  /**
//   * 获取终端用户个人信息
//   * 
//   * @param request
//   * @param baseRequest
//   * @return
//   */
//  @RequestMapping(value = "/accoutInfo", method = RequestMethod.POST)
//  public @ResponseBody ResponseOne<EndUser> accoutInfo(@RequestBody BaseRequest req) {
//    ResponseOne<EndUser> response = new ResponseOne<EndUser>();
//    Long userId = req.getUserId();
//    String token = req.getToken();
//
//    // 验证登录token
//    String userToken = endUserService.getEndUserToken(userId);
//    if (!TokenGenerator.isValiableToken(token, userToken)) {
//      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
//      response.setDesc(Message.error("lb.user.token.timeout").getContent());
//      return response;
//    }
//
//    EndUser endUser = endUserService.find(userId);
//    String newtoken = TokenGenerator.generateToken(req.getToken());
//    endUserService.createEndUserToken(newtoken, userId);
//    response.setMsg(endUser);
//    response.setToken(newtoken);
//    response.setCode(CommonAttributes.SUCCESS);
//    return response;
//  }
//
//  /**
//   * 获取终端用户职业列表
//   * 
//   * @param req
//   * @return
//   */
//  @RequestMapping(value = "/getProfession", method = RequestMethod.POST)
//  public @ResponseBody ResponseMultiple<Profession> getProfession(@RequestBody BaseRequest req) {
//    ResponseMultiple<Profession> response = new ResponseMultiple<Profession>();
//    Long userId = req.getUserId();
//    String token = req.getToken();
//
//    // 验证登录token
//    String userToken = endUserService.getEndUserToken(userId);
//    if (!TokenGenerator.isValiableToken(token, userToken)) {
//      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
//      response.setDesc(Message.error("lb.user.token.timeout").getContent());
//      return response;
//    }
//
//    List<Profession> professions = new ArrayList<>(professionService.findAll());
//    String newtoken = TokenGenerator.generateToken(req.getToken());
//    endUserService.createEndUserToken(newtoken, userId);
//    response.setMsg(professions);
//    response.setToken(newtoken);
//    response.setCode(CommonAttributes.SUCCESS);
//    return response;
//  }
//
//  /**
//   * 修改终端用户名
//   * 
//   * @param req
//   * @return
//   */
//  @RequestMapping(value = "/editUserName", method = RequestMethod.POST)
//  public @ResponseBody ResponseOne<EndUser> editUserName(@RequestBody BaseRequest req) {
//    ResponseOne<EndUser> response = new ResponseOne<EndUser>();
//    Long userId = req.getUserId();
//    String userName = req.getUserName();
//    String token = req.getToken();
//
//    Assert.notNull(userName);
//    // 验证登录token
//    String userToken = endUserService.getEndUserToken(userId);
//    if (!TokenGenerator.isValiableToken(token, userToken)) {
//      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
//      response.setDesc(Message.error("lb.user.token.timeout").getContent());
//      return response;
//    }
//
//    EndUser user = endUserService.findByUserMobileOrName(userName);
//    if (user != null) {
//      response.setCode(CommonAttributes.FAIL_ENDUSER_INFO);
//      response.setDesc(Message.error("lb.username.exist").getContent());
//    } else {
//      EndUser endUser = endUserService.find(userId);
//      if (endUser.getUserName() == null) {
//        endUser =
//            endUserService.endUserDonate(endUser, "donateSource", DonateSource.SETUSERNAME,
//                DonateType.MONEY);
//      }
//      endUser.setUserName(userName);
//      endUserService.update(endUser);
//      if (LogUtil.isDebugEnabled(EndUserController.class)) {
//        LogUtil.debug(EndUserController.class, "Update",
//            "Updating userName for EndUser. Mobile: %s, NewUserName: %s", endUser.getMobileNum(),
//            userName);
//      }
//      response.setCode(CommonAttributes.SUCCESS);
//      response.setMsg(endUser);
//      if (endUser.getDonateAmount() != null) {
//        response.setDesc(Message.success("lb.complete.success", endUser.getDonateAmount())
//            .getContent());
//      }
//    }
//    String newtoken = TokenGenerator.generateToken(req.getToken());
//    endUserService.createEndUserToken(newtoken, userId);
//    response.setToken(newtoken);
//    return response;
//  }
//
//
//  /**
//   * 获取短信验证码（登录后带token）
//   * 
//   * @param request
//   * @param smsTokenRequest
//   * @return
//   */
//  @RequestMapping(value = "/getSmsTokenWithToken", method = RequestMethod.POST)
//  public @ResponseBody BaseResponse getSmsTokenWithToken(HttpServletRequest request,
//      @RequestBody SmsTokenRequest smsTokenRequest) {
//    BaseResponse response = new BaseResponse();
//    Long userId = smsTokenRequest.getUserId();
//    String token = smsTokenRequest.getToken();
//    String mobileNo = smsTokenRequest.getMobileNo();
//    Integer tokenType = smsTokenRequest.getTokenType();
//    Assert.notNull(mobileNo);
//    Assert.notNull(tokenType);
//    // 验证登录token
//    String userToken = endUserService.getEndUserToken(userId);
//    if (!TokenGenerator.isValiableToken(token, userToken)) {
//      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
//      response.setDesc(Message.error("lb.user.token.timeout").getContent());
//      return response;
//    }
//
//    if (StringUtils.isEmpty(mobileNo) || !isMobileNumber(mobileNo)) {
//      response.setCode(CommonAttributes.FAIL_SMSTOKEN);
//      response.setDesc(Message.error("lb.mobile.invaliable").getContent());
//    } else {
//      if (LogUtil.isDebugEnabled(EndUserController.class)) {
//        LogUtil.debug(EndUserController.class, "find",
//            "Fetching SmsToken from database with mobile no: %s", mobileNo);
//      }
//      if (tokenType == SmsTokenType.MODIFY_MOBILENUM.ordinal()) {// 短信验证码类型为修改手机号
//        SmsToken smsToken = smsTokenService.findByUserMobile(mobileNo,SmsTokenType.MODIFY_MOBILENUM);
//        if (smsToken != null) {
//          smsTokenService.delete(smsToken);
//        }
//        Integer smsTokenNo = (int) ((Math.random() * 9 + 1) * 100000);
//        String smsTxt = setting.getSmsTxtPrefix() + smsTokenNo + setting.getSmsTxtSuffix();
//        ApiUtils.sendSmsMsg(mobileNo, smsTxt);// 通过第三平台发送短信验证码
//        SmsToken userSmsToken = new SmsToken();
//        userSmsToken.setCreateDate(new Date());
//        userSmsToken.setMobile(mobileNo);
//        userSmsToken.setSmsToken(smsTokenNo.toString());
//        userSmsToken.setTimeoutToken(TokenGenerator.generateToken());
//        userSmsToken.setSmsTokenType(SmsTokenType.class.getEnumConstants()[tokenType]);
//        smsTokenService.save(userSmsToken);
//        response.setDesc(smsTokenNo.toString());
//      }
//
//    }
//    response.setCode(CommonAttributes.SUCCESS);
//    String newtoken = TokenGenerator.generateToken(smsTokenRequest.getToken());
//    endUserService.createEndUserToken(newtoken, userId);
//    response.setToken(newtoken);
//    return response;
//  }
//
//  /**
//   * 终端用户设置绑定手机号
//   * 
//   * @param request
//   * @param smsTokenRequest
//   * @return
//   */
//  @RequestMapping(value = "/bindMobile", method = RequestMethod.POST)
//  public @ResponseBody BaseResponse bindMobile(HttpServletRequest request,
//      @RequestBody UserRegRequest reg) {
//    BaseResponse response = new BaseResponse();
//    Long userId = reg.getUserId();
//    String token = reg.getToken();
//    String userName = reg.getUserName();
//    String smsToken = reg.getSmsToken();
//    Assert.notNull(userName);
//    Assert.notNull(smsToken);
//    // 验证登录token
//    String userToken = endUserService.getEndUserToken(userId);
//    if (!TokenGenerator.isValiableToken(token, userToken)) {
//      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
//      response.setDesc(Message.error("lb.user.token.timeout").getContent());
//      return response;
//    }
//
//    // 短信验证码验证
//    SmsToken userSmsToken = smsTokenService.findByUserMobile(userName,SmsTokenType.MODIFY_MOBILENUM);
//    if (userSmsToken == null) {
//      response.setCode(CommonAttributes.FAIL_SMSTOKEN);
//      response.setDesc(Message.error("lb.mobile.invaliable").getContent());
//    } else {
//      String timeOutToken = userSmsToken.getTimeoutToken();
//      String smsCode = userSmsToken.getSmsToken();
//      if (timeOutToken != null
//          && !TokenGenerator.tokenTimeOut(timeOutToken, setting.getSmsCodeTimeOut())) {
//        if (!smsCode.equals(smsToken)) {
//          response.setCode(CommonAttributes.FAIL_SMSTOKEN);
//          response.setDesc(Message.error("lb.sms.token.error").getContent());
//        } else {
//          smsTokenService.delete(userSmsToken);
//          EndUser endUser = endUserService.findByUserMobile(userName);
//          Lawyer lawyer = lawyerService.findByUserMobile(userName);
//          if (endUser != null || lawyer != null) {
//            response.setCode(CommonAttributes.FAIL_ENDUSER_INFO);
//            response.setDesc(Message.error("lb.username.exist").getContent());
//          } else {
//            response.setCode(CommonAttributes.SUCCESS);
//            EndUser user = endUserService.find(userId);
//            if (user.getMobileNum() == null) {
//              user =
//                  endUserService.endUserDonate(user, "donateSource", DonateSource.SETMOBILE,
//                      DonateType.MONEY);
//            }
//            user.setMobileNum(userName);
//            endUserService.update(user);
//          }
//        }
//      } else {
//        response.setCode(CommonAttributes.FAIL_SMSTOKEN);
//        response.setDesc(Message.error("lb.sms.token.timeout").getContent());
//      }
//    }
//    String newtoken = TokenGenerator.generateToken(reg.getToken());
//    endUserService.createEndUserToken(newtoken, userId);
//    response.setToken(newtoken);
//    return response;
//  }
//
//  /**
//   * 用户签到
//   * 
//   * @param req
//   * @return
//   */
//  @RequestMapping(value = "/signin", method = RequestMethod.POST)
//  public @ResponseBody BaseResponse signin(@RequestBody BaseRequest req) {
//    BaseResponse response = new BaseResponse();
//    Long userId = req.getUserId();
//    String token = req.getToken();
//
//    // 验证登录token
//    String userToken = endUserService.getEndUserToken(userId);
//    if (!TokenGenerator.isValiableToken(token, userToken)) {
//      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
//      response.setDesc(Message.error("lb.user.token.timeout").getContent());
//      return response;
//    }
//    EndUser endUser = endUserService.find(userId);
//    if (endUser != null
//        && endUser.getSignInTime() != null
//        && (TimeUtils.getDateStart(new Date(), 0)).compareTo(TimeUtils.getDateStart(
//            endUser.getSignInTime(), 0)) == 0) {
//      response.setCode(CommonAttributes.FAIL_SIGNIN);
//      response.setDesc(Message.error("lb.signin.duplicate").getContent());
//    } else {
//      endUser =
//          endUserService.endUserDonate(endUser, "donateSource", DonateSource.SIGNIN,
//              DonateType.MONEY);
//      endUser.setSignInTime(new Date());
//      endUserService.update(endUser);
//      response
//          .setDesc(Message.success("lb.signin.success", endUser.getDonateAmount()).getContent());
//      response.setCode(CommonAttributes.SUCCESS);
//    }
//    String newtoken = TokenGenerator.generateToken(req.getToken());
//    endUserService.createEndUserToken(newtoken, userId);
//    response.setToken(newtoken);
//    return response;
//  }
//
//}
