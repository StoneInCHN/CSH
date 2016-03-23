package com.csh.dao.impl; 

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.csh.dao.EndUserDao;
import com.csh.entity.EndUser;
import com.csh.framework.dao.impl.BaseDaoImpl;
@Repository("endUserDaoImpl")
public class EndUserDaoImpl extends  BaseDaoImpl<EndUser,Long> implements EndUserDao {

  @Override
  public Long countUserByTenantID (Long tenantID)
  {
    String jpql =
        "select DISTINCT endUser from Vehicle v where v.tenantID=:tenantID ";
    Query query = entityManager.createQuery(jpql);
        query.setParameter("tenantID",tenantID);
    List<EndUser> endUserList =  query.getResultList ();
    if (endUserList != null)
    {
      return (long) endUserList.size ();
    }else {
      return 0L;
    }
  }

}