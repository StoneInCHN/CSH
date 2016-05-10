package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.Coupon;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.CouponDao;
@Repository("couponDaoImpl")
public class CouponDaoImpl extends  BaseDaoImpl<Coupon,Long> implements CouponDao {

}