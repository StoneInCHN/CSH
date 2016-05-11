package com.csh.service;

import com.csh.entity.Coupon;
import com.csh.entity.EndUser;
import com.csh.entity.commonenum.CommonEnum.CouponSendType;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.BaseService;

public interface CouponService extends BaseService<Coupon, Long> {

  /**
   * 根据租户和优惠券发送类型（注册优惠券，绑定租户优惠券）领取优惠券
   * 
   * @param tenantId
   * @param endUser
   * @param redPackageSendType
   */
  void takeCouponBySendType(Long tenantId, EndUser endUser, CouponSendType couponSendType);

  /**
   * 获取可领取优惠券列表
   * 
   * @return
   */
  Page<Coupon> getCouponList(Pageable pageable, Long tenantId);

  /**
   * 用户是否已经领取红包
   * 
   * @param couponId
   * @param endUser
   * @return
   */
  Boolean isUserGetCoupon(Long couponId, EndUser endUser);

}
