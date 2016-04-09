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
}
