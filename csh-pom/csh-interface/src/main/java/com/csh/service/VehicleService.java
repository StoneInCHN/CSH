package com.csh.service;

import com.csh.entity.DeviceInfo;
import com.csh.entity.EndUser;
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
  Vehicle bindDevice(Vehicle vehicle, DeviceInfo deviceInfo);

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

  /**
   * 添加车辆信息
   * 
   * @param vehicle
   * @param endUser
   * @param isDefault
   * @return
   */
  Vehicle addVehicle(Vehicle vehicle, EndUser endUser, Boolean isDefault);


  /**
   * 修改车辆信息
   * 
   * @param vehicle
   * @param endUser
   * @param isDefault
   * @return
   */
  Vehicle updateVehicle(Vehicle vehicle, EndUser endUser, Boolean isDefault);

}
