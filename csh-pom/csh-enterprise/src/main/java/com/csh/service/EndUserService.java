package com.csh.service; 

import com.csh.entity.EndUser;
import com.csh.framework.service.BaseService;

public interface EndUserService extends BaseService<EndUser,Long>{
  Long countUserByTenantID(Long tenantID);
}