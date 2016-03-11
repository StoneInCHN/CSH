package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.ConfigMeta;
import com.csh.dao.ConfigMetaDao;
import com.csh.service.ConfigMetaService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("configMetaServiceImpl")
public class ConfigMetaServiceImpl extends BaseServiceImpl<ConfigMeta,Long> implements ConfigMetaService {

      @Resource(name="configMetaDaoImpl")
      public void setBaseDao(ConfigMetaDao configMetaDao) {
         super.setBaseDao(configMetaDao);
  }
}