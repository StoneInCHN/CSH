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
import com.csh.common.log.LogUtil;
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.Coupon;
import com.csh.entity.CouponEndUser;
import com.csh.entity.EndUser;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.base.BaseResponse;
import com.csh.json.base.PageResponse;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.request.CouponRequest;
import com.csh.service.CouponEndUserService;
import com.csh.service.CouponService;
import com.csh.service.EndUserService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.TokenGenerator;



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
   * 领取优惠券
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/getCoupon", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse getCoupon(@RequestBody CouponRequest request) {

    BaseResponse response = new BaseResponse();
    Long userId = request.getUserId();
    String token = request.getToken();
    Long couponId = request.getCouponId();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    Coupon coupon = couponService.find(couponId);
    EndUser endUser = endUserService.find(userId);

    if (LogUtil.isDebugEnabled(CouponController.class)) {
      LogUtil.debug(CouponController.class, "save",
          "take coupon for User with UserName: %s,UserId: %s,couponId: %s,couponAmount: %s",
          endUser.getUserName(), endUser.getId(), coupon.getId(), coupon.getAmount());
    }
    Boolean isGet = couponEndUserService.getCoupon(coupon, endUser);
    if (!isGet) {
      response.setCode(CommonAttributes.FAIL_COUPON_NO_REMAIN);
      response.setDesc(Message.error("csh.coupon.no.count").getContent());
      return response;
    }

    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
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
    if (LogUtil.isDebugEnabled(CouponController.class)) {
      LogUtil.debug(CouponController.class, "find",
          "search my coupon list for User with UserName: %s,UserId: %s", endUser.getUserName(),
          endUser.getId());
    }

    Page<CouponEndUser> coupons = couponEndUserService.getMyCoupons(pageable, endUser);

    String[] properties =
        {"id", "isOverDue", "overDueTime", "coupon.remark", "isUsed", "coupon.amount",
            "coupon.type"};
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
   * 获取支付时可用的优惠券
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/getCouponForPay", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> getCouponForPay(
      @RequestBody CouponRequest request) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Long userId = request.getUserId();
    String token = request.getToken();
    Long serviceId = request.getServiceId();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);

    Pageable pageable = new Pageable(request.getPageNumber(), request.getPageSize());
    if (LogUtil.isDebugEnabled(CouponController.class)) {
      LogUtil.debug(CouponController.class, "find",
          "search my coupon list for special service. UserName: %s,CarServiceId: %s",
          endUser.getUserName(), serviceId);
    }

    List<CouponEndUser> coupons =
        couponEndUserService.getMyCouponsForPay(pageable, endUser, serviceId);

    String[] properties = {"id", "overDueTime", "coupon.remark", "coupon.amount", "coupon.type"};
    List<Map<String, Object>> map = FieldFilterUtils.filterCollectionMap(properties, coupons);

    response.setMsg(map);

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
  @RequestMapping(value = "/availableCoupon", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> availableCoupon(
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
    if (LogUtil.isDebugEnabled(CouponController.class)) {
      LogUtil.debug(CouponController.class, "find",
          "search available coupon list for User with UserName: %s,UserId: %s",
          endUser.getUserName(), endUser.getId());
    }
    Page<Coupon> coupons = couponService.getCouponList(pageable, tenantId);

    String[] properties = {"id", "amount", "deadlineTime", "remark", "remainNum", "type"};
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
