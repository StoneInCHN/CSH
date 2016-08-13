package com.csh.dao;

import com.csh.entity.commonenum.CommonEnum.OrderLogType;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.OrderLog;
import com.csh.framework.dao.BaseDao;

public interface OrderLogDao extends BaseDao<OrderLog, Long> {

  /**
   * 查询订单log
   * 
   * @param order
   * @param logType
   * @return
   */
  public OrderLog getLogByOrderOpr(Order order, OrderLogType logType);
}
