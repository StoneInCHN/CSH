package com.csh.service;

import com.csh.entity.Role;
import com.csh.framework.service.BaseService;

public interface RoleService extends BaseService<Role, Long> {

   public boolean hasContainAdmin(Role role);
}
