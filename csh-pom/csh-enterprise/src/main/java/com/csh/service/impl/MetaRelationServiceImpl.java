package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.MetaRelationDao;
import com.csh.entity.MetaRelation;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.MetaRelationService;

@Service ("metaRelationServiceImpl")
public class MetaRelationServiceImpl extends
    BaseServiceImpl<MetaRelation, Long> implements MetaRelationService
{

  @Resource (name = "metaRelationDaoImpl")
  private MetaRelationDao metaRelationDao;

  @Resource
  public void setBaseDao (MetaRelationDao metaRelationDao)
  {
    super.setBaseDao (metaRelationDao);
  }

}
