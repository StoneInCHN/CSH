package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.TenantApply;
import com.csh.dao.TenantApplyDao;
import com.csh.service.TenantApplyService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("tenantApplyServiceImpl")
public class TenantApplyServiceImpl extends BaseServiceImpl<TenantApply,Long> implements TenantApplyService {

      @Resource(name="tenantApplyDaoImpl")
      public void setBaseDao(TenantApplyDao tenantApplyDao) {
         super.setBaseDao(tenantApplyDao);
  }
}