package com.csh.service; 

import com.csh.entity.estore.Order;
import com.csh.entity.estore.Refunds;
import com.csh.framework.service.BaseService;

public interface RefundsService extends BaseService<Refunds,Long>{

  void saveRefunds(Order order, Refunds refunds);

}