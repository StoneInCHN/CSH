package com.csh.service;

import com.csh.entity.EndUser;
import com.csh.entity.TenantEvaluate;
import com.csh.framework.service.BaseService;

public interface TenantEvaluateService extends BaseService<TenantEvaluate, Long> {

  /**
   * 用户购买商户服务后对该商户打分，并重新计算平均分
   * 
   * @param endUser
   * @param tenantId
   * @param score
   */
  public void doRateForTenant(EndUser endUser, Long tenantId, Integer score);
}
