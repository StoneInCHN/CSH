package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.Role;
import com.csh.dao.RoleDao;
import com.csh.service.RoleService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("roleServiceImpl")
public class RoleServiceImpl extends BaseServiceImpl<Role,Long> implements RoleService {

      @Resource(name="roleDaoImpl")
      public void setBaseDao(RoleDao roleDao) {
         super.setBaseDao(roleDao);
  }
}