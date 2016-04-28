package com.csh.dao; 
import com.csh.entity.Vehicle;
import com.csh.framework.dao.BaseDao;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;

public interface VehicleDao extends  BaseDao<Vehicle,Long>{

  Vehicle findVehicleByDeviceId (Long deviceId);
  Page<Vehicle> listUnBuindVehicle (Long tenantId ,String vehiclePlateSearch,
      String motorcadeSearch, String vehicleFullBrandSearch, Pageable pageable);
}