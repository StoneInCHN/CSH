package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.CarWashingCouponEndUser;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.CarWashingCouponEndUserDao;
@Repository("carWashingCouponEndUserDaoImpl")
public class CarWashingCouponEndUserDaoImpl extends  BaseDaoImpl<CarWashingCouponEndUser,Long> implements CarWashingCouponEndUserDao {

}