package com.csh.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.csh.dao.CarServiceDao;
import com.csh.entity.CarService;
import com.csh.entity.TenantInfo;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("carServiceDaoImpl")
public class CarServiceDaoImpl extends BaseDaoImpl<CarService, Long> implements CarServiceDao {

  @Override
  public List<CarService> getServicesByTenantAndCategory(TenantInfo tenantInfo, Long categoryId) {
    if (tenantInfo == null || categoryId == null) {
      return null;
    }
    try {
      String jpql =
          "select carService from CarService carService where carService.tenantInfo = :tenantInfo and carService.serviceCategory.id = :categoryId";
      return entityManager.createQuery(jpql, CarService.class).setFlushMode(FlushModeType.COMMIT)
          .setParameter("tenantInfo", tenantInfo).setParameter("categoryId", categoryId)
          .getResultList();
    } catch (NoResultException e) {
      return null;
    }
  }

}
