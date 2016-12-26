package com.csh.service; 

import com.csh.entity.Vehicle;
import com.csh.framework.paging.Page;
import com.csh.framework.service.BaseService;
import com.csh.json.request.VehicleRequest;

public interface VehicleService extends BaseService<Vehicle,Long>{

    Page<Vehicle> findPageByRequest(VehicleRequest request);

}