package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.TenantInfoDao;
import com.csh.entity.TenantInfo;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("tenantInfoDaoImpl")
public class TenantInfoDaoImpl extends BaseDaoImpl<TenantInfo, Long> implements TenantInfoDao {



}
