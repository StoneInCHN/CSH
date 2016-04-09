package com.csh.controller;

import java.util.List;
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
import com.csh.entity.EndUser;
import com.csh.entity.MsgEndUser;
import com.csh.json.base.BaseRequest;
import com.csh.json.base.ResponseMultiple;
import com.csh.service.AdvertisementService;
import com.csh.service.EndUserService;
import com.csh.utils.TokenGenerator;

/**
 * Control 广告
 * 
 * @author Andrea
 *
 */
@Controller("advertisementController")
@RequestMapping("/advertisement")
public class AdvertisementController extends MobileBaseController {

  @Resource(name = "advertisementServiceImpl")
  private AdvertisementService advertisementService;

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;


  /**
   * 获取广告
   * 
   * @return
   */
  @RequestMapping(value = "/getAdvImage", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> getAdvImage(
      @RequestBody BaseRequest request) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();

    Long userId = request.getUserId();
    String token = request.getToken();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    List<Map<String, Object>> advMap = advertisementService.getAdvBanner(userId);
    response.setMsg(advMap);

    Integer count = 0; // 未读消息数
    EndUser endUser = endUserService.find(userId);
    if (endUser != null) {
      for (MsgEndUser msgEndUser : endUser.getMsgEndUsers()) {
        if (!msgEndUser.getIsRead()) {
          count++;
        }
      }
    }

    response.setDesc(count.toString());

    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }
}
