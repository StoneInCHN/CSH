package com.csh.service; 

import java.util.List;
import java.util.Map;

import com.csh.entity.Vehicle;
import com.csh.framework.service.BaseService;

public interface VehicleService extends BaseService<Vehicle,Long>{

  List<Map<String, Object>> findVehicleUnderUser (Long userId);

}