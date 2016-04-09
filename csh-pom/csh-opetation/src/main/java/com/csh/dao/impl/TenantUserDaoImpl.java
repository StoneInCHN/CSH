package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.TenantUser;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.TenantUserDao;
@Repository("tenantUserDaoImpl")
public class TenantUserDaoImpl extends  BaseDaoImpl<TenantUser,Long> implements TenantUserDao {

}