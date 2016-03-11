package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.Department;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.DepartmentDao;
@Repository("departmentDaoImpl")
public class DepartmentDaoImpl extends  BaseDaoImpl<Department,Long> implements DepartmentDao {

}