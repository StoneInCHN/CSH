package com.csh.service; 

import com.csh.entity.TenantAccount;
import com.csh.entity.estore.Order;
import com.csh.framework.service.BaseService;

public interface OrderService extends BaseService<Order,Long>{

  void confirm(Order order, TenantAccount operator);
}