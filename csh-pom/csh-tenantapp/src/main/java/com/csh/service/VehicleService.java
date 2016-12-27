package com.csh.service; 

import com.csh.entity.Vehicle;
import com.csh.entity.commonenum.CommonEnum.OnlineStatus;
import com.csh.framework.paging.Page;
import com.csh.framework.service.BaseService;
import com.csh.json.request.VehicleRequest;

public interface VehicleService extends BaseService<Vehicle,Long>{

    Page<Vehicle> findPageByRequest(VehicleRequest request);

    /**
     * 根据条件进行车辆查询
     * @param request
     * @return
     */
    Page<Vehicle> findPageForList(VehicleRequest request);
    
    /**
     * 根据onlineStatus获取记录数
     * @param request
     * @param onlineStatus
     * @return
     */
    Long getCountByOnlineStatus(VehicleRequest request ,OnlineStatus onlineStatus);
   
}