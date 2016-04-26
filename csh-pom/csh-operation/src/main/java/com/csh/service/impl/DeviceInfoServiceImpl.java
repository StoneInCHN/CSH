package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.DeviceInfoDao;
import com.csh.entity.DeviceInfo;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.DeviceInfoService;

@Service("deviceInfoServiceImpl")
public class DeviceInfoServiceImpl extends BaseServiceImpl<DeviceInfo, Long> implements
    DeviceInfoService {

  @Resource(name = "deviceInfoDaoImpl")
  private DeviceInfoDao deviceInfoDao;

  @Resource(name = "deviceInfoDaoImpl")
  public void setBaseDao(DeviceInfoDao deviceInfoDao) {
    super.setBaseDao(deviceInfoDao);
  }

  @Override
  public DeviceInfo findByDeviceNo(String deviceNo) {
    return deviceInfoDao.findByDeviceNo(deviceNo);
  }
}
