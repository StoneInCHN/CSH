package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.CarWashingCouponEndUser;
import com.csh.dao.CarWashingCouponEndUserDao;
import com.csh.service.CarWashingCouponEndUserService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("carWashingCouponEndUserServiceImpl")
public class CarWashingCouponEndUserServiceImpl extends BaseServiceImpl<CarWashingCouponEndUser,Long> implements CarWashingCouponEndUserService {

      @Resource(name="carWashingCouponEndUserDaoImpl")
      public void setBaseDao(CarWashingCouponEndUserDao carWashingCouponEndUserDao) {
         super.setBaseDao(carWashingCouponEndUserDao);
  }
}