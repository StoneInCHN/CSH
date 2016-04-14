package com.csh.dao.impl; 

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

import com.csh.dao.EndUserDao;
import com.csh.entity.EndUser;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
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

  @Override
  public Page<EndUser> findEndUser (Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String userNameSearch,
      AccountStatus accountStatusSearch,Long tenantID)
  { Map<String, Object> paramMap  = new HashMap<String, Object> ();
  
  String jpql ="select u from EndUser u, Vehicle v "+
      "where u.id = v.endUser and v.tenantID = :tenantID";
  paramMap.put ("tenantID", tenantID);
  if (userNameSearch != null)
  {
    jpql= jpql+ " and u.userName like :userName";
    paramMap.put ("userName", "%"+userNameSearch+"%");
  }
  if (accountStatusSearch != null)
  {
    jpql= jpql+ " and u.accountStatus = :accountStatus";
    paramMap.put ("accountStatus", accountStatusSearch);
  }

  return findPageCustomized (pageable, jpql, paramMap);}

}