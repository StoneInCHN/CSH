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
import com.csh.entity.FeedBack;
import com.csh.json.base.BaseResponse;
import com.csh.json.request.FeedBackRequest;
import com.csh.service.EndUserService;
import com.csh.service.FeedBackService;
import com.csh.utils.TokenGenerator;



@Controller("FeedBackController")
@RequestMapping("/feedback")
public class FeedBackController extends MobileBaseController {


  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "feedBackServiceImpl")
  private FeedBackService feedBackService;



  /**
   * 提交意见反馈
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse walletCharge(@RequestBody FeedBackRequest feedBackReq) {

    BaseResponse response = new BaseResponse();
    Long userId = feedBackReq.getUserId();
    String token = feedBackReq.getToken();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    FeedBack feedBack = new FeedBack();
    feedBack.setEndUser(endUser);
    feedBack.setContent(feedBackReq.getContent());
    feedBackService.save(feedBack);

    if (LogUtil.isDebugEnabled(FeedBackController.class)) {
      LogUtil.debug(FeedBackController.class, "Save",
          "Save User FeedBack.UserName: %s, Content: %s,", endUser.getUserName(),
          feedBack.getContent());
    }

    String newtoken = TokenGenerator.generateToken(feedBackReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

}
