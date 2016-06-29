package com.csh.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.csh.dao.WalletRecordDao;
import com.csh.entity.WalletRecord;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("walletRecordDaoImpl")
public class WalletRecordDaoImpl extends BaseDaoImpl<WalletRecord, Long> implements WalletRecordDao {

  @Override
  public WalletRecord getRecordByOrderNum(String orderNum) {
    if (orderNum == null) {
      return null;
    }
    try {
      String jpql = "select record from WalletRecord record where record.recordNo = :orderNum";
      return entityManager.createQuery(jpql, WalletRecord.class).setFlushMode(FlushModeType.COMMIT)
          .setParameter("orderNum", orderNum).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

}
