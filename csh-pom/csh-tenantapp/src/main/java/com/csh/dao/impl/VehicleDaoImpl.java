package com.csh.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.csh.entity.Vehicle;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.dao.VehicleDao;

@Repository("vehicleDaoImpl")
public class VehicleDaoImpl extends BaseDaoImpl<Vehicle, Long> implements VehicleDao {

  @Override
  public Page<Vehicle> listUnbindVehicle(Long tenantId, Pageable pageable) {

    Map<String, Object> paramMap = new HashMap<String, Object>();

    String jpql =
        "select vehicle from DeviceInfo deviceInfo right join  deviceInfo.vehicle vehicle"
            + " where vehicle.plate != '0000000' and vehicle.tenantID=:tenantID and deviceInfo.id is null";
    paramMap.put("tenantID", tenantId);
    return findPageCustomized(pageable, jpql, paramMap);

  }


}
