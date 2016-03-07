package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.ConfigTypeDao;
import com.csh.entity.ConfigType;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.ConfigTypeService;

@Service("configTypeServiceImpl")
public class ConfigTypeServiceImpl extends BaseServiceImpl<ConfigType, Long> implements ConfigTypeService {

  @Resource(name = "configTypeDaoImpl")
  private ConfigTypeDao configTypeDao;
  
  @Resource
  public void setBaseDao(ConfigTypeDao configTypeDao) {
    super.setBaseDao(configTypeDao);
  }

}
