package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.VersionConfigDao;
import com.csh.entity.VersionConfig;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.ConfigMetaService;
import com.csh.service.VersionConfigService;

@Service ("versionConfigServiceImpl")
public class VersionConfigServiceImpl extends
    BaseServiceImpl<VersionConfig, Long> implements VersionConfigService
{

  @Resource (name = "versionConfigDaoImpl")
  private VersionConfigDao versionConfigDao;
  @Resource(name = "configMetaServiceImpl")
  private ConfigMetaService configMetaService;
  
  @Resource
  public void setBaseDao (VersionConfigDao versionConfigDao)
  {
    super.setBaseDao (versionConfigDao);
  }

}
