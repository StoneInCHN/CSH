package com.csh.dao;

import com.csh.entity.SystemConfig;
import com.csh.entity.commonenum.CommonEnum.SystemConfigKey;
import com.csh.framework.dao.BaseDao;

public interface SystemConfigDao extends BaseDao<SystemConfig, Long> {

  /**
   * 获取系统配置项
   * 
   */
  SystemConfig getConfigByKey(SystemConfigKey key, Long tenantId);
}
