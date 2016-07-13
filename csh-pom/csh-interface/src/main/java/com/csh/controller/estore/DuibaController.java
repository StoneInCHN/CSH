package com.csh.controller.estore;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.csh.entity.commonenum.CommonEnum.WalletType;
import com.csh.json.base.BaseRequest;
import com.csh.json.base.BaseResponse;
import com.csh.service.EndUserService;
import com.csh.service.WalletRecordService;
import com.csh.service.WalletService;
import com.csh.utils.TokenGenerator;
import com.csh.utils.duiba.CreditConsumeResult;
import com.csh.utils.duiba.CreditTool;
import com.csh.utils.duiba.SignTool;


/**
 * Controller - 电商订单
 * 
 * @author sujinxuan
 *
 */
@Controller("DuibaController")
@RequestMapping("/estore/duiba")
public class DuibaController extends MobileBaseController {

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "walletServiceImpl")
  private WalletService walletService;

  @Resource(name = "walletRecordServiceImpl")
  private WalletRecordService walletRecordService;

  /**
   * 获取兑吧登录URL
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/getLoginUrl", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse getLoginUrl(@RequestBody BaseRequest req) {

    BaseResponse response = new BaseResponse();

    String token = req.getToken();
    Long userId = req.getUserId();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    CreditTool tool = new CreditTool(setting.getDuibaAppKey(), setting.getDuibaAppSecret());
    Map<String, String> params = new HashMap<String, String>();
    params.put("uid", endUser.getUserName());
    params.put("credits", endUser.getWallet().getScore().setScale(0, BigDecimal.ROUND_DOWN)
        .toString());
    // if(redirect!=null){
    // //redirect是目标页面地址，默认积分商城首页是：http://www.duiba.com.cn/chome/index
    // //此处请设置成一个外部传进来的参数，方便运营灵活配置
    // params.put("redirect",redirect);
    // }
    String url = tool.buildUrlWithSign(setting.getDuibaLoginUrl(), params);
    response.setDesc(url);
    String newtoken = TokenGenerator.generateToken(token);
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 兑吧用户积分扣除回调接口
   * 
   * @param req
   * @return
   * @throws IOException
   */
  @RequestMapping(value = "/notify_score", method = RequestMethod.GET)
  public @ResponseBody String notify_score(HttpServletRequest request) throws Exception {
    Map<String, String[]> map = request.getParameterMap();
    Map<String, String> data = new HashMap<String, String>();
    for (String key : map.keySet()) {
      data.put(key, new String(map.get(key)[0].getBytes("ISO-8859-1"), "UTF-8"));
    }
    if (LogUtil.isDebugEnabled(DuibaController.class)) {
      LogUtil.debug(DuibaController.class, "notify_score", "Dui Ba score notify. response: %s",
          data);
    }
    if (SignTool.signVerify(setting.getDuibaAppSecret(), data)) {
      // 用户id
      String uid = data.get("uid");
      // 本次兑换扣除的积分
      String credits = data.get("credits");
      // 兑吧订单号
      String orderNum = data.get("orderNum");

      EndUser endUser = endUserService.findByUserName(uid);
      Wallet wallet = endUser.getWallet();
      wallet.setScore(wallet.getScore().subtract(new BigDecimal(credits)));
      WalletRecord walletRecord = new WalletRecord();
      walletRecord.setBalanceType(BalanceType.OUTCOME);
      walletRecord.setWalletType(WalletType.SCORE);
      walletRecord.setScore(new BigDecimal(credits));
      walletRecord.setRecordNo(orderNum);
      walletRecord.setRemark(Message.success("csh.wallet.duiba.score").getContent());
      walletRecord.setWallet(wallet);
      wallet.getWalletRecords().add(walletRecord);
      walletService.update(wallet);

      CreditConsumeResult result = new CreditConsumeResult(true);
      result.setBizId(orderNum);
      result.setCredits(new Long(wallet.getScore().setScale(0, BigDecimal.ROUND_DOWN).toString()));
      if (LogUtil.isDebugEnabled(DuibaController.class)) {
        LogUtil.debug(DuibaController.class, "notify_score",
            "Dui Ba score notify success. return to Dui ba: %s", result.toString());
      }
      return result.toString();

    } else {
      if (LogUtil.isDebugEnabled(DuibaController.class)) {
        LogUtil.debug(DuibaController.class, "notify_score",
            "Dui Ba score notify Verify sign failed.");
      }
      CreditConsumeResult result = new CreditConsumeResult(false);
      return result.toString();
    }

  }


  /**
   * 兑吧订单兑换成功/失败消息的接收回调接口
   * 
   * @param req
   * @return
   * @throws IOException
   */
  @RequestMapping(value = "/notify_order", method = RequestMethod.GET)
  public @ResponseBody String notify_order(HttpServletRequest request) throws Exception {
    Map<String, String[]> map = request.getParameterMap();
    Map<String, String> data = new HashMap<String, String>();
    for (String key : map.keySet()) {
      data.put(key, new String(map.get(key)[0].getBytes("ISO-8859-1"), "UTF-8"));
    }
    if (LogUtil.isDebugEnabled(DuibaController.class)) {
      LogUtil.debug(DuibaController.class, "notify_order", "Dui Ba order notify. response: %s",
          data);
    }
    if (SignTool.signVerify(setting.getDuibaAppSecret(), data)) {
      // 兑换是否成功
      String success = data.get("success");
      // 兑吧订单号
      String orderNum = data.get("orderNum");

      if (Boolean.valueOf(success)) {
        if (LogUtil.isDebugEnabled(DuibaController.class)) {
          LogUtil.debug(DuibaController.class, "notify_order", "Dui Ba order notify success.");
        }
      } else {
        if (LogUtil.isDebugEnabled(DuibaController.class)) {
          LogUtil.debug(DuibaController.class, "notify_order",
              "Dui Ba order notify failed. rollback data, recordNo: %s", orderNum);
        }
        WalletRecord walletRecord = walletRecordService.getRecordByOrderNum(orderNum);
        if (walletRecord != null) {
          Wallet wallet = walletRecord.getWallet();
          wallet.setScore(wallet.getScore().add(walletRecord.getScore()));
          walletRecord.setWallet(null);
          wallet.getWalletRecords().remove(walletRecord);
          walletService.update(wallet);
        }

      }
    } else {
      if (LogUtil.isDebugEnabled(DuibaController.class)) {
        LogUtil.debug(DuibaController.class, "notify_order",
            "Dui Ba order notify Verify sign failed.");
      }
    }
    return "ok";
  }

}
