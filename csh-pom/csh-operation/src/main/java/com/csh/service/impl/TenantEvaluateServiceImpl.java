package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.TenantEvaluate;
import com.csh.dao.TenantEvaluateDao;
import com.csh.service.TenantEvaluateService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("tenantEvaluateServiceImpl")
public class TenantEvaluateServiceImpl extends BaseServiceImpl<TenantEvaluate,Long> implements TenantEvaluateService {

      @Resource(name="tenantEvaluateDaoImpl")
      public void setBaseDao(TenantEvaluateDao tenantEvaluateDao) {
         super.setBaseDao(tenantEvaluateDao);
  }
}