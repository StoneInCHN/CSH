package com.csh.dao;

import java.util.Date;

import com.csh.entity.EndUser;
import com.csh.entity.Vehicle;
import com.csh.framework.dao.BaseDao;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;

public interface VehicleDao extends BaseDao<Vehicle, Long> {

  /**
   * 查询未绑定车辆
   * 
   * @param tenantId
   * @param plate
   * @param endUser
   * @param startTime
   * @param endTime
   * @param pageable
   * @return
   */
  public Page<Vehicle> listUnbindVehicle(Long tenantId, String plate, EndUser endUser,
      Date startTime, Date endTime, Pageable pageable);

}
