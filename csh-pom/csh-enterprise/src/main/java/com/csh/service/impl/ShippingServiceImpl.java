package com.csh.service.impl; 

import javax.annotation.Resource; 
import javax.persistence.LockModeType;

import org.springframework.stereotype.Service; 




import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.entity.commonenum.CommonEnum.OrderLogType;
import com.csh.entity.commonenum.CommonEnum.ShippingStatus;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.OrderItem;
import com.csh.entity.estore.OrderLog;
import com.csh.entity.estore.Product;
import com.csh.entity.estore.Shipping;
import com.csh.entity.estore.ShippingItem;
import com.csh.dao.OrderDao;
import com.csh.dao.OrderItemDao;
import com.csh.dao.OrderLogDao;
import com.csh.dao.ProductDao;
import com.csh.dao.ShippingDao;
import com.csh.service.ShippingService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("shippingServiceImpl")
public class ShippingServiceImpl extends BaseServiceImpl<Shipping,Long> implements ShippingService {
      @Resource(name="shippingDaoImpl")
      ShippingDao shippingDao;
      @Resource(name="shippingDaoImpl")
      public void setBaseDao(ShippingDao shippingDao) {
         super.setBaseDao(shippingDao);
      }
      @Resource(name="productDaoImpl")
      ProductDao productDao;
      @Resource(name="orderDaoImpl")
      OrderDao orderDao;
      @Resource(name="orderItemDaoImpl")
      OrderItemDao orderItemDao;
      @Resource(name="orderLogDaoImpl")
      OrderLogDao orderLogDao;      
      
      @Override
      @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
      public void saveShipping(Order order, Shipping shipping) {
          for (OrderItem orderItem : order.getOrderItems()) {
              if (orderItem != null) {
                  Product product = orderItem.getProduct();
                  productDao.lock(product, LockModeType.PESSIMISTIC_WRITE);
                  if (product != null && product.getStock() != null) {
                      product.setAllocatedStock(product.getAllocatedStock() + (orderItem.getQuantity() - orderItem.getShippedQuantity()));
                      productDao.merge(product);
                      //orderDao.flush();
                  }
              }
          }
          order.setIsAllocatedStock(true);

      shipping.setOrder(order);
      shippingDao.persist(shipping);
      for (ShippingItem shippingItem : shipping.getShippingItems()) {
          OrderItem orderItem = order.getOrderItem(shippingItem.getSn());
          if (orderItem != null) {
              Product product = orderItem.getProduct();
              productDao.lock(product, LockModeType.PESSIMISTIC_WRITE);
              if (product != null) {
                  if (product.getStock() != null) {
                      product.setStock(product.getStock() - shippingItem.getQuantity());
                      if (order.getIsAllocatedStock()) {
                          product.setAllocatedStock(product.getAllocatedStock() - shippingItem.getQuantity());
                      }
                  }
                  productDao.merge(product);
//                  orderDao.flush();
              }
              orderItemDao.lock(orderItem, LockModeType.PESSIMISTIC_WRITE);
              orderItem.setShippedQuantity(orderItem.getShippedQuantity() + shippingItem.getQuantity());
          }
      }
      if (order.getShippedQuantity() >= order.getQuantity()) {
          order.setShippingStatus(ShippingStatus.shipped);
          order.setIsAllocatedStock(false);
      } else if (order.getShippedQuantity() > 0) {
          order.setShippingStatus(ShippingStatus.partialShipment);
      }
      order.setExpire(null);
      orderDao.merge(order);
      //保存订单日志
      OrderLog orderLog = new OrderLog();
      orderLog.setType(OrderLogType.shipping);
      orderLog.setOperator(shipping.getOperator() != null ? shipping.getOperator() : null);
      orderLog.setOrder(order);
      orderLog.setTenantID(tenantAccountService.getCurrentTenantID());
      orderLogDao.persist(orderLog);
     }
}