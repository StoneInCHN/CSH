package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.CarWashingCoupon;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.CarWashingCouponDao;
@Repository("carWashingCouponDaoImpl")
public class CarWashingCouponDaoImpl extends  BaseDaoImpl<CarWashingCoupon,Long> implements CarWashingCouponDao {

}