package com.csh.service; 

import com.csh.entity.TenantDeductClearingRecord;
import com.csh.framework.service.BaseService;

public interface TenantDeductClearingRecordService extends BaseService<TenantDeductClearingRecord,Long>{
  void saveTenantDeductClearingRecord (TenantDeductClearingRecord tenantDeductClearingRecord,
      Long[] ids);
}