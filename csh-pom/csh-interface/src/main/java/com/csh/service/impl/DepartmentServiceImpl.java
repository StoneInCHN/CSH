package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.Department;
import com.csh.dao.DepartmentDao;
import com.csh.service.DepartmentService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("departmentServiceImpl")
public class DepartmentServiceImpl extends BaseServiceImpl<Department,Long> implements DepartmentService {

      @Resource(name="departmentDaoImpl")
      public void setBaseDao(DepartmentDao departmentDao) {
         super.setBaseDao(departmentDao);
  }
}