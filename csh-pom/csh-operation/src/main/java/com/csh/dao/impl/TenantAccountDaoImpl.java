package com.csh.dao.impl; 

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository; 

import com.csh.entity.TenantAccount;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.TenantAccountDao;
@Repository("tenantAccountDaoImpl")
public class TenantAccountDaoImpl extends  BaseDaoImpl<TenantAccount,Long> implements TenantAccountDao {
  public boolean usernameExists(String userName) {
    if (userName == null) {
      return false;
    }
    String jpql = "select count(*) from TenantAccount tenantAccount where lower(tenantAccount.userName) = lower(:userName)";
    Long count =
        entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT)
            .setParameter("userName", userName).getSingleResult();
    return count > 0;
  }

}