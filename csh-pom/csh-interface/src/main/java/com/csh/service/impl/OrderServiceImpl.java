package com.csh.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.beans.CommonAttributes;
import com.csh.beans.Setting;
import com.csh.dao.CartItemDao;
import com.csh.dao.OrderDao;
import com.csh.dao.ProductDao;
import com.csh.dao.ReceiverAddressDao;
import com.csh.dao.SnDao;
import com.csh.entity.EndUser;
import com.csh.entity.Sn.Type;
import com.csh.entity.commonenum.CommonEnum.OrderLogType;
import com.csh.entity.commonenum.CommonEnum.OrderStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentStatus;
import com.csh.entity.commonenum.CommonEnum.ShippingStatus;
import com.csh.entity.estore.CartItem;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.OrderItem;
import com.csh.entity.estore.OrderLog;
import com.csh.entity.estore.Product;
import com.csh.entity.estore.ReceiverAddress;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.json.base.BaseResponse;
import com.csh.service.OrderService;
import com.csh.utils.SettingUtils;

@Service("orderServiceImpl")
public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {

  @Resource(name = "orderDaoImpl")
  private OrderDao orderDao;

  @Resource(name = "snDaoImpl")
  private SnDao snDao;

  @Resource(name = "productDaoImpl")
  private ProductDao productDao;

  @Resource(name = "receiverAddressDaoImpl")
  private ReceiverAddressDao receiverAddressDao;

  @Resource(name = "cartItemDaoImpl")
  private CartItemDao cartItemDao;

  @Resource(name = "orderDaoImpl")
  public void setBaseDao(OrderDao orderDao) {
    super.setBaseDao(orderDao);
  }

  @SuppressWarnings("unused")
  private synchronized Boolean checkProductRemainNum(Long productId, Integer quantity) {
    Product product = productDao.find(productId);
    if (product.getStock() - quantity < 0) {// 库存不足，无法创建订单
      return false;
    } else {
      product.setStock(product.getStock() - quantity);
      productDao.merge(product);
      return true;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public BaseResponse createOrderDirect(EndUser endUser, Long productId, Integer quantity,
      Long receiverId, Boolean isInvoice, String invoiceTitle) {

    BaseResponse response = new BaseResponse();
    Setting setting = SettingUtils.get();
    ReceiverAddress receiver = receiverAddressDao.find(receiverId);
    Product product = productDao.find(productId);
    Order order = new Order();
    order.setIsInvoice(isInvoice);
    order.setInvoiceTitle(invoiceTitle);
    order.setSn(snDao.generate(Type.order));
    order.setOrderStatus(OrderStatus.unconfirmed);
    order.setPaymentStatus(PaymentStatus.unpaid);
    order.setShippingStatus(ShippingStatus.unshipped);
    order.setTenantID(product.getTenantID());
    order.setAmountPaid(new BigDecimal(0));
    order.setFee(new BigDecimal(0));
    order.setTax(new BigDecimal(0));
    order.setPromotionDiscount(new BigDecimal(0));
    order.setCouponDiscount(new BigDecimal(0));
    order.setOffsetAmount(new BigDecimal(0));
    order.setEndUser(endUser);
    order.setExpire(DateUtils.addMinutes(new Date(), setting.getOrderTimeOut()));
    if (receiver != null) {
      order.setConsignee(receiver.getConsignee());
      order.setAreaName(receiver.getAreaName());
      order.setAddress(receiver.getAddress());
      order.setZipCode(receiver.getZipCode());
      order.setPhone(receiver.getPhone());
      order.setArea(receiver.getArea());
    }

    List<OrderItem> orderItems = order.getOrderItems();
    OrderItem orderItem = new OrderItem();
    orderItem.setSn(product.getSn());
    orderItem.setName(product.getName());
    orderItem.setFullName(product.getFullName());
    orderItem.setPrice(product.getPrice());
    orderItem.setWeight(product.getWeight());
    orderItem.setThumbnail(product.getThumbnail());
    orderItem.setIsGift(false);
    orderItem.setQuantity(quantity);
    orderItem.setShippedQuantity(0);
    orderItem.setReturnQuantity(0);
    orderItem.setProduct(product);
    orderItem.setTenantID(order.getTenantID());
    orderItem.setOrder(order);
    orderItems.add(orderItem);

    Set<OrderLog> orderLogs = order.getOrderLogs();
    OrderLog orderLog = new OrderLog();
    orderLog.setTenantID(order.getTenantID());
    orderLog.setType(OrderLogType.create);
    orderLog.setOrder(order);
    orderLog.setOperator(endUser.getUserName());
    // orderLog.setContent("创建订单");
    orderLogs.add(orderLog);

    if (checkProductRemainNum(productId, quantity)) {
      orderDao.persist(order);
      response.setCode(CommonAttributes.SUCCESS);
    } else {
      response.setCode(CommonAttributes.FAIL_COMMON);
      response.setDesc(product.getName());
    }
    return response;

  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public BaseResponse createOrderByCart(EndUser endUser, Long[] cartItems, Long receiverId,
      Boolean isInvoice, String invoiceTitle) {
    BaseResponse response = new BaseResponse();
    Setting setting = SettingUtils.get();
    ReceiverAddress receiver = receiverAddressDao.find(receiverId);

    Set<Long> tenantIds = new HashSet<Long>();

    List<CartItem> cartList = new ArrayList<CartItem>();
    for (Long itemId : cartItems) {
      CartItem cartItem = cartItemDao.find(itemId);
      tenantIds.add(cartItem.getTenantID());
      cartList.add(cartItem);
    }


    for (Long tenantId : tenantIds) {
      Order order = new Order();
      List<OrderItem> orderItems = order.getOrderItems();
      order.setSn(snDao.generate(Type.order));
      order.setIsInvoice(isInvoice);
      order.setInvoiceTitle(invoiceTitle);
      order.setOrderStatus(OrderStatus.unconfirmed);
      order.setPaymentStatus(PaymentStatus.unpaid);
      order.setShippingStatus(ShippingStatus.unshipped);
      order.setTenantID(tenantId);
      order.setAmountPaid(new BigDecimal(0));
      order.setFee(new BigDecimal(0));
      order.setTax(new BigDecimal(0));
      order.setPromotionDiscount(new BigDecimal(0));
      order.setCouponDiscount(new BigDecimal(0));
      order.setOffsetAmount(new BigDecimal(0));
      order.setEndUser(endUser);
      order.setExpire(DateUtils.addMinutes(new Date(), setting.getOrderTimeOut()));
      if (receiver != null) {
        order.setConsignee(receiver.getConsignee());
        order.setAreaName(receiver.getAreaName());
        order.setAddress(receiver.getAddress());
        order.setZipCode(receiver.getZipCode());
        order.setPhone(receiver.getPhone());
        order.setArea(receiver.getArea());
      }
      Set<OrderLog> orderLogs = order.getOrderLogs();
      OrderLog orderLog = new OrderLog();
      orderLog.setTenantID(tenantId);
      orderLog.setType(OrderLogType.create);
      orderLog.setOrder(order);
      orderLog.setOperator(endUser.getUserName());
      // orderLog.setContent("创建订单");
      orderLogs.add(orderLog);

      for (CartItem cartItem : cartList) {
        if (tenantId.equals(cartItem.getTenantID())) {
          Product product = cartItem.getProduct();
          OrderItem orderItem = new OrderItem();
          orderItem.setSn(product.getSn());
          orderItem.setName(product.getName());
          orderItem.setFullName(product.getFullName());
          orderItem.setPrice(product.getPrice());
          orderItem.setWeight(product.getWeight());
          orderItem.setThumbnail(product.getThumbnail());
          orderItem.setIsGift(false);
          orderItem.setQuantity(cartItem.getQuantity());
          orderItem.setShippedQuantity(0);
          orderItem.setReturnQuantity(0);
          orderItem.setProduct(product);
          orderItem.setTenantID(tenantId);
          orderItem.setOrder(order);
          orderItems.add(orderItem);
          if (!checkProductRemainNum(product.getId(), cartItem.getQuantity())) {
            response.setCode(CommonAttributes.FAIL_COMMON);
            response.setDesc(product.getName());
            return response;
          }
        }
      }
      orderDao.persist(order);
    }
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }
}
