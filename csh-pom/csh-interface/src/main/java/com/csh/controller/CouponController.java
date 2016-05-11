package com.csh.controller;

import java.math.BigDecimal;
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
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.Coupon;
import com.csh.entity.CouponEndUser;
import com.csh.entity.EndUser;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.base.PageResponse;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.base.ResponseOne;
import com.csh.json.request.CouponRequest;
import com.csh.json.request.WalletRequest;
import com.csh.service.CouponEndUserService;
import com.csh.service.CouponService;
import com.csh.service.EndUserService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.PayUtil;
import com.csh.utils.TokenGenerator;
import com.csh.utils.ToolsUtils;



@Controller("CouponController")
@RequestMapping("/coupon")
public class CouponController extends MobileBaseController {


  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "couponServiceImpl")
  private CouponService couponService;

  @Resource(name = "couponEndUserServiceImpl")
  private CouponEndUserService couponEndUserService;


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

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    String tradeNo = ToolsUtils.generateRecordNo("000000");
    if (PaymentType.WECHAT.equals(paymentType)) {
      try {
        BigDecimal weChatAmount = amount.multiply(new BigDecimal(100));
        response =
            PayUtil.wechat(tradeNo, "wallet charge in", httpReq.getRemoteAddr(), "0",
                weChatAmount.intValue() + "");
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
   * 我的优惠劵列表
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/myCoupon", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> myCoupon(
      @RequestBody CouponRequest request) {

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

    Long tenantId = null;
    EndUser endUser = endUserService.find(userId);
    if (endUser.getDefaultVehicle() != null) {
      tenantId = endUser.getDefaultVehicle().getTenantID();
    }

    Pageable pageable = new Pageable(request.getPageNumber(), request.getPageSize());
    Page<CouponEndUser> coupons = couponEndUserService.getMyCoupons(pageable, endUser);

    String[] properties = {"id", "isOverDue", "overDueTime", "remark", "isUsed", "coupon.amount"};
    List<Map<String, Object>> map =
        FieldFilterUtils.filterCollectionMap(properties, coupons.getContent());

    response.setMsg(map);

    PageResponse page = new PageResponse();
    page.setPageNumber(request.getPageNumber());
    page.setPageSize(request.getPageSize());
    page.setTotal((int) coupons.getTotal());
    response.setPage(page);
    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 可领取优惠券列表
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/availableList", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> availableList(
      @RequestBody CouponRequest request) {

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

    Long tenantId = null;
    EndUser endUser = endUserService.find(userId);
    if (endUser.getDefaultVehicle() != null) {
      tenantId = endUser.getDefaultVehicle().getTenantID();
    }

    Pageable pageable = new Pageable(request.getPageNumber(), request.getPageSize());
    Page<Coupon> coupons = couponService.getCouponList(pageable, tenantId);

    String[] properties = {"id", "amount", "overDueTime", "remark", "remainNum"};
    List<Map<String, Object>> map =
        FieldFilterUtils.filterCollectionMap(properties, coupons.getContent());
    for (Map<String, Object> couponMap : map) {
      Boolean isGet = couponService.isUserGetCoupon((Long) couponMap.get("id"), endUser);
      couponMap.put("isGet", isGet);
    }
    response.setMsg(map);

    PageResponse page = new PageResponse();
    page.setPageNumber(request.getPageNumber());
    page.setPageSize(request.getPageSize());
    page.setTotal((int) coupons.getTotal());
    response.setPage(page);
    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

}
