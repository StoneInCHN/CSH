package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.VersionConfig;
import com.csh.dao.VersionConfigDao;
import com.csh.service.VersionConfigService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("versionConfigServiceImpl")
public class VersionConfigServiceImpl extends BaseServiceImpl<VersionConfig,Long> implements VersionConfigService {

      @Resource(name="versionConfigDaoImpl")
      public void setBaseDao(VersionConfigDao versionConfigDao) {
         super.setBaseDao(versionConfigDao);
  }
}