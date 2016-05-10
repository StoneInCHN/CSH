package com.csh.service.impl; 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.Coupon;
import com.csh.entity.CouponEndUser;
import com.csh.entity.EndUser;
import com.csh.entity.commonenum.CommonEnum.CouponOverDueType;
import com.csh.entity.commonenum.CommonEnum.CouponSendType;
import com.csh.dao.CouponDao;
import com.csh.dao.CouponEndUserDao;
import com.csh.service.CouponService;
import com.csh.utils.TimeUtils;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("couponServiceImpl")
public class CouponServiceImpl extends BaseServiceImpl<Coupon,Long> implements CouponService {

	
	  @Resource(name="couponDaoImpl")
	  private CouponDao couponDao;
	  
	  @Resource(name="couponEndUserDaoImpl")
	  private CouponEndUserDao couponEndUserDao;
	  
      @Resource(name="couponDaoImpl")
      public void setBaseDao(CouponDao couponDao) {
         super.setBaseDao(couponDao);
  }

	@Override
	public void takeCouponBySendType(Long tenantId, EndUser endUser,
			CouponSendType couponSendType) {

		List<Filter> filters = new ArrayList<Filter>();
		Filter typeFilter = new Filter("sendType", Operator.eq, couponSendType);
		Filter flagFilter = new Filter("sendFlag", Operator.eq, true);
		Filter tenantFilter = new Filter();
		tenantFilter.setProperty("tenantID");
		if (tenantId!=null) {
			tenantFilter.setOperator(Operator.eq);
			tenantFilter.setValue(tenantId);
		}else {
			tenantFilter.setOperator(Operator.isNull);
		}
		filters.add(typeFilter);
		filters.add(tenantFilter);
		filters.add(flagFilter);
		List<Coupon> coupons = couponDao.findList(null, null, filters, null);
		if (coupons != null && coupons.size()==1) {
			Coupon coupon = coupons.get(0);
			CouponEndUser couponEndUser = new CouponEndUser();
			couponEndUser.setEndUser(endUser);
			couponEndUser.setCoupon(coupon);
			couponEndUser.setIsOverDue(false);
			couponEndUser.setIsUsed(false);
			if (coupon.getOverDueType().equals(CouponOverDueType.BYDATE)) {
				couponEndUser.setOverDueTime(coupon.getOverDueTime());
			}else if (coupon.getOverDueType().equals(CouponOverDueType.BYDAY)) {
				Date overDueTime = TimeUtils.addDays(coupon.getOverDueDay(), new Date());
				couponEndUser.setOverDueTime(overDueTime);
			}
			couponEndUserDao.merge(couponEndUser);		
		}
	
		
	}
}