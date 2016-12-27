package com.csh.service; 

import com.csh.entity.Vehicle;
import com.csh.framework.paging.Page;
import com.csh.framework.service.BaseService;
import com.csh.json.request.VehicleRequest;

public interface VehicleService extends BaseService<Vehicle,Long>{

    /**
     * 根据条件进行车辆查询
     * @param request
     * @return
     */
    Page<Vehicle> findPageForList(VehicleRequest request);
    
}