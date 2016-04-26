package com.csh.service; 

import com.csh.entity.DeviceInfo;
import com.csh.framework.service.BaseService;

public interface DeviceInfoService extends BaseService<DeviceInfo,Long>{

  /**
   * 根据设备号查找
   * @param deviceNo
   * @return
   */
  public DeviceInfo findByDeviceNo(String deviceNo);
  
}