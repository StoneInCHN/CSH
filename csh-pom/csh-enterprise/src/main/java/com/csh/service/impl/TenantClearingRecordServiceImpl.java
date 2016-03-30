package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.TenantClearingRecordDao;
import com.csh.entity.TenantClearingRecord;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.TenantClearingRecordService;

/**
 * 结算记录
 * 
 * @author shijun
 *
 */
@Service("tenantClearingRecordServiceImpl")
public class TenantClearingRecordServiceImpl extends BaseServiceImpl<TenantClearingRecord, Long> implements
TenantClearingRecordService {


  @Resource(name = "tenantClearingRecordDaoImpl")
  private TenantClearingRecordDao tenantClearingRecordDao;

  @Resource()
  public void setBaseDao(TenantClearingRecordDao tenantClearingRecordDao) {
    super.setBaseDao(tenantClearingRecordDao);
  }

}
