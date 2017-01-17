package com.csh.service;

import com.csh.entity.DeviceInfo;
import com.csh.entity.commonenum.CommonEnum;
import com.csh.framework.paging.Page;
import com.csh.framework.service.BaseService;
import com.csh.json.request.DeviceInfoRequest;

public interface DeviceInfoService extends BaseService<DeviceInfo, Long> {

  /**
   * 请求分页
   * 
   * @param request
   * @return
   */
  Page<DeviceInfo> findPageByRequest(DeviceInfoRequest request);

  /**
   * 根据绑定状态统计数量
   * 
   * @param tenantId
   * @param bindStatus
   * @return
   */
  Integer countByBindStatus(long tenantId, CommonEnum.BindStatus bindStatus);

  /**
   * 解绑设备
   * 
   * @param deviceInfo
   */
  void unBind(DeviceInfo deviceInfo);

  DeviceInfo getDeviceByDeviceNo(String deviceNo);

}
