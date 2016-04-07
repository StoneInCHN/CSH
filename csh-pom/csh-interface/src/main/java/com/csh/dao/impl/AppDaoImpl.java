package com.csh.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.csh.dao.AppDao;
import com.csh.entity.App;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("appDaoImpl")
public class AppDaoImpl extends BaseDaoImpl<App, Long> implements AppDao {

  @Override
  public App getTenantAppById(Long tenantId) {
    if (tenantId == null) {
      return null;
    }
    try {
      String jpql = "select app from App app where app.tenantID = :tenantID";
      return entityManager.createQuery(jpql, App.class).setFlushMode(FlushModeType.COMMIT)
          .setParameter("tenantID", tenantId).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

}
