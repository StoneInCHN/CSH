package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.TenantAccount;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.TenantAccountDao;
@Repository("tenantAccountDaoImpl")
public class TenantAccountDaoImpl extends  BaseDaoImpl<TenantAccount,Long> implements TenantAccountDao {

}