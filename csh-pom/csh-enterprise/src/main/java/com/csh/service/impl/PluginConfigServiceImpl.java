package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.PluginConfigDao;
import com.csh.entity.PluginConfig;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.PluginConfigService;

/**
 * 插件配置
 * 
 * @author shijun
 *
 */
@Service("pluginConfigServiceImpl")
public class PluginConfigServiceImpl extends BaseServiceImpl<PluginConfig, Long> implements PluginConfigService {

  @Resource(name = "pluginConfigDaoImpl")
  private PluginConfigDao pluginConfigDao;

  @Resource(name = "pluginConfigDaoImpl")
  public void setBaseDao(PluginConfigDao pluginConfigDao) {
      super.setBaseDao(pluginConfigDao);
  }

  @Transactional(readOnly = true)
  public boolean pluginIdExists(String pluginId) {
      return pluginConfigDao.pluginIdExists(pluginId);
  }

  @Transactional(readOnly = true)
  public PluginConfig findByPluginId(String pluginId) {
      return pluginConfigDao.findByPluginId(pluginId);
  }

}
