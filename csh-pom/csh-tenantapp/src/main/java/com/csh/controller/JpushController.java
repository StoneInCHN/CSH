package com.csh.controller;

import java.util.ArrayList;
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
import com.csh.entity.TenantAccount;
import com.csh.entity.commonenum.CommonEnum.AppPlatform;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.json.base.ResponseOne;
import com.csh.json.request.JpushRequest;
import com.csh.service.TenantAccountService;
import com.csh.utils.TokenGenerator;



@Controller("JpushController")
@RequestMapping("/jpush")
public class JpushController extends MobileBaseController {

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  /**
   * 初始化jpush reg id
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/setRegId", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseOne<Map<String, Object>> setRegId(@RequestBody JpushRequest req) {

    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
    Long userId = req.getUserId();
    String token = req.getToken();
    String jPushRegId = req.getRegId();
    AppPlatform appPlatform = req.getAppPlatform();

    // 验证登录token
    String userToken = tenantAccountService.createTenantUserToken(token, userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }
    tenantAccountService.createTenantUserAppPlatform(appPlatform, userId);

    TenantAccount tenantAccount = tenantAccountService.find(userId);
    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter("jpushRegId", Operator.eq, jPushRegId);
    filters.add(filter);
    List<TenantAccount> updateUsers = new ArrayList<TenantAccount>();
    List<TenantAccount> users = tenantAccountService.findList(null, filters, null);
    if (!CollectionUtils.isEmpty(users)) {
      for (TenantAccount user : users) {
        if (!user.getId().equals(tenantAccount.getId()) && jPushRegId.equals(user.getjpushRegId())) {
          user.setjpushRegId(null);
          updateUsers.add(user);
        }
      }
    }

    tenantAccount.setjpushRegId(jPushRegId);
    updateUsers.add(tenantAccount);
    tenantAccountService.update(updateUsers);

    if (LogUtil.isDebugEnabled(JpushController.class)) {
      LogUtil.debug(JpushController.class, "setRegId",
          "init Tenant User Jpush reg ID.UserName: %s, regId: %s, appPlatform: %s",
          tenantAccount.getUserName(), jPushRegId, appPlatform);
    }

    // Map<String, Object> map = new HashMap<String, Object>();
    //
    // response.setMsg(map);
    String newtoken = TokenGenerator.generateToken(req.getToken());
    tenantAccountService.createTenantUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }
}
