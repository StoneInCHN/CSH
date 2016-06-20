package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.OrderLog;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.OrderLogDao;
@Repository("orderLogDaoImpl")
public class OrderLogDaoImpl extends  BaseDaoImpl<OrderLog,Long> implements OrderLogDao {

}