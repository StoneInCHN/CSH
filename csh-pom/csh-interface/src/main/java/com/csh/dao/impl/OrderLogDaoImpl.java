package com.csh.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.csh.dao.OrderLogDao;
import com.csh.entity.commonenum.CommonEnum.OrderLogType;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.OrderLog;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("orderLogDaoImpl")
public class OrderLogDaoImpl extends BaseDaoImpl<OrderLog, Long> implements OrderLogDao {

  @Override
  public OrderLog getLogByOrderOpr(Order order, OrderLogType logType) {
    if (order == null || logType == null) {
      return null;
    }
    try {
      String jpql =
          "select orderLog from OrderLog orderLog where orderLog.order = :order and orderLog.type = :type";
      return entityManager.createQuery(jpql, OrderLog.class).setFlushMode(FlushModeType.COMMIT)
          .setParameter("order", order).setParameter("type", logType).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

}
