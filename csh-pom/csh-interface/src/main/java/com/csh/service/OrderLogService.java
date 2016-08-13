package com.csh.service;

import com.csh.entity.commonenum.CommonEnum.OrderLogType;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.OrderLog;
import com.csh.framework.service.BaseService;

public interface OrderLogService extends BaseService<OrderLog, Long> {

  /**
   * 查询订单log
   * 
   * @param order
   * @param logType
   * @return
   */
  public OrderLog getLogByOrderOpr(Order order, OrderLogType logType);
}
