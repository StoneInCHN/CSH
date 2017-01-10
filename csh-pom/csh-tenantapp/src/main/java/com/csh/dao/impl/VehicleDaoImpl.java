package com.csh.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.csh.dao.VehicleDao;
import com.csh.entity.EndUser;
import com.csh.entity.Vehicle;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;

@Repository("vehicleDaoImpl")
public class VehicleDaoImpl extends BaseDaoImpl<Vehicle, Long> implements VehicleDao {

  @Override
  public Page<Vehicle> listUnbindVehicle(Long tenantId, String plate, EndUser endUser,
      Date startTime, Date endTime, Pageable pageable) {

    Map<String, Object> paramMap = new HashMap<String, Object>();

    String jpql =
        "select vehicle from DeviceInfo deviceInfo right join deviceInfo.vehicle vehicle"
            + " where vehicle.plate != '0000000' and vehicle.tenantID=:tenantID and deviceInfo.id is null";
    paramMap.put("tenantID", tenantId);
    if (plate != null) {
      jpql = jpql + " and vehicle.plate like :plate";
      paramMap.put("plate", "%" + plate + "%");
    }
    if (endUser != null) {
      jpql = jpql + " and vehicle.endUser = :endUser";
      paramMap.put("endUser", endUser);
    }
    if (startTime != null) {
      jpql = jpql + " and vehicle.createDate >= :startTime";
      paramMap.put("startTime", startTime);
    }
    if (endTime != null) {
      jpql = jpql + " and vehicle.createDate <= :endTime";
      paramMap.put("endTime", endTime);
    }
    return findPageCustomized(pageable, jpql, paramMap);

  }


}
