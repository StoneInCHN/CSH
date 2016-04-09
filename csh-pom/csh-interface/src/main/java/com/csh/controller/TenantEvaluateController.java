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
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.EndUser;
import com.csh.json.base.BaseResponse;
import com.csh.json.request.TenantInfoRequest;
import com.csh.service.EndUserService;
import com.csh.service.TenantEvaluateService;
import com.csh.utils.TokenGenerator;

/**
 * Control 商家评分
 * 
 * @author Andrea
 *
 */
@Controller("tenantEvaluateController")
@RequestMapping("/tenantEvaluate")
public class TenantEvaluateController extends MobileBaseController {

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "tenantEvaluateServiceImpl")
  private TenantEvaluateService tenantEvaluateService;


  /**
   * 评价
   * 
   * @return
   */
  @RequestMapping(value = "/doRate", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse doRate(@RequestBody TenantInfoRequest renantReq) {

    BaseResponse response = new BaseResponse();

    Long userId = renantReq.getUserId();
    String token = renantReq.getToken();
    Long tenantId = renantReq.getTenantId();
    Integer score = renantReq.getScore();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    tenantEvaluateService.doRateForTenant(endUser, tenantId, score);

    String newtoken = TokenGenerator.generateToken(renantReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }
}
