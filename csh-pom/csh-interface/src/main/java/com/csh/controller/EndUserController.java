package com.csh.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.CommonAttributes;
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.EndUser;
import com.csh.json.base.BaseRequest;
import com.csh.json.base.BaseResponse;
import com.csh.json.base.ResponseOne;
import com.csh.service.EndUserService;
import com.csh.utils.TokenGenerator;



@Controller("endUserController")
@RequestMapping("/endUser")
public class EndUserController extends MobileBaseController {

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

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
   * 用户登录
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public @ResponseBody ResponseOne<EndUser> login(@RequestBody BaseRequest req) {
    ResponseOne<EndUser> response = new ResponseOne<EndUser>();
    String token = TokenGenerator.generateToken();
    endUserService.createEndUserToken(token, req.getUserId());
    response.setToken(token);
    return response;
  }


}
