package com.csh.dao;

import com.csh.entity.PluginConfig;
import com.csh.framework.dao.BaseDao;

/**
 * 插件配置
 * 
 * @author shijun
 *
 */
public interface PluginConfigDao extends BaseDao<PluginConfig, Long> {

  /**
   * 判断插件ID是否存在
   * 
   * @param pluginId 插件ID
   * @return 插件ID是否存在
   */
  boolean pluginIdExists(String pluginId);

  /**
   * 根据插件ID查找插件配置
   * 
   * @param pluginId 插件ID
   * @return 插件配置，若不存在则返回null
   */
  PluginConfig findByPluginId(String pluginId);

}
