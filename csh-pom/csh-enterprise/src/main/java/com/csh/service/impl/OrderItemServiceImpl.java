package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.OrderItem;
import com.csh.dao.OrderItemDao;
import com.csh.service.OrderItemService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("orderItemServiceImpl")
public class OrderItemServiceImpl extends BaseServiceImpl<OrderItem,Long> implements OrderItemService {

      @Resource(name="orderItemDaoImpl")
      public void setBaseDao(OrderItemDao orderItemDao) {
         super.setBaseDao(orderItemDao);
  }
}