package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.CouponEndUserDao;
import com.csh.entity.CouponEndUser;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("couponEndUserDaoImpl")
public class CouponEndUserDaoImpl extends BaseDaoImpl<CouponEndUser, Long> implements
    CouponEndUserDao {


}
