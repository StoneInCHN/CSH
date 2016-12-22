package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.TenantImage;
import com.csh.dao.TenantImageDao;
import com.csh.service.TenantImageService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("tenantImageServiceImpl")
public class TenantImageServiceImpl extends BaseServiceImpl<TenantImage,Long> implements TenantImageService {

      @Resource(name="tenantImageDaoImpl")
      public void setBaseDao(TenantImageDao tenantImageDao) {
         super.setBaseDao(tenantImageDao);
  }
}