package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.RoleDao;
import com.csh.entity.*;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.RoleService;

/**
 * 
 * @author pengyanan
 *
 */
@Service("roleServiceImpl")
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

  @Resource(name = "roleDaoImpl")
  private void setBaseDao(RoleDao roleDao) {
    super.setBaseDao(roleDao);
  }
}
