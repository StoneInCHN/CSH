package com.csh.service;

import java.util.Set;

import com.csh.entity.TenantMsg;
import com.csh.framework.service.BaseService;

public interface TenantMsgService extends BaseService<TenantMsg, Long> {

  Set<Integer> getPushMsgCat(Long tenantId);

}
