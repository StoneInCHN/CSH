package com.csh.service.impl; 

import javax.annotation.Resource; 
import javax.persistence.LockModeType;

import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.entity.commonenum.CommonEnum.AfterSalesStatus;
import com.csh.entity.commonenum.CommonEnum.OrderLogType;
import com.csh.entity.commonenum.CommonEnum.ShippingStatus;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.OrderItem;
import com.csh.entity.estore.OrderLog;
import com.csh.entity.estore.Product;
import com.csh.entity.estore.Refunds;
import com.csh.entity.estore.Returns;
import com.csh.entity.estore.ReturnsItem;
import com.csh.entity.estore.ShippingItem;
import com.csh.dao.OrderDao;
import com.csh.dao.OrderItemDao;
import com.csh.dao.OrderLogDao;
import com.csh.dao.ProductDao;
import com.csh.dao.RefundsDao;
import com.csh.dao.ReturnsDao;
import com.csh.service.ReturnsService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("returnsServiceImpl")
public class ReturnsServiceImpl extends BaseServiceImpl<Returns,Long> implements ReturnsService {
      @Resource(name="returnsDaoImpl")
      ReturnsDao returnsDao;
      @Resource(name="returnsDaoImpl")
      public void setBaseDao(ReturnsDao returnsDao) {
         super.setBaseDao(returnsDao);
      }
      @Resource(name="productDaoImpl")
      ProductDao productDao;
      @Resource(name="orderDaoImpl")
      OrderDao orderDao;
      @Resource(name="orderItemDaoImpl")
      OrderItemDao orderItemDao;
      @Resource(name="orderLogDaoImpl")
      OrderLogDao orderLogDao;
      @Resource(name="refundsDaoImpl")
      RefundsDao refundsDao;
      /**
       * 批准退货
       */
      @Override
      @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
      public void approvedReturns(Order order, Returns returns) {

        orderDao.lock(order, LockModeType.PESSIMISTIC_WRITE);

        returnsDao.persist(returns);
        for (ReturnsItem returnsItem : returns.getReturnsItems()) {
            OrderItem orderItem = order.getOrderItem(returnsItem.getSn());
            if (orderItem != null) {
                orderItemDao.lock(orderItem, LockModeType.PESSIMISTIC_WRITE);
                orderItem.setReturnQuantity(orderItem.getReturnQuantity() + returnsItem.getQuantity());
            }
        }
        if (order.getReturnQuantity() >= order.getShippedQuantity()) {
            order.setShippingStatus(ShippingStatus.returned);
        } else if (order.getReturnQuantity() > 0) {
            order.setShippingStatus(ShippingStatus.partialReturns);
        }
        order.setExpire(null);
        orderDao.merge(order);    
      }
      @Override
      public void confirmApprovedRefunds(Order order, Returns returns, Refunds refunds) {
        orderDao.lock(order, LockModeType.PESSIMISTIC_WRITE);
        returnsDao.merge(returns);
        refundsDao.persist(refunds);
        for (ReturnsItem returnsItem : returns.getReturnsItems()) {
          
          OrderItem orderItem = order.getOrderItem(returnsItem.getSn());
          if (orderItem != null) {
              Product product = orderItem.getProduct();
              productDao.lock(product, LockModeType.PESSIMISTIC_WRITE);
              if (product != null) {
                  if (product.getStock() != null) {
                      product.setStock(product.getStock() + returnsItem.getQuantity());
                  }
                  productDao.merge(product);
              }
              orderItemDao.lock(orderItem, LockModeType.PESSIMISTIC_WRITE);
              orderItem.setShippedQuantity(orderItem.getShippedQuantity() - returnsItem.getQuantity());
          }
        }
        if (order.getAmountPaid().compareTo(refunds.getAmount()) == 1) {
          order.setAfterSalesStatus(AfterSalesStatus.approvedPartialRefund);
        }else {
          order.setAfterSalesStatus(AfterSalesStatus.approvedRefund);
        }
//        order.setExpire(null);
        orderDao.merge(order);
        //保存订单日志
        OrderLog orderLog = new OrderLog();
        orderLog.setType(OrderLogType.returns);
        orderLog.setOperator(returns.getOperator() != null ? returns.getOperator() : null);
        orderLog.setOrder(order);
        orderLog.setTenantID(tenantAccountService.getCurrentTenantID());
        orderLogDao.persist(orderLog);   
        
      }
}