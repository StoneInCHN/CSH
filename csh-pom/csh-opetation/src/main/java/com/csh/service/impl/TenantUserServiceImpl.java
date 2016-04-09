package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.TenantUser;
import com.csh.dao.TenantUserDao;
import com.csh.service.TenantUserService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("tenantUserServiceImpl")
public class TenantUserServiceImpl extends BaseServiceImpl<TenantUser,Long> implements TenantUserService {

      @Resource(name="tenantUserDaoImpl")
      public void setBaseDao(TenantUserDao tenantUserDao) {
         super.setBaseDao(tenantUserDao);
  }
}