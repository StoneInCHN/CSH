package com.csh.dao.impl; 

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.csh.dao.CouponEndUserDao;
import com.csh.entity.CouponEndUser;
import com.csh.entity.EndUser;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.utils.TimeUtils;
@Repository("couponEndUserDaoImpl")
public class CouponEndUserDaoImpl extends  BaseDaoImpl<CouponEndUser,Long> implements CouponEndUserDao {

	 @Override
	  public Page<CouponEndUser> getMyCouponsForPay(Pageable pageable, EndUser endUser, Long serviceId) {
		 Map<String, Object> paramMap = new HashMap<String, Object>();
		 String jpql =
			        "select couponEndUser from CouponEndUser couponEndUser where couponEndUser.endUser = :endUser and couponEndUser.isUsed=:isUsed and couponEndUser.overDueTime >= :overDueTime and couponEndUser.coupon.carServices.id=:serviceId order by couponEndUser.overDueTime asc";
		    paramMap.put("endUser", endUser);
		    paramMap.put("isUsed", false);
		    paramMap.put("overDueTime", TimeUtils.formatDate2Day(new Date()));
		    paramMap.put("serviceId", serviceId);
		    
		 return findPageCustomized(pageable, jpql, paramMap);
	  }
}