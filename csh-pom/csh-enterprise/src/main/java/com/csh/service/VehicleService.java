package com.csh.service; 

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.csh.entity.Vehicle;
import com.csh.framework.service.BaseService;
import com.csh.json.response.VehicleDailyReport;

public interface VehicleService extends BaseService<Vehicle,Long>{

  List<Map<String, Object>> findVehicleUnderUser (Long userId);

  List<Map<String, Object>> findVehicleUserInfoUnderTenant (String endUserFilter);

  VehicleDailyReport callVehicleDailyData (Date date, Long vehicleId);

  Vehicle findVehicleByDeviceId(Long deviceId);
//  VehicleDailyReport callVehicleStatus (Long[] ids);

}