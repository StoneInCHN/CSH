package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.MetaPropertyDao;
import com.csh.entity.MetaProperty;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.MetaPropertyService;

@Service("metaPropertyServiceImpl")
public class MetaPropertyServiceImpl extends BaseServiceImpl<MetaProperty, Long> implements MetaPropertyService {

  @Resource(name = "metaPropertyDaoImpl")
  private MetaPropertyDao metaPropertyDao;
  
  @Resource
  public void setBaseDao(MetaPropertyDao metaPropertyDao) {
    super.setBaseDao(metaPropertyDao);
  }

}
