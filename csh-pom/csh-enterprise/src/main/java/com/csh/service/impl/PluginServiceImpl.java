package com.csh.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.stereotype.Service;

import com.csh.plugin.StoragePlugin;
import com.csh.service.PluginService;

/**
 * 插件
 */
@Service("pluginServiceImpl")
public class PluginServiceImpl implements PluginService {

  @Resource
  private List<StoragePlugin> storagePlugins = new ArrayList<StoragePlugin>();
  @Resource
  private Map<String, StoragePlugin> storagePluginMap = new HashMap<String, StoragePlugin>();


  public List<StoragePlugin> getStoragePlugins() {
    Collections.sort(storagePlugins);
    return storagePlugins;
  }

  public List<StoragePlugin> getStoragePlugins(final boolean isEnabled) {
    List<StoragePlugin> result = new ArrayList<StoragePlugin>();
    CollectionUtils.select(storagePlugins, new Predicate() {
      public boolean evaluate(Object object) {
        StoragePlugin storagePlugin = (StoragePlugin) object;
        return storagePlugin.getIsEnabled() == isEnabled;
      }
    }, result);
    Collections.sort(result);
    return result;
  }

  public StoragePlugin getStoragePlugin(String id) {
    return storagePluginMap.get(id);
  }

}
