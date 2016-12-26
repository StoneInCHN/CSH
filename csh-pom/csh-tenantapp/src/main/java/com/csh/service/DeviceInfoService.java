package com.csh.service; 

import com.csh.entity.DeviceInfo;
import com.csh.entity.commonenum.CommonEnum;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.BaseService;
import com.csh.json.request.DeviceInfoRequest;

public interface DeviceInfoService extends BaseService<DeviceInfo,Long>{

    Page<DeviceInfo> findPageByRequest(DeviceInfoRequest request);

    Integer countByBindStatus(long tenantId, CommonEnum.BindStatus bindStatus);

}