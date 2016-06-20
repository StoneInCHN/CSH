package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.OrderItem;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.OrderItemDao;
@Repository("orderItemDaoImpl")
public class OrderItemDaoImpl extends  BaseDaoImpl<OrderItem,Long> implements OrderItemDao {

}