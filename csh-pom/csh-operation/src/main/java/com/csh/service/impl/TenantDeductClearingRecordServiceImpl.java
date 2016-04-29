package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.TenantDeductClearingRecordDao;
import com.csh.entity.TenantDeductClearingRecord;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.TenantDeductClearingRecordService;

@Service("tenantDeductClearingRecordServiceImpl")
public class TenantDeductClearingRecordServiceImpl extends
    BaseServiceImpl<TenantDeductClearingRecord, Long> implements TenantDeductClearingRecordService {

  @Resource
  public void setBaseDao(TenantDeductClearingRecordDao tenantDeductClearingRecordDao) {
    super.setBaseDao(tenantDeductClearingRecordDao);
  }


}
