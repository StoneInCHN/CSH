package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.TenantEvaluate;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.TenantEvaluateDao;
@Repository("tenantEvaluateDaoImpl")
public class TenantEvaluateDaoImpl extends  BaseDaoImpl<TenantEvaluate,Long> implements TenantEvaluateDao {

}