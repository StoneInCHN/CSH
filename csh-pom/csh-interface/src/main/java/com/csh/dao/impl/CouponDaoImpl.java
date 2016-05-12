package com.csh.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.csh.dao.CouponDao;
import com.csh.entity.Coupon;
import com.csh.entity.CouponEndUser;
import com.csh.entity.EndUser;
import com.csh.entity.commonenum.CommonEnum.CouponSendType;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.utils.TimeUtils;


@Repository("couponDaoImpl")
public class CouponDaoImpl extends BaseDaoImpl<Coupon, Long> implements CouponDao {

  @Override
  public Page<Coupon> getCouponList(Pageable pageable, Long tenantId) {
    Map<String, Object> paramMap = new HashMap<String, Object>();
    String jpql =
        "select coupon from Coupon coupon where coupon.isEnabled = :isEnabled and coupon.sendType=:sendType and coupon.deadlineTime >= :deadlineTime";
    if (tenantId == null) {
      jpql += " and coupon.tenantID is null";
    } else {
      jpql += " and (coupon.tenantID is null or coupon.tenantID = :tenantId)";
      paramMap.put("tenantId", tenantId);
    }
    jpql += " order by coupon.overDueTime asc";
    paramMap.put("isEnabled", true);
    paramMap.put("sendType", CouponSendType.NORMAL);
    paramMap.put("deadlineTime", TimeUtils.formatDate2Day(new Date()));
    return findPageCustomized(pageable, jpql, paramMap);
  }


  @Override
  public Page<CouponEndUser> getMyCouponsForPay(Pageable pageable, EndUser endUser, Long serviceId) {
    // TODO Auto-generated method stub
    return null;
  }

}
