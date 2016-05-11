package com.csh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.CouponEndUserDao;
import com.csh.entity.CouponEndUser;
import com.csh.entity.EndUser;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.CouponEndUserService;
import com.csh.utils.TimeUtils;

@Service("couponEndUserServiceImpl")
public class CouponEndUserServiceImpl extends BaseServiceImpl<CouponEndUser, Long> implements
    CouponEndUserService {

  @Resource(name = "couponEndUserDaoImpl")
  private CouponEndUserDao couponEndUserDao;

  @Resource(name = "couponEndUserDaoImpl")
  public void setBaseDao(CouponEndUserDao couponEndUserDao) {
    super.setBaseDao(couponEndUserDao);
  }

  @Override
  public Page<CouponEndUser> getMyCoupons(Pageable pageable, EndUser endUser) {
    List<Filter> filters = new ArrayList<Filter>();
    Filter userFilter = new Filter("endUser", Operator.eq, endUser);
    filters.add(userFilter);
    pageable.setFilters(filters);
    Page<CouponEndUser> page = couponEndUserDao.findPage(pageable);
    checkOverDue(page.getContent());
    return page;
  }

  private void checkOverDue(List<CouponEndUser> coupons) {
    for (CouponEndUser coupon : coupons) {
      if (!coupon.getIsOverDue() && !coupon.getIsUsed()
          && TimeUtils.daysBetween(coupon.getOverDueTime(), new Date()) >= 1) {
        coupon.setIsOverDue(true);
      }
    }
  }
}
