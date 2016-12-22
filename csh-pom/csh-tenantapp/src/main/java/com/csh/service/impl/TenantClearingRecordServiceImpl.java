package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.TenantClearingRecord;
import com.csh.dao.TenantClearingRecordDao;
import com.csh.service.TenantClearingRecordService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("tenantClearingRecordServiceImpl")
public class TenantClearingRecordServiceImpl extends BaseServiceImpl<TenantClearingRecord,Long> implements TenantClearingRecordService {

      @Resource(name="tenantClearingRecordDaoImpl")
      public void setBaseDao(TenantClearingRecordDao tenantClearingRecordDao) {
         super.setBaseDao(tenantClearingRecordDao);
  }
}