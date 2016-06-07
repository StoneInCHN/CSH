package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.CarWashingCouponDao;
import com.csh.entity.CarWashingCoupon;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.CarWashingCouponService;

@Service("carWashingCouponServiceImpl")
public class CarWashingCouponServiceImpl extends BaseServiceImpl<CarWashingCoupon, Long> implements
    CarWashingCouponService {

  @Resource(name = "carWashingCouponDaoImpl")
  public void setBaseDao(CarWashingCouponDao carWashingCouponDao) {
    super.setBaseDao(carWashingCouponDao);
  }



}
