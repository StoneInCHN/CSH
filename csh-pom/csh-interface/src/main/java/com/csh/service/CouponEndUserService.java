package com.csh.service;

import java.util.List;

import com.csh.entity.Coupon;
import com.csh.entity.CouponEndUser;
import com.csh.entity.EndUser;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.BaseService;

public interface CouponEndUserService extends BaseService<CouponEndUser, Long> {

  /**
   * 我的优惠券列表
   * 
   * @param pageable
   * @param endUser
   * @return
   */
  Page<CouponEndUser> getMyCoupons(Pageable pageable, EndUser endUser);

  /**
   * 支付时可用的优惠券列表
   * 
   * @param pageable
   * @param endUser
   * @return
   */
  List<CouponEndUser> getMyCouponsForPay(Pageable pageable, EndUser endUser, Long serviceId);


  /**
   * 手机用户领取优惠券
   * 
   * @param coupon
   * @param endUser
   */
  Boolean getCoupon(Coupon coupon, EndUser endUser);
}
