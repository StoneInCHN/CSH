package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.CouponEndUser;
import com.csh.dao.CouponEndUserDao;
import com.csh.service.CouponEndUserService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("couponEndUserServiceImpl")
public class CouponEndUserServiceImpl extends BaseServiceImpl<CouponEndUser,Long> implements CouponEndUserService {

      @Resource(name="couponEndUserDaoImpl")
      public void setBaseDao(CouponEndUserDao couponEndUserDao) {
         super.setBaseDao(couponEndUserDao);
  }
}