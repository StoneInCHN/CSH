package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.TenantInfo;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.TenantInfoDao;
@Repository("tenantInfoDaoImpl")
public class TenantInfoDaoImpl extends  BaseDaoImpl<TenantInfo,Long> implements TenantInfoDao {

}