package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.Order;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.OrderDao;
@Repository("orderDaoImpl")
public class OrderDaoImpl extends  BaseDaoImpl<Order,Long> implements OrderDao {

}