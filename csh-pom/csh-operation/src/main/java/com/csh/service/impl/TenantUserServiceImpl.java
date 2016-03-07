package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.TenantUserDao;
import com.csh.entity.TenantUser;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.TenantUserService;

@Service("tenantUserServiceImpl")
public class TenantUserServiceImpl extends BaseServiceImpl<TenantUser, Long> implements TenantUserService {

  @Resource(name = "tenantUserDaoImpl")
  private TenantUserDao tenantUserDao;
  
  @Resource
  public void setBaseDao(TenantUserDao tenantUserDao) {
    super.setBaseDao(tenantUserDao);
  }



}
