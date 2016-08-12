package com.csh.service; 

import com.csh.entity.estore.Order;
import com.csh.entity.estore.Shipping;
import com.csh.framework.service.BaseService;

public interface ShippingService extends BaseService<Shipping,Long>{

  void saveShipping(Order order, Shipping shipping);

}