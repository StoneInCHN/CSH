package com.csh.dao;

import com.csh.entity.DeviceInfo;
import com.csh.framework.dao.BaseDao;

public interface DeviceInfoDao extends BaseDao<DeviceInfo, Long> {

  /**
   * 根据设备号查找
   * 
   * @param deviceNo
   * @return
   */
  public DeviceInfo findByDeviceNo(String deviceNo);

}
