package com.csh.service;

import com.csh.entity.DeviceInfo;
import com.csh.entity.GpsSwitchRecord;
import com.csh.entity.commonenum.CommonEnum.GpsSwitch;
import com.csh.framework.service.BaseService;

public interface GpsSwitchRecordService extends BaseService<GpsSwitchRecord, Long> {

  void createGpsSwitchRecord(GpsSwitch switchOpr, DeviceInfo deviceInfo, Long userId);
}
