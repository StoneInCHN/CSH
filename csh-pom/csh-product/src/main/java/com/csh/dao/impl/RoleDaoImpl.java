package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.RoleDao;
import com.csh.entity.Role;
import com.csh.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 角色
 * @author pengyanan
 *
 */
@Repository("roleDaoImpl")
public class RoleDaoImpl extends BaseDaoImpl<Role, Long> implements RoleDao {

}
