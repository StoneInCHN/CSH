package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.TenantInfo;
import com.csh.dao.TenantInfoDao;
import com.csh.service.TenantInfoService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("tenantInfoServiceImpl")
public class TenantInfoServiceImpl extends BaseServiceImpl<TenantInfo,Long> implements TenantInfoService {

      @Resource(name="tenantInfoDaoImpl")
      public void setBaseDao(TenantInfoDao tenantInfoDao) {
         super.setBaseDao(tenantInfoDao);
  }
}