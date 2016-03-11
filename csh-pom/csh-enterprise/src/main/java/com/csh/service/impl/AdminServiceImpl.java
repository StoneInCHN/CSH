package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.Admin;
import com.csh.dao.AdminDao;
import com.csh.service.AdminService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("adminServiceImpl")
public class AdminServiceImpl extends BaseServiceImpl<Admin,Long> implements AdminService {

      @Resource(name="adminDaoImpl")
      public void setBaseDao(AdminDao adminDao) {
         super.setBaseDao(adminDao);
  }
}