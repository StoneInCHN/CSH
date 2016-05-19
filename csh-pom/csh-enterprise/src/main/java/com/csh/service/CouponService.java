package com.csh.service; 

import com.csh.entity.Coupon;
import com.csh.framework.service.BaseService;

public interface CouponService extends BaseService<Coupon,Long>{

  void saveCoupon (Coupon coupon);

}