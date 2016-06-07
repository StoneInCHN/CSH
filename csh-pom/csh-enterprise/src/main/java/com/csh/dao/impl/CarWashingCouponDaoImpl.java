package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.CarWashingCouponDao;
import com.csh.entity.CarWashingCoupon;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("carWashingCouponDaoImpl")
public class CarWashingCouponDaoImpl extends BaseDaoImpl<CarWashingCoupon, Long> implements
    CarWashingCouponDao {

}
