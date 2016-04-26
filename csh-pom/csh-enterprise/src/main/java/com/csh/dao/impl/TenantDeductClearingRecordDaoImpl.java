package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.TenantDeductClearingRecord;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.TenantDeductClearingRecordDao;
@Repository("tenantDeductClearingRecordDaoImpl")
public class TenantDeductClearingRecordDaoImpl extends  BaseDaoImpl<TenantDeductClearingRecord,Long> implements TenantDeductClearingRecordDao {

}