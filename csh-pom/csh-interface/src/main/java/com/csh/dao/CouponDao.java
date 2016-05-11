package com.csh.dao;

import com.csh.entity.Coupon;
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
}
