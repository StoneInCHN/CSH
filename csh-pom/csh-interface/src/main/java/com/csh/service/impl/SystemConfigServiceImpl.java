package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.SystemConfigDao;
import com.csh.entity.SystemConfig;
import com.csh.entity.commonenum.CommonEnum.SystemConfigKey;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.SystemConfigService;

@Service("systemConfigServiceImpl")
public class SystemConfigServiceImpl extends BaseServiceImpl<SystemConfig, Long> implements
    SystemConfigService {

  @Resource(name = "systemConfigDaoImpl")
  private SystemConfigDao systemConfigDao;

  @Resource(name = "systemConfigDaoImpl")
  public void setBaseDao(SystemConfigDao systemConfigDao) {
    super.setBaseDao(systemConfigDao);
  }

  @Override
  public SystemConfig getConfigByKey(SystemConfigKey key, Long tenantId) {
    return systemConfigDao.getConfigByKey(key, tenantId);
  }
}
