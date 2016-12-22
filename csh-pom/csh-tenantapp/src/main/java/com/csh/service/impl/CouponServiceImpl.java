package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.Coupon;
import com.csh.dao.CouponDao;
import com.csh.service.CouponService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("couponServiceImpl")
public class CouponServiceImpl extends BaseServiceImpl<Coupon,Long> implements CouponService {

      @Resource(name="couponDaoImpl")
      public void setBaseDao(CouponDao couponDao) {
         super.setBaseDao(couponDao);
  }
}