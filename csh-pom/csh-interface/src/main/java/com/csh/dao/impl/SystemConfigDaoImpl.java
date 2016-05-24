package com.csh.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.csh.dao.SystemConfigDao;
import com.csh.entity.SystemConfig;
import com.csh.entity.commonenum.CommonEnum.SystemConfigKey;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("systemConfigDaoImpl")
public class SystemConfigDaoImpl extends BaseDaoImpl<SystemConfig, Long> implements SystemConfigDao {

  @Override
  public SystemConfig getConfigByKey(SystemConfigKey key, Long tenantId) {
    if (key == null) {
      return null;
    }
    try {
      if (tenantId != null) {
        String jpql =
            "select config from SystemConfig config where config.configKey = :configKey and config.isEnabled = :isEnabled and config.tenantID = :tenantId";
        return entityManager.createQuery(jpql, SystemConfig.class)
            .setFlushMode(FlushModeType.COMMIT).setParameter("configKey", key)
            .setParameter("tenantId", tenantId).setParameter("isEnabled", true).getSingleResult();
      } else {
        String jpql =
            "select config from SystemConfig config where config.configKey = :configKey and config.isEnabled = :isEnabled and config.tenantID is null";
        return entityManager.createQuery(jpql, SystemConfig.class)
            .setFlushMode(FlushModeType.COMMIT).setParameter("configKey", key)
            .setParameter("isEnabled", true).getSingleResult();
      }

    } catch (NoResultException e) {
      return null;
    }
  }

}
