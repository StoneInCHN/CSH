package com.csh.service;

import java.util.List;

import com.csh.entity.CarWashingCouponEndUser;
import com.csh.entity.EndUser;
import com.csh.framework.service.BaseService;

public interface CarWashingCouponEndUserService extends BaseService<CarWashingCouponEndUser, Long> {

  /**
   * 手机用户获取租户洗车劵
   * 
   * @param endUser
   * @return
   */
  List<CarWashingCouponEndUser> userGetWashingCoupon(EndUser endUser);

  /**
   * 是否可以用洗车券支付
   * 
   * @param endUser
   * @param serviceId
   * @return
   */
  CarWashingCouponEndUser getWashingCouponPay(EndUser endUser, Long serviceId);
}
