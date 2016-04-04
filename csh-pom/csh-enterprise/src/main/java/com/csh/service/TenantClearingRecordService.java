package com.csh.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.csh.entity.CarServiceRecord;
import com.csh.entity.TenantClearingRecord;
import com.csh.entity.commonenum.CommonEnum.ClearingStatus;
import com.csh.framework.service.BaseService;

/**
 * 结算记录
 * 
 * @author shijun
 *
 */
public interface TenantClearingRecordService extends BaseService<TenantClearingRecord, Long> {

  Map<String, Date> findPeriodBeginEndDate ();

  void saveTenantClearingRecord (TenantClearingRecord tenantClearingRecord,
      Long[] ids);
  
}
