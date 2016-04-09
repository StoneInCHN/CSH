package com.csh.service;

import com.csh.entity.Distributor;
import com.csh.entity.TenantApply;
import com.csh.framework.service.BaseService;

public interface TenantApplyService extends BaseService<TenantApply, Long> {
  
  void auditPassed(TenantApply tenantApply ,Distributor distributor);
  
}
