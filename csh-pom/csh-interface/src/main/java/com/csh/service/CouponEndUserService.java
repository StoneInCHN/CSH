package com.csh.service;

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
}
