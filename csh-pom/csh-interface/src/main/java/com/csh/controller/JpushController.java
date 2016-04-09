package com.csh.controller;

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
import com.csh.entity.EndUser;
import com.csh.json.base.BaseResponse;
import com.csh.json.request.JpushRequest;
import com.csh.service.EndUserService;
import com.csh.utils.TokenGenerator;



@Controller("JpushController")
@RequestMapping("/jpush")
public class JpushController extends MobileBaseController {


  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;



  /**
   * 初始化jpush reg id
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/setRegId", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse setRegId(@RequestBody JpushRequest req) {

    BaseResponse response = new BaseResponse();
    Long userId = req.getUserId();
    String token = req.getToken();
    String jPushRegId = req.getRegId();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    endUser.setjpushRegId(jPushRegId);
    endUserService.update(endUser);

    if (LogUtil.isDebugEnabled(JpushController.class)) {
      LogUtil.debug(JpushController.class, "Update",
          "init User Jpush reg ID.UserName: %s, regId: %s,", endUser.getUserName(), jPushRegId);
    }

    String newtoken = TokenGenerator.generateToken(req.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

}
