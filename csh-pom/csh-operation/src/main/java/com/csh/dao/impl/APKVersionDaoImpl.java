package com.csh.dao.impl;

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository;

import com.csh.dao.APKVersionDao;
import com.csh.entity.ApkVersion;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("apkVersionDaoImpl")
public class APKVersionDaoImpl extends BaseDaoImpl<ApkVersion, Long> implements APKVersionDao {
  @Override
  public boolean versionExists(String versionName, Long id) {
    if (versionName == null) {
      return false;
    }
    if (id == null) {
      String jpql =
          "select count(*) from ApkVersion apkVersion where lower(apkVersion.versionName) = lower(:versionName) ";
      Long count =
          entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT)
              .setParameter("versionName", versionName).getSingleResult();
      return count > 0;
    } else {
      String jpql =
          "select count(*) from ApkVersion apkVersion where lower(apkVersion.versionName) = lower(:versionName) and apkVersion.id !=:id";
      Long count =
          entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT)
              .setParameter("versionName", versionName).setParameter("id", id).getSingleResult();
      return count > 0;
    }
  }
}
