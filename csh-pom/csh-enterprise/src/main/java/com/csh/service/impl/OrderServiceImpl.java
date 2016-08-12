package com.csh.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.util.Assert;

import com.csh.beans.Message;
import com.csh.dao.OrderDao;
import com.csh.dao.OrderLogDao;
import com.csh.entity.TenantAccount;
import com.csh.entity.commonenum.CommonEnum.OrderLogType;
import com.csh.entity.commonenum.CommonEnum.OrderStatus;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.OrderLog;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.OrderService;
import com.csh.utils.SpringUtils;

@Service("orderServiceImpl")
public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {

  @Resource(name = "orderDaoImpl")
  private OrderDao orderDao;

  @Resource(name = "orderLogDaoImpl")
  private OrderLogDao orderLogDao;

  @Resource(name = "orderDaoImpl")
  public void setBaseDao(OrderDao orderDao) {
    super.setBaseDao(orderDao);
  }

  @Transactional
  public void confirm(Order order, TenantAccount operator) {
    Assert.notNull(order);
    order.setOrderStatus(OrderStatus.confirmed);
    orderDao.merge(order);
    OrderLog orderLog = new OrderLog();
    orderLog.setType(OrderLogType.confirm);
    orderLog.setOperator(operator != null ? operator.getUserName() : null);
    orderLog.setOrder(order);
    orderLog.setContent(SpringUtils.getMessage("csh.order.orderStatus.change.confirmed"));
    orderLogDao.persist(orderLog);
  }

}
