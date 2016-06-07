package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.CarWashingCouponEndUserDao;
import com.csh.entity.CarWashingCouponEndUser;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("carWashingCouponEndUserDaoImpl")
public class CarWashingCouponEndUserDaoImpl extends BaseDaoImpl<CarWashingCouponEndUser, Long>
    implements CarWashingCouponEndUserDao {

}
