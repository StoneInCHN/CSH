package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.TenantApplyDao;
import com.csh.entity.TenantApply;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("tenantApplyDaoImpl")
public class TenantApplyDaoImpl extends BaseDaoImpl<TenantApply, Long> implements TenantApplyDao {

}
