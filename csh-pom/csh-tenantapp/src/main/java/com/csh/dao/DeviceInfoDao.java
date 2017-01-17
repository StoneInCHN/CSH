package com.csh.dao;

import com.csh.entity.DeviceInfo;
import com.csh.framework.dao.BaseDao;

public interface DeviceInfoDao extends BaseDao<DeviceInfo, Long> {

  DeviceInfo getDeviceByDeviceNo(String deviceNo);

}
