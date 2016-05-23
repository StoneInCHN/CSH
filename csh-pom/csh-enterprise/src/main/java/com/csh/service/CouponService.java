package com.csh.service; 

import com.csh.entity.Coupon;
import com.csh.entity.MessageInfo;
import com.csh.framework.service.BaseService;

public interface CouponService extends BaseService<Coupon,Long>{

  MessageInfo saveCoupon (Coupon coupon);

}