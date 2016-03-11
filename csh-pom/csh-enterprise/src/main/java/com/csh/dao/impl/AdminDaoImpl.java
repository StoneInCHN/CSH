package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.Admin;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.AdminDao;
@Repository("adminDaoImpl")
public class AdminDaoImpl extends  BaseDaoImpl<Admin,Long> implements AdminDao {

}