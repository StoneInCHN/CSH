package com.csh.service;

import java.util.List;

import com.csh.entity.CarService;
import com.csh.entity.TenantInfo;
import com.csh.framework.service.BaseService;

public interface CarServiceService extends BaseService<CarService, Long> {

  /**
   * 根据租户和服务类别查询该租户下某种服务类别下的所有服务
   * 
   * @param tenantInfo
   * @param categoryId
   * @return
   */
  List<CarService> getServicesByTenantAndCategory(TenantInfo tenantInfo, Long categoryId);
}
