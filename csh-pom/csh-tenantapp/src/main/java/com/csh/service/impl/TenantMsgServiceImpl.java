package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.TenantMsg;
import com.csh.dao.TenantMsgDao;
import com.csh.service.TenantMsgService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("tenantMsgServiceImpl")
public class TenantMsgServiceImpl extends BaseServiceImpl<TenantMsg,Long> implements TenantMsgService {

      @Resource(name="tenantMsgDaoImpl")
      public void setBaseDao(TenantMsgDao tenantMsgDao) {
         super.setBaseDao(tenantMsgDao);
  }
}