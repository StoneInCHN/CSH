package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.Order;
import com.csh.dao.OrderDao;
import com.csh.service.OrderService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("orderServiceImpl")
public class OrderServiceImpl extends BaseServiceImpl<Order,Long> implements OrderService {

      @Resource(name="orderDaoImpl")
      public void setBaseDao(OrderDao orderDao) {
         super.setBaseDao(orderDao);
  }
}