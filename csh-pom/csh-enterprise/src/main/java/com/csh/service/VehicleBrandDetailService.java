package com.csh.service; 

import java.util.List;
import java.util.Map;

import com.csh.entity.VehicleBrandDetail;
import com.csh.entity.VehicleLine;
import com.csh.framework.service.BaseService;

public interface VehicleBrandDetailService extends BaseService<VehicleBrandDetail,Long>{
  public List<Map<String, Object>> findVehicleBrandDetailByLine (VehicleLine vehicleLine);
}