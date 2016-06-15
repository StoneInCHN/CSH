package com.csh.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.csh.dao.CouponEndUserDao;
import com.csh.entity.CouponEndUser;
import com.csh.entity.EndUser;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;

@Repository("couponEndUserDaoImpl")
public class CouponEndUserDaoImpl extends BaseDaoImpl<CouponEndUser, Long> implements
    CouponEndUserDao {

  @Override
  public Page<CouponEndUser> getMyCoupons(Pageable pageable, EndUser endUser, Long tenantId) {
    Map<String, Object> paramMap = new HashMap<String, Object>();
    String jpql =
        "select couponEndUser from CouponEndUser couponEndUser where couponEndUser.isUsed = :isUsed and couponEndUser.endUser=:endUser";
    if (tenantId == null) {
      jpql += " and couponEndUser.coupon.tenantID is null";
    } else {
      jpql +=
          " and (couponEndUser.coupon.tenantID is null or couponEndUser.coupon.tenantID = :tenantId)";
      paramMap.put("tenantId", tenantId);
    }
    jpql += " order by coupon.overDueTime asc";
    paramMap.put("isUsed", false);
    paramMap.put("endUser", endUser);
    return findPageCustomized(pageable, jpql, paramMap);
  }


}
