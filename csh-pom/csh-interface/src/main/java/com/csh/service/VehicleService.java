package com.csh.service;

import java.math.BigDecimal;

import com.csh.entity.DeviceInfo;
import com.csh.entity.Vehicle;
import com.csh.framework.service.BaseService;

public interface VehicleService extends BaseService<Vehicle, Long> {


  /**
   * 车辆绑定租户
   * 
   * @param vehicle
   * @param deviceInfo
   * @return
   */
  Vehicle bindTenant(Vehicle vehicle);

  /**
   * 车辆绑定设备
   * 
   * @param vehicle
   * @param deviceInfo
   * @return
   */
  Vehicle bindDevice(Vehicle vehicle, DeviceInfo deviceInfo, BigDecimal bindPrice);

  /**
   * 根据车牌号查询车辆
   * 
   * @param plate
   * @return
   */
  Vehicle getVehicleByPlate(String plate);

  /**
   * 根据车牌架查询车辆
   * 
   * @param vehicleNo
   * @return
   */
  Vehicle getVehicleByVehicleNo(String vehicleNo);
}
