package com.csh.service;

import java.util.Map;

import com.csh.entity.TenantInfo;
import com.csh.framework.service.BaseService;

public interface TenantInfoService extends BaseService<TenantInfo, Long> {

  /**
   * 根据用户和服务类别获取租户
   * 
   * @param userId
   * @param categoryId
   * @return
   */
  Map<String, Object> getTenantByUserAndServiceCategory(Long userId, Long categoryId);

  /**
   * 通过机构代码找租户
   * 
   * @param orgCode
   * @return
   */
  TenantInfo findTenantWithOrgCode(String orgCode);

}
