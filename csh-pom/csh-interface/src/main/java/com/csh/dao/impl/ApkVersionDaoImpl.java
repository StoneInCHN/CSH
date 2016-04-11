package com.csh.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.csh.dao.ApkVersionDao;
import com.csh.entity.ApkVersion;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("apkVersionDaoImpl")
public class ApkVersionDaoImpl extends BaseDaoImpl<ApkVersion, Long> implements ApkVersionDao {

  @Override
  public ApkVersion getNewVersion(Integer versionCode) {
    if (versionCode == null) {
      return null;
    }
    try {
      String jpql =
          "select version from ApkVersion version where version.versionCode > :versionCode";
      return entityManager.createQuery(jpql, ApkVersion.class).setFlushMode(FlushModeType.COMMIT)
          .setParameter("versionCode", versionCode).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

}
