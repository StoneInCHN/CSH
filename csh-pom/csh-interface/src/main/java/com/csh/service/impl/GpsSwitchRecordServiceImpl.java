package com.csh.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.DeviceInfoDao;
import com.csh.dao.GpsSwitchRecordDao;
import com.csh.entity.DeviceInfo;
import com.csh.entity.GpsSwitchRecord;
import com.csh.entity.commonenum.CommonEnum.GpsSwitch;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.GpsSwitchRecordService;

@Service("gpsSwitchRecordServiceImpl")
public class GpsSwitchRecordServiceImpl extends BaseServiceImpl<GpsSwitchRecord, Long> implements
    GpsSwitchRecordService {

  @Resource(name = "gpsSwitchRecordDaoImpl")
  private GpsSwitchRecordDao gpsSwitchRecordDao;

  @Resource(name = "deviceInfoDaoImpl")
  private DeviceInfoDao deviceInfoDao;

  @Resource(name = "gpsSwitchRecordDaoImpl")
  public void setBaseDao(GpsSwitchRecordDao gpsSwitchRecordDao) {
    super.setBaseDao(gpsSwitchRecordDao);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void createGpsSwitchRecord(GpsSwitch switchOpr, DeviceInfo deviceInfo, Long userId) {
    GpsSwitchRecord gpsSwitchRecord = new GpsSwitchRecord();
    gpsSwitchRecord.setDeviceNo(deviceInfo.getDeviceNo());
    gpsSwitchRecord.setUserId(userId);
    gpsSwitchRecord.setSwitchOpr(switchOpr);
    gpsSwitchRecord.setOprTime(new Date());
    if (switchOpr.equals(GpsSwitch.TURNON)) {
      deviceInfo.setIsGpsEnable(true);
    } else if (switchOpr.equals(GpsSwitch.TURNOFF)) {
      deviceInfo.setIsGpsEnable(false);
    }

    gpsSwitchRecordDao.persist(gpsSwitchRecord);
    deviceInfoDao.merge(deviceInfo);
  }
}
