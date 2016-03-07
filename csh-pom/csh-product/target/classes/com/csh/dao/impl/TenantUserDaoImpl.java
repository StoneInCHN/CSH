package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.TenantUserDao;
import com.csh.entity.TenantUser;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("tenantUserDaoImpl")
public class TenantUserDaoImpl extends BaseDaoImpl<TenantUser, Long> implements TenantUserDao {


}
