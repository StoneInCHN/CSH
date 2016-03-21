package com.csh.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.csh.dao.AdvertisementDao;
import com.csh.entity.Advertisement;
import com.csh.entity.commonenum.CommonEnum.Status;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("advertisementDaoImpl")
public class AdvertisementDaoImpl extends BaseDaoImpl<Advertisement, Long> implements
    AdvertisementDao {

  @Override
  public List<Advertisement> getAdvBanner(Long tenantId) {
    try {
      String jpql =
          "select adv from Advertisement adv where adv.status = :status and (adv.tenantId = :tenantId or adv.tenantId is null)";
      return entityManager.createQuery(jpql, Advertisement.class)
          .setFlushMode(FlushModeType.COMMIT).setParameter("status", Status.ENABLE)
          .setParameter("tenantId", tenantId).getResultList();
    } catch (NoResultException e) {
      return null;
    }
  }

}
