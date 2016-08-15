package com.csh.service; 

import com.csh.entity.estore.Order;
import com.csh.entity.estore.Refunds;
import com.csh.entity.estore.Returns;
import com.csh.framework.service.BaseService;

public interface ReturnsService extends BaseService<Returns,Long>{


  void approvedReturns(Order order, Returns returns);

  void confirmApprovedRefunds(Order order, Returns returns, Refunds refunds);

}