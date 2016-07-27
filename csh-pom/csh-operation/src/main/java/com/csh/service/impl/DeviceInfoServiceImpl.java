package com.csh.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.DeviceInfoDao;
import com.csh.entity.DeviceInfo;
import com.csh.entity.Vehicle;
import com.csh.entity.commonenum.CommonEnum.BindStatus;
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

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public DeviceInfo unBind(DeviceInfo deviceInfo)
  {
    Vehicle vehicle = deviceInfo.getVehicle ();
    deviceInfo.setBindStatus (BindStatus.UNBINDED);
//    vehicle.setTenantID (null);
    deviceInfo.setVehicle (null);
    deviceInfo.setBindTime (null);
    deviceInfo.setUnBindTime (new Date ());
//    vehicleDao.merge (vehicle);
    deviceInfoDao.merge (deviceInfo);
    return deviceInfo;
  }
}
