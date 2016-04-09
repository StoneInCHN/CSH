package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.PluginConfig;
import com.csh.dao.PluginConfigDao;
import com.csh.service.PluginConfigService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("pluginConfigServiceImpl")
public class PluginConfigServiceImpl extends BaseServiceImpl<PluginConfig,Long> implements PluginConfigService {

      @Resource(name="pluginConfigDaoImpl")
      public void setBaseDao(PluginConfigDao pluginConfigDao) {
         super.setBaseDao(pluginConfigDao);
  }
}