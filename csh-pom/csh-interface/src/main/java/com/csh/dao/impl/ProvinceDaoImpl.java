package com.csh.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.csh.dao.ProvinceDao;
import com.csh.entity.Province;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("provinceDaoImpl")
public class ProvinceDaoImpl extends BaseDaoImpl<Province, Long> implements ProvinceDao {

  @Override
  public Province getProvinceByShortName(String shortName) {
    if (shortName == null) {
      return null;
    }
    try {
      String jpql = "select province from Province province where province.shortName = :shortName";
      return entityManager.createQuery(jpql, Province.class).setFlushMode(FlushModeType.COMMIT)
          .setParameter("shortName", shortName).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

}
