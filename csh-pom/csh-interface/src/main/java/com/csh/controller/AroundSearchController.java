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
import com.csh.json.base.ResponseMultiple;
import com.csh.json.request.AroundSearchRequest;
import com.csh.service.EndUserService;
import com.csh.utils.TelematicsUtils;
import com.csh.utils.TokenGenerator;



@Controller("AroundSearchController")
@RequestMapping("/aroundSearch")
public class AroundSearchController extends MobileBaseController {


  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;


  /**
   * 提交意见反馈
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/keyWordSearch", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> aroundSearch(
      @RequestBody AroundSearchRequest req) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Long userId = req.getUserId();
    String token = req.getToken();
    String lon = req.getLongitude();
    String lat = req.getLatitude();
    String keyWord = req.getKeyWord();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    List<Map<String, Object>> resMaps = TelematicsUtils.baiduTelematics(lon, lat, keyWord);
    response.setMsg(resMaps);

    String newtoken = TokenGenerator.generateToken(req.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

}
