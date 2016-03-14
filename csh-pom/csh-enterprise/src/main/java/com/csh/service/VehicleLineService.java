package com.csh.service; 

import java.util.List;
import java.util.Map;

import com.csh.entity.VehicleBrand;
import com.csh.entity.VehicleLine;
import com.csh.framework.service.BaseService;

public interface VehicleLineService extends BaseService<VehicleLine,Long>{

  public List<Map<String, Object>> findVehicleLineByBrand(VehicleBrand vehicleBrand);
}