package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.SystemConfig;
import com.csh.dao.SystemConfigDao;
import com.csh.service.SystemConfigService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("systemConfigServiceImpl")
public class SystemConfigServiceImpl extends BaseServiceImpl<SystemConfig,Long> implements SystemConfigService {

      @Resource(name="systemConfigDaoImpl")
      public void setBaseDao(SystemConfigDao systemConfigDao) {
         super.setBaseDao(systemConfigDao);
  }
}