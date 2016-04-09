package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.TenantAccount;
import com.csh.dao.TenantAccountDao;
import com.csh.service.TenantAccountService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("tenantAccountServiceImpl")
public class TenantAccountServiceImpl extends BaseServiceImpl<TenantAccount,Long> implements TenantAccountService {

      @Resource(name="tenantAccountDaoImpl")
      public void setBaseDao(TenantAccountDao tenantAccountDao) {
         super.setBaseDao(tenantAccountDao);
  }
}