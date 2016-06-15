package com.csh.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.csh.dao.CarWashingCouponEndUserDao;
import com.csh.entity.CarWashingCouponEndUser;
import com.csh.entity.EndUser;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("carWashingCouponEndUserDaoImpl")
public class CarWashingCouponEndUserDaoImpl extends BaseDaoImpl<CarWashingCouponEndUser, Long>
    implements CarWashingCouponEndUserDao {

  @Override
  public CarWashingCouponEndUser userGetWashingCouponByTenant(Long tenantId, EndUser endUser) {
    if (tenantId == null || endUser == null) {
      return null;
    }
    try {
      String jpql =
          "select washing from CarWashingCouponEndUser washing where washing.carWashingCoupon.tenantID = :tenantId and washing.endUser = :endUser and washing.remainNum>0";
      return entityManager.createQuery(jpql, CarWashingCouponEndUser.class)
          .setFlushMode(FlushModeType.COMMIT).setParameter("endUser", endUser)
          .setParameter("tenantId", tenantId).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

}
