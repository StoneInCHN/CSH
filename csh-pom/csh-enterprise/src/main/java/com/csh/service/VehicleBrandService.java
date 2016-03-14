package com.csh.service; 

import java.util.List;
import java.util.Map;

import com.csh.entity.VehicleBrand;
import com.csh.framework.service.BaseService;

public interface VehicleBrandService extends BaseService<VehicleBrand,Long>{
  public List<Map<String, Object>> findAllVehicleBrand();
}