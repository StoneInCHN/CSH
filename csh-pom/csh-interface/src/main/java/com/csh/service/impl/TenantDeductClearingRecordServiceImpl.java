package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.TenantDeductClearingRecord;
import com.csh.dao.TenantDeductClearingRecordDao;
import com.csh.service.TenantDeductClearingRecordService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("tenantDeductClearingRecordServiceImpl")
public class TenantDeductClearingRecordServiceImpl extends BaseServiceImpl<TenantDeductClearingRecord,Long> implements TenantDeductClearingRecordService {

      @Resource(name="tenantDeductClearingRecordDaoImpl")
      public void setBaseDao(TenantDeductClearingRecordDao tenantDeductClearingRecordDao) {
         super.setBaseDao(tenantDeductClearingRecordDao);
  }
}