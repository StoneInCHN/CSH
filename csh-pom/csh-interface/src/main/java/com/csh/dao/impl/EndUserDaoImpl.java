package com.csh.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.EndUserDao;
import com.csh.entity.EndUser;
import com.csh.entity.commonenum.CommonEnum.AppPlatform;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("endUserDaoImpl")
public class EndUserDaoImpl extends BaseDaoImpl<EndUser, Long> implements EndUserDao {

  @Transactional
  // @Cacheable(value = "endUser",key="'endUser.id='+#userName")
  public EndUser findByUserName(String username) {
    if (username == null) {
      return null;
    }
    try {
      String jpql = "select user from EndUser user where user.userName = :username";
      return entityManager.createQuery(jpql, EndUser.class).setFlushMode(FlushModeType.COMMIT)
          .setParameter("username", username).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  @Transactional
  // @Cacheable(value = "endUser",key="'endUser.id='+#mobileNo")
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

  @Override
  @Cacheable(value = "endUser", key = "'endUser.token='+#id")
  public String getEndUserToken(Long id) {
    return null;
  }

  @Override
  @CachePut(value = "endUser", key = "'endUser.token='+#id")
  public String createEndUserToken(String token, Long id) {
    return token;
  }

  @Override
  @CacheEvict(value = "endUser", key = "'endUser.token='+#id")
  public void deleteEndUserToken(Long id) {

  }

  @Override
  @Cacheable(value = "endUser", key = "'endUser.appPlatform='+#id")
  public AppPlatform getEndUserAppPlatform(Long id) {
    return null;
  }

  @Override
  @CachePut(value = "endUser", key = "'endUser.appPlatform='+#id")
  public AppPlatform createEndUserAppPlatform(AppPlatform appPlatform, Long id) {
    return appPlatform;
  }

}
