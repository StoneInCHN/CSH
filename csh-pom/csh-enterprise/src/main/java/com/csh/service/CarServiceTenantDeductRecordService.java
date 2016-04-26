package com.csh.service; 

import java.util.List;

import com.csh.entity.CarServiceTenantDeductRecord;
import com.csh.framework.service.BaseService;

public interface CarServiceTenantDeductRecordService extends BaseService<CarServiceTenantDeductRecord,Long>{

  List<CarServiceTenantDeductRecord> findCurrentClearingRecords ();

}