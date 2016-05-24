package com.csh.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
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
import com.csh.entity.SystemConfig;
import com.csh.entity.Wallet;
import com.csh.entity.WalletRecord;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.entity.commonenum.CommonEnum.SystemConfigKey;
import com.csh.entity.commonenum.CommonEnum.WalletType;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.base.BaseRequest;
import com.csh.json.base.BaseResponse;
import com.csh.json.base.PageResponse;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.base.ResponseOne;
import com.csh.json.request.WalletRequest;
import com.csh.service.AdvanceDepositsService;
import com.csh.service.EndUserService;
import com.csh.service.SystemConfigService;
import com.csh.service.WalletRecordService;
import com.csh.service.WalletService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.PayUtil;
import com.csh.utils.TimeUtils;
import com.csh.utils.TokenGenerator;



@Controller("BalanceController")
@RequestMapping("/balance")
public class BalanceController extends MobileBaseController {


  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "walletServiceImpl")
  private WalletService walletService;

  @Resource(name = "walletRecordServiceImpl")
  private WalletRecordService walletRecordService;

  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;

  @Resource(name = "advanceDepositsServiceImpl")
  private AdvanceDepositsService advanceDepositsService;


  /**
   * 充值
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/chargeIn", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseOne<Map<String, Object>> chargeIn(
      @RequestBody WalletRequest walletReq, HttpServletRequest httpReq) {

    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
    Long userId = walletReq.getUserId();
    String token = walletReq.getToken();
    PaymentType paymentType = walletReq.getPaymentType();
    BigDecimal amount = walletReq.getAmount();
    String chargeType = walletReq.getChargeType();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    String tradeNo = TimeUtils.getLongDateStr(new Date()) + "000000";
    if (PaymentType.WECHAT.equals(paymentType)) {
      try {
        BigDecimal weChatAmount = amount.multiply(new BigDecimal(100));
        response =
            PayUtil.wechat(chargeType + tradeNo + "_" + userId.toString(), "wallet charge in",
                httpReq.getRemoteAddr(), "0", weChatAmount.intValue() + "");
      } catch (DocumentException e) {
        e.printStackTrace();
      }
    } else {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("out_trade_no", tradeNo);
      response.setMsg(map);
      response.setCode(CommonAttributes.SUCCESS);
    }

    String newtoken = TokenGenerator.generateToken(walletReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    return response;
  }


  /**
   * 钱包充值余额call back
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/walletCharge", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse walletCharge(@RequestBody WalletRequest walletRequest) {

    BaseResponse response = new BaseResponse();
    Long userId = walletRequest.getUserId();
    String token = walletRequest.getToken();
    String recordNo = walletRequest.getRecordNo();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    walletService.updateWallet(userId, walletRequest.getAmount(), recordNo);

    if (LogUtil.isDebugEnabled(BalanceController.class)) {
      LogUtil.debug(BalanceController.class, "walletCharge",
          "User charge in call back for Wallet.UserId: %s, ChargeAmount: %s,", userId.toString(),
          walletRequest.getAmount());
    }

    String newtoken = TokenGenerator.generateToken(walletRequest.getToken());
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


  /**
   * 按类别查询钱包明细
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/walletRecordByType", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> walletRecordByType(
      @RequestBody WalletRequest request) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Long userId = request.getUserId();
    String token = request.getToken();
    WalletType walletType = request.getWalletType();
    Long walletId = request.getWalletId();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    Wallet wallet = walletService.find(walletId);
    List<Filter> filters = new ArrayList<Filter>();
    Filter walletFilter = new Filter("wallet", Operator.eq, wallet);
    Filter typeFilter = new Filter("walletType", Operator.eq, walletType);
    filters.add(walletFilter);
    filters.add(typeFilter);
    Pageable pageable = new Pageable(request.getPageNumber(), request.getPageSize());
    pageable.setFilters(filters);
    Page<WalletRecord> walletRecords = walletRecordService.findPage(pageable);

    String[] properties =
        {"id", "createDate", "money", "redPacket", "score", "balanceType", "remark"};
    List<Map<String, Object>> map =
        FieldFilterUtils.filterCollectionMap(properties, walletRecords.getContent());
    response.setMsg(map);

    PageResponse page = new PageResponse();
    page.setPageNumber(request.getPageNumber());
    page.setPageSize(request.getPageSize());
    page.setTotal((int) walletRecords.getTotal());
    response.setPage(page);
    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  /**
   * 购买设备充值页面
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/purDevicePage", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseOne<Map<String, Object>> purDevicePage(
      @RequestBody BaseRequest walletReq) {

    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
    Long userId = walletReq.getUserId();
    String token = walletReq.getToken();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    SystemConfig devicePrice =
        systemConfigService.getConfigByKey(SystemConfigKey.DEVICE_PRICE, null);
    if (devicePrice == null || devicePrice.getConfigValue() == null) {
      response.setCode(CommonAttributes.FAIL_COMMON);
      response.setDesc(Message.error("csh.wallet.config.error").getContent());
      return response;
    }

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("devicePrice", devicePrice.getConfigValue());
    response.setMsg(map);
    String newtoken = TokenGenerator.generateToken(walletReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 购买设备充值call back
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/purDeviceCharge", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse purDeviceCharge(@RequestBody WalletRequest request) {

    BaseResponse response = new BaseResponse();
    Long userId = request.getUserId();
    String token = request.getToken();
    // PaymentType paymentType = request.getPaymentType();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }


    SystemConfig devicePrice =
        systemConfigService.getConfigByKey(SystemConfigKey.DEVICE_PRICE, null);

    if (LogUtil.isDebugEnabled(BalanceController.class)) {
      LogUtil.debug(BalanceController.class, "purDeviceCharge",
          "User charge in call back for purchase device.UserId: %s, ChargeAmount: %s,", userId,
          devicePrice.getConfigValue());
    }
    advanceDepositsService.updateAdvanceDeposit(userId,
        new BigDecimal(devicePrice.getConfigValue()));

    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }
}
