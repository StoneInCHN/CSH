package com.csh.dao;

import com.csh.entity.CarWashingCouponEndUser;
import com.csh.entity.EndUser;
import com.csh.framework.dao.BaseDao;

public interface CarWashingCouponEndUserDao extends BaseDao<CarWashingCouponEndUser, Long> {
  /**
   * 手机用户获取服务所属租户可用洗车劵
   * 
   * @param tenantId
   * @param endUser
   * @return
   */
  CarWashingCouponEndUser userGetWashingCouponByTenant(Long tenantId, EndUser endUser);
}
