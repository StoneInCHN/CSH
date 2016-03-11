package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.ConfigType;
import com.csh.dao.ConfigTypeDao;
import com.csh.service.ConfigTypeService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("configTypeServiceImpl")
public class ConfigTypeServiceImpl extends BaseServiceImpl<ConfigType,Long> implements ConfigTypeService {

      @Resource(name="configTypeDaoImpl")
      public void setBaseDao(ConfigTypeDao configTypeDao) {
         super.setBaseDao(configTypeDao);
  }
}