package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.csh.dao.AuthorityDao;
import com.csh.entity.ConfigMeta;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.AuthorityService;

/**
 * ServiceImpl - 权限
 * @author pengyanan
 *
 */
@Repository("authorityServiceImpl")
public class AuthorityServiceImpl extends BaseServiceImpl<ConfigMeta, Long> implements AuthorityService{
  @Resource(name="authorityDaoImpl")
  private AuthorityDao authorityDao;
  
  @Resource
  private void setBaseDao(AuthorityDao authorityDao){
    super.setBaseDao(authorityDao);
  }
}
