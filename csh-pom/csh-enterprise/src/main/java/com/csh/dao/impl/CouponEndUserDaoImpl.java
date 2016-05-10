package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.CouponEndUser;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.CouponEndUserDao;
@Repository("couponEndUserDaoImpl")
public class CouponEndUserDaoImpl extends  BaseDaoImpl<CouponEndUser,Long> implements CouponEndUserDao {

}