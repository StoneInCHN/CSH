package com.csh.dao.impl;


import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.csh.dao.EndUserDao;
import com.csh.entity.EndUser;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("endUserDaoImpl")
public class EndUserDaoImpl extends BaseDaoImpl<EndUser, Long> implements EndUserDao {

  public EndUser findByUserMobile(String mobileNo) {
    if (mobileNo == null) {
      return null;
    }
    try {
      String jpql = "select user from EndUser user where user.mobileNum = :mobileNum";
      return entityManager.createQuery(jpql, EndUser.class).setFlushMode(FlushModeType.COMMIT)
          .setParameter("mobileNum", mobileNo).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }
}
