package com.csh.service;

import com.csh.entity.DeviceInfo;
import com.csh.entity.Vehicle;
import com.csh.framework.service.BaseService;

public interface VehicleService extends BaseService<Vehicle, Long> {

  /**
   * 绑定设备
   * 
   * @param vehicle
   * @param deviceInfo
   * @return
   */
  Vehicle bindDevice(Vehicle vehicle, DeviceInfo deviceInfo);
}
