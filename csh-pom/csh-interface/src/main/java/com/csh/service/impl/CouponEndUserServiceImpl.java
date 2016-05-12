package com.csh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.common.log.LogUtil;
import com.csh.controller.CouponController;
import com.csh.dao.CouponDao;
import com.csh.dao.CouponEndUserDao;
import com.csh.entity.CarService;
import com.csh.entity.Coupon;
import com.csh.entity.CouponEndUser;
import com.csh.entity.EndUser;
import com.csh.entity.commonenum.CommonEnum.CouponOverDueType;
import com.csh.entity.commonenum.CommonEnum.CouponType;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.ordering.Ordering;
import com.csh.framework.ordering.Ordering.Direction;
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

  @Resource(name = "couponDaoImpl")
  private CouponDao couponDao;

  @Resource(name = "couponEndUserDaoImpl")
  public void setBaseDao(CouponEndUserDao couponEndUserDao) {
    super.setBaseDao(couponEndUserDao);
  }

  @Override
  public Page<CouponEndUser> getMyCoupons(Pageable pageable, EndUser endUser) {
    List<Filter> filters = new ArrayList<Filter>();
    Filter userFilter = new Filter("endUser", Operator.eq, endUser);
    Filter isUsedFilter = new Filter("isUsed", Operator.eq, false);
    filters.add(userFilter);
    filters.add(isUsedFilter);
    pageable.setFilters(filters);
    pageable.setOrderProperty("overDueTime");
    pageable.setOrderDirection(Direction.desc);
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

  @Override
  public List<CouponEndUser> getMyCouponsForPay(Pageable pageable, EndUser endUser, Long serviceId) {
    List<Filter> filters = new ArrayList<Filter>();
    Filter userFilter = new Filter("endUser", Operator.eq, endUser);
    Filter isUsedFilter = new Filter("isUsed", Operator.eq, false);
    Filter overDueFilter =
        new Filter("overDueTime", Operator.ge, TimeUtils.formatDate2Day(new Date()));
    filters.add(userFilter);
    filters.add(isUsedFilter);
    filters.add(overDueFilter);
    List<Ordering> orders = new ArrayList<Ordering>();
    Ordering ordering = new Ordering("overDueTime", Direction.asc);
    orders.add(ordering);
    List<CouponEndUser> list = couponEndUserDao.findList(null, null, filters, orders);
    List<CouponEndUser> coupons = new ArrayList<CouponEndUser>();
    for (CouponEndUser couponEndUser : list) {
      Coupon coupon = couponEndUser.getCoupon();
      if (coupon.getIsEnabled()) {
        if (CouponType.COMMON.equals(coupon.getType())) {
          coupons.add(couponEndUser);
        } else if (CouponType.SPECIFY.equals(coupon.getType())) {
          for (CarService carService : coupon.getCarServices()) {
            if (serviceId.equals(carService.getId())) {
              coupons.add(couponEndUser);
              break;
            }
          }
        }
      }

    }
    return coupons;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public Boolean getCoupon(Coupon coupon, EndUser endUser) {
    Boolean canGet = updateCouponCounts(coupon, endUser);
    if (!canGet) {
      return false;
    }
    CouponEndUser couponEndUser = new CouponEndUser();
    couponEndUser.setEndUser(endUser);
    couponEndUser.setCoupon(coupon);
    couponEndUser.setIsOverDue(false);
    couponEndUser.setIsUsed(false);
    if (coupon.getOverDueType().equals(CouponOverDueType.BYDATE)) {
      couponEndUser.setOverDueTime(coupon.getOverDueTime());
    } else if (coupon.getOverDueType().equals(CouponOverDueType.BYDAY)) {
      Date overDueTime = TimeUtils.addDays(coupon.getOverDueDay(), new Date());
      couponEndUser.setOverDueTime(overDueTime);
    }
    couponEndUserDao.persist(couponEndUser);
    return true;
  }

  /**
   * 更新优惠券剩余数量
   * 
   * @param coupon
   */
  private synchronized Boolean updateCouponCounts(Coupon coupon, EndUser endUser) {
    Integer count = coupon.getRemainNum();
    if (count == null || count < 1) {
      return false;
    }
    coupon.setRemainNum(count - 1);
    if (LogUtil.isDebugEnabled(CouponEndUserServiceImpl.class)) {
      LogUtil.debug(CouponController.class, "updateCouponCounts",
          "update coupon count for User with UserName: %s,UserId: %s,couponId: %s,remainNum: %s",
          endUser.getUserName(), endUser.getId(), coupon.getId(), coupon.getRemainNum());
    }
    couponDao.merge(coupon);
    return true;
  }

}
