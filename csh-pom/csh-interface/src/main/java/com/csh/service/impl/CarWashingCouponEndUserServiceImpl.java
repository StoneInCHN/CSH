package com.csh.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.beans.Setting;
import com.csh.dao.CarServiceDao;
import com.csh.dao.CarWashingCouponEndUserDao;
import com.csh.entity.CarService;
import com.csh.entity.CarWashingCouponEndUser;
import com.csh.entity.EndUser;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.CarWashingCouponEndUserService;
import com.csh.utils.SettingUtils;

@Service("carWashingCouponEndUserServiceImpl")
public class CarWashingCouponEndUserServiceImpl extends
    BaseServiceImpl<CarWashingCouponEndUser, Long> implements CarWashingCouponEndUserService {

  @Resource(name = "carWashingCouponEndUserDaoImpl")
  private CarWashingCouponEndUserDao carWashingCouponEndUserDao;

  @Resource(name = "carServiceDaoImpl")
  private CarServiceDao carServiceDao;

  @Resource(name = "carWashingCouponEndUserDaoImpl")
  public void setBaseDao(CarWashingCouponEndUserDao carWashingCouponEndUserDao) {
    super.setBaseDao(carWashingCouponEndUserDao);
  }

  @Override
  public List<CarWashingCouponEndUser> userGetWashingCoupon(EndUser endUser) {
    List<Filter> filters = new ArrayList<Filter>();
    Filter userFilter = new Filter("endUser", Operator.eq, endUser);
    Filter isUsedFilter = new Filter("remainNum", Operator.gt, 0);
    filters.add(userFilter);
    filters.add(isUsedFilter);

    List<CarWashingCouponEndUser> list =
        carWashingCouponEndUserDao.findList(null, null, filters, null);
    return list;
  }

  @Override
  public CarWashingCouponEndUser getWashingCouponPay(EndUser endUser, Long serviceId) {
    Setting setting = SettingUtils.get();
    CarService carService = carServiceDao.find(serviceId);
    if (carService.getServiceCategory().getId().equals(setting.getServiceCateWash())) {
      Long tenantId = carService.getTenantInfo().getId();
      CarWashingCouponEndUser washingCoupon =
          carWashingCouponEndUserDao.userGetWashingCouponByTenant(tenantId, endUser);
      return washingCoupon;
    }
    return null;
  }
}
