package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.Role;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.RoleDao;
@Repository("roleDaoImpl")
public class RoleDaoImpl extends  BaseDaoImpl<Role,Long> implements RoleDao {

}