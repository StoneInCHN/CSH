package com.csh.dao.impl; 

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository; 

import com.csh.entity.TenantInfo;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.TenantInfoDao;
@Repository("tenantInfoDaoImpl")
public class TenantInfoDaoImpl extends  BaseDaoImpl<TenantInfo,Long> implements TenantInfoDao {

	@Override
	public TenantInfo findTenantWithOrgCode(String orgCode) {
	    try {
	      String jpql =
	          "select tenantInfo from TenantInfo tenantInfo where tenantInfo.orgCode = :orgCode";
	      return entityManager.createQuery(jpql, TenantInfo.class).setFlushMode(FlushModeType.COMMIT)
	          .setParameter("orgCode", orgCode).getSingleResult();
	    } catch (NoResultException e) {
	      return null;
	    }
	  
	}

}