package com.csh.controller;

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
import com.csh.common.log.LogUtil;
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.EndUser;
import com.csh.entity.Wallet;
import com.csh.entity.WalletRecord;
import com.csh.entity.commonenum.CommonEnum.BalanceType;
import com.csh.json.base.BaseRequest;
import com.csh.json.base.BaseResponse;
import com.csh.json.base.ResponseOne;
import com.csh.json.request.OrderRequest;
import com.csh.service.EndUserService;
import com.csh.service.WalletService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.TokenGenerator;



@Controller("BalanceController")
@RequestMapping("/balance")
public class BalanceController extends MobileBaseController {


  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "walletServiceImpl")
  private WalletService walletService;



  /**
   * 钱包充值余额
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/walletCharge", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse walletCharge(@RequestBody OrderRequest orderRequest) {

    BaseResponse response = new BaseResponse();
    Long userId = orderRequest.getUserId();
    String token = orderRequest.getToken();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    Wallet wallet = endUser.getWallet();
    wallet.setBalanceAmount(wallet.getBalanceAmount().add(orderRequest.getAmount()));
    WalletRecord walletRecord = new WalletRecord();
    walletRecord.setType(BalanceType.INCOME);
    walletRecord.setMoney(orderRequest.getAmount());
    walletRecord.setWallet(wallet);
    wallet.getWalletRecords().add(walletRecord);
    walletService.update(wallet);

    if (LogUtil.isDebugEnabled(BalanceController.class)) {
      LogUtil.debug(BalanceController.class, "Update",
          "User Charge in Wallet.UserName: %s, ChargeAmount: %s,", endUser.getUserName(),
          orderRequest.getAmount());
    }

    String newtoken = TokenGenerator.generateToken(orderRequest.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 我的钱包
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/myWallet", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseOne<Map<String, Object>> myWallet(@RequestBody BaseRequest request) {

    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
    Long userId = request.getUserId();
    String token = request.getToken();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    Wallet wallet = endUser.getWallet();

    String[] properties = {"id", "balanceAmount", "giftAmount", "score"};
    Map<String, Object> map = FieldFilterUtils.filterEntityMap(properties, wallet);

    response.setMsg(map);
    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


}
