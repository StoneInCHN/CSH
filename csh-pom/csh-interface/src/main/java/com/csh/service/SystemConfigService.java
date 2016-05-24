package com.csh.service;

import com.csh.entity.SystemConfig;
import com.csh.entity.commonenum.CommonEnum.SystemConfigKey;
import com.csh.framework.service.BaseService;

public interface SystemConfigService extends BaseService<SystemConfig, Long> {


  /**
   * 获取系统配置项
   * 
   */
  SystemConfig getConfigByKey(SystemConfigKey key, Long tenantId);
}
