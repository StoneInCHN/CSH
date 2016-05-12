package com.csh.dao; 
import com.csh.entity.CouponEndUser;
import com.csh.entity.EndUser;
import com.csh.framework.dao.BaseDao;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;

public interface CouponEndUserDao extends  BaseDao<CouponEndUser,Long>{

	/**
	   * 支付时可用的优惠券列表
	   * 
	   * @param pageable
	   * @param endUser
	   * @return
	   */
	  Page<CouponEndUser> getMyCouponsForPay(Pageable pageable, EndUser endUser, Long serviceId);
}