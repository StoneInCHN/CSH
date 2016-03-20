package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.CommonAttributes;
import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.EndUser;
import com.csh.entity.Wallet;
import com.csh.entity.WalletRecord;
import com.csh.entity.commonenum.CommonEnum.BalanceType;
import com.csh.json.base.BaseResponse;
import com.csh.json.request.OrderRequest;
import com.csh.service.EndUserService;
import com.csh.service.WalletService;
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
    WalletRecord walletRecord = new WalletRecord();
    walletRecord.setType(BalanceType.INCOME);
    walletRecord.setMoney(orderRequest.getAmount());
    walletRecord.setWallet(wallet);
    wallet.getWalletRecords().add(walletRecord);
    walletService.update(wallet);
    
    if (LogUtil.isDebugEnabled(BalanceController.class)) {
        LogUtil.debug(BalanceController.class, "Update",
            "User Charge in Wallet.UserName: %s, ChargeAmount: %s,", endUser.getUserName(),orderRequest.getAmount());
    }
    
    String newtoken = TokenGenerator.generateToken(orderRequest.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  
}
