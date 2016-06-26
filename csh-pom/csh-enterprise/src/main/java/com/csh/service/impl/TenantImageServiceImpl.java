package com.csh.service.impl; 

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.TenantImageDao;
import com.csh.entity.TenantImage;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.TenantImageService;

@Service("tenantImageServiceImpl")
public class TenantImageServiceImpl extends BaseServiceImpl<TenantImage,Long> implements TenantImageService {

      @Resource(name="tenantImageDaoImpl")
      public void setBaseDao(TenantImageDao tenantImageDao) {
         super.setBaseDao(tenantImageDao);
  }
}