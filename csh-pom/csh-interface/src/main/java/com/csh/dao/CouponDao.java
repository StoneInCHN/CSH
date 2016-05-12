package com.csh.dao;

import com.csh.entity.Coupon;
import com.csh.entity.CouponEndUser;
import com.csh.entity.EndUser;
import com.csh.framework.dao.BaseDao;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;

public interface CouponDao extends BaseDao<Coupon, Long> {

  /**
   * 获取可领取优惠券列表
   * 
   * @return
   */
  Page<Coupon> getCouponList(Pageable pageable, Long tenantId);

  /**
   * 支付时可用的优惠券列表
   * 
   * @param pageable
   * @param endUser
   * @return
   */
  Page<CouponEndUser> getMyCouponsForPay(Pageable pageable, EndUser endUser, Long serviceId);
}
