package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.OrderLogDao;
import com.csh.entity.commonenum.CommonEnum.OrderLogType;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.OrderLog;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.OrderLogService;

@Service("orderLogServiceImpl")
public class OrderLogServiceImpl extends BaseServiceImpl<OrderLog, Long> implements OrderLogService {

  @Resource(name = "orderLogDaoImpl")
  private OrderLogDao orderLogDao;

  @Resource(name = "orderLogDaoImpl")
  public void setBaseDao(OrderLogDao orderLogDao) {
    super.setBaseDao(orderLogDao);
  }

  @Override
  public OrderLog getLogByOrderOpr(Order order, OrderLogType logType) {
    return orderLogDao.getLogByOrderOpr(order, logType);
  }
}
