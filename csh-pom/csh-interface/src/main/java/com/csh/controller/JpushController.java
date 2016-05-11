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
import com.csh.entity.ApkVersion;
import com.csh.entity.EndUser;
import com.csh.entity.commonenum.CommonEnum.AppPlatform;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.json.base.ResponseOne;
import com.csh.json.request.JpushRequest;
import com.csh.service.ApkVersionService;
import com.csh.service.EndUserService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.TokenGenerator;



@Controller("JpushController")
@RequestMapping("/jpush")
public class JpushController extends MobileBaseController {


  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "apkVersionServiceImpl")
  private ApkVersionService apkVersionService;



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
    Integer versionCode = req.getVersionCode();
    String jPushRegId = req.getRegId();
    AppPlatform appPlatform = req.getAppPlatform();
    
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }
    endUserService.createEndUserAppPlatform(appPlatform, userId);
    System.out.println(endUserService.getEndUserAppPlatform(userId));
    EndUser endUser = endUserService.find(userId);
    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter("jpushRegId", Operator.eq, jPushRegId);
    filters.add(filter);
    List<EndUser> updateUsers = new ArrayList<EndUser>();
    List<EndUser> users = endUserService.findList(null, filters, null);
    if (!CollectionUtils.isEmpty(users)) {
      for (EndUser user : users) {
        if (!user.getId().equals(endUser.getId()) && jPushRegId.equals(user.getjpushRegId())) {
          user.setjpushRegId(null);
          updateUsers.add(user);
        }
      }
    }

    endUser.setjpushRegId(jPushRegId);
    updateUsers.add(endUser);
    endUserService.update(updateUsers);

    if (LogUtil.isDebugEnabled(JpushController.class)) {
      LogUtil.debug(JpushController.class, "Update",
          "init User Jpush reg ID.UserName: %s, regId: %s,", endUser.getUserName(), jPushRegId);
    }

    ApkVersion version = apkVersionService.getNewVersion(versionCode);
    if (version != null) {
      String[] properties = {"id", "versionName", "versionCode", "apkPath", "updateContent"};
      Map<String, Object> map = FieldFilterUtils.filterEntityMap(properties, version);
      response.setMsg(map);
    }
    String newtoken = TokenGenerator.generateToken(req.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

}
