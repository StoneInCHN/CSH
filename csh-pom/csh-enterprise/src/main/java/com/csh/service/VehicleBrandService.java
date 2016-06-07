package com.csh.service; 

import java.util.List;
import java.util.Map;

import com.csh.entity.VehicleBrand;
import com.csh.framework.service.BaseService;
import com.csh.json.response.TreeNodeResponse;

public interface VehicleBrandService extends BaseService<VehicleBrand,Long>{
  public List<Map<String, Object>> findAllVehicleBrand();

  public List<TreeNodeResponse> listVehicleBrandWithDetail (Long vehicleLineId,
      Long vehicleLineParentId, Long vehicleBrandId, Integer size,
      Integer selectCount, Long itemPartId);
}