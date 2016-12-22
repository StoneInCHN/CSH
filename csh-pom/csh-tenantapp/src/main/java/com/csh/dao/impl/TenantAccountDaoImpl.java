package com.csh.dao.impl; 

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository; 

import com.csh.entity.TenantAccount;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.TenantAccountDao;
@Repository("tenantAccountDaoImpl")
public class TenantAccountDaoImpl extends  BaseDaoImpl<TenantAccount,Long> implements TenantAccountDao {

	@Override
	@Cacheable(value = "tenantUser", key = "'tenantUser.token='+#id")
	public String getTenantUserToken(Long id) {
		return null;
	}

	@Override
	@CachePut(value = "tenantUser", key = "'tenantUser.token='+#id")
	public String createTenantUserToken(String token, Long id) {
		return token;
	}

	@Override
	@CacheEvict(value = "tenantUser", key = "'tenantUser.token='+#id")
	public void deleteTenantUserToken(Long id) {
		
	}

	@Override
	public TenantAccount findByNameAndOrgCode(String name, String orgCode) {
		try {
		      String jpql =
		          "select tenantAccount from TenantAccount tenantAccount,TenantInfo tenantInfo where tenantAccount.tenantID = tenantInfo.id and tenantInfo.orgCode = :orgCode and tenantAccount.userName = :username";
		      return entityManager.createQuery(jpql, TenantAccount.class)
		          .setFlushMode(FlushModeType.COMMIT).setParameter("username", name)
		          .setParameter("orgCode", orgCode).getSingleResult();
		    } catch (NoResultException e) {
		      return null;
		    }
	}

}