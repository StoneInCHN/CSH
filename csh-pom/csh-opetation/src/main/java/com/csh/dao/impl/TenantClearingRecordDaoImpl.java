package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.TenantClearingRecord;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.TenantClearingRecordDao;
@Repository("tenantClearingRecordDaoImpl")
public class TenantClearingRecordDaoImpl extends  BaseDaoImpl<TenantClearingRecord,Long> implements TenantClearingRecordDao {

}