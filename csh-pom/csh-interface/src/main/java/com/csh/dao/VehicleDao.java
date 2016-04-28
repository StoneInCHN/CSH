package com.csh.dao;

import com.csh.entity.Vehicle;
import com.csh.framework.dao.BaseDao;

public interface VehicleDao extends BaseDao<Vehicle, Long> {


  /**
   * 根据车票号查询车辆
   * 
   * @param plate
   * @return
   */
  Vehicle getVehicleByPlate(String plate);

  /**
   * 根据车牌架查询车辆
   * 
   * @param plate
   * @return
   */
  Vehicle getVehicleByVehicleNo(String vehicleNo);
}
