package com.csh.service; 

import java.util.List;

import com.csh.entity.CarServiceRecord;
import com.csh.entity.commonenum.CommonEnum.ChargeStatus;
import com.csh.framework.service.BaseService;

public interface CarServiceRecordService extends BaseService<CarServiceRecord,Long>{
  List<CarServiceRecord> findCurrentClearingRecords();

  void updateCarServiceRecord (CarServiceRecord oldCarServiceRecord, CarServiceRecord newCarServiceRecord2);

  void sendRecordStatusUpdateMessag (CarServiceRecord record,
      ChargeStatus newChargeStatus);
}