package com.csh.dao;

import com.csh.entity.Role;
import com.csh.framework.dao.BaseDao;

/**
 * Dao - 角色
 * 
 */
public interface RoleDao extends BaseDao<Role, Long> {
  boolean nameExists(String name, Long id);
  
  boolean hasContainAdmin(Role role);
}
