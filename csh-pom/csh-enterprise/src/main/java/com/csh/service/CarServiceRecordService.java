package com.csh.service; 

import java.util.List;

import com.csh.entity.CarServiceRecord;
import com.csh.framework.service.BaseService;

public interface CarServiceRecordService extends BaseService<CarServiceRecord,Long>{
  List<CarServiceRecord> findCurrentClearingRecords();
}