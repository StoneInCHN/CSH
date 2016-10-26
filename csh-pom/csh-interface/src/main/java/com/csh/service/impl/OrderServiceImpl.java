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
import com.csh.beans.Message;
import com.csh.beans.Setting;
import com.csh.dao.CartItemDao;
import com.csh.dao.OrderDao;
import com.csh.dao.OrderRelationDao;
import com.csh.dao.ProductDao;
import com.csh.dao.ReceiverAddressDao;
import com.csh.dao.TenantMsgDao;
import com.csh.dao.WalletDao;
import com.csh.entity.AccountBalance;
import com.csh.entity.EndUser;
import com.csh.entity.Sn.Type;
import com.csh.entity.TenantMsg;
import com.csh.entity.Wallet;
import com.csh.entity.WalletRecord;
import com.csh.entity.commonenum.CommonEnum.BalanceType;
import com.csh.entity.commonenum.CommonEnum.OrderLogType;
import com.csh.entity.commonenum.CommonEnum.OrderStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.entity.commonenum.CommonEnum.ServiceType;
import com.csh.entity.commonenum.CommonEnum.ShippingStatus;
import com.csh.entity.commonenum.CommonEnum.WalletType;
import com.csh.entity.estore.CartItem;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.OrderItem;
import com.csh.entity.estore.OrderLog;
import com.csh.entity.estore.OrderRelation;
import com.csh.entity.estore.Product;
import com.csh.entity.estore.ReceiverAddress;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.json.base.BaseResponse;
import com.csh.service.AccountBalanceService;
import com.csh.service.OrderService;
import com.csh.service.SnService;
import com.csh.utils.SettingUtils;

@Service("orderServiceImpl")
public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {

  @Resource(name = "accountBalanceServiceImpl")
  private AccountBalanceService accountBalanceService;

  @Resource(name = "orderDaoImpl")
  private OrderDao orderDao;

  @Resource(name = "snServiceImpl")
  private SnService snService;

  @Resource(name = "productDaoImpl")
  private ProductDao productDao;

  @Resource(name = "receiverAddressDaoImpl")
  private ReceiverAddressDao receiverAddressDao;

  @Resource(name = "cartItemDaoImpl")
  private CartItemDao cartItemDao;

  @Resource(name = "walletDaoImpl")
  private WalletDao walletDao;

  @Resource(name = "orderRelationDaoImpl")
  private OrderRelationDao orderRelationDao;

  @Resource(name = "tenantMsgDaoImpl")
  private TenantMsgDao tenantMsgDao;

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
    order.setSn(snService.generate(Type.order));
    order.setOrderStatus(OrderStatus.unconfirmed);
    order.setPaymentStatus(PaymentStatus.unpaid);
    order.setShippingStatus(ShippingStatus.unshipped);
    order.setTenantID(product.getTenantID());
    order.setAmountPaid(new BigDecimal(0));
    order.setFee(new BigDecimal(0));
    order.setTax(new BigDecimal(0));
    order.setFreight(new BigDecimal(0));
    order.setPromotionDiscount(new BigDecimal(0));
    order.setCouponDiscount(new BigDecimal(0));
    order.setOffsetAmount(new BigDecimal(0));
    order.setIsAllocatedStock(false);
    order.setShippingMethodName("免运费");
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
    orderItem.setThumbnail(product.getImage());
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
      response.setDesc(order.getId().toString());
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

    List<Long> orderIds = new ArrayList<Long>();
    for (Long tenantId : tenantIds) {
      Order order = new Order();
      List<OrderItem> orderItems = order.getOrderItems();
      order.setSn(snService.generate(Type.order));
      order.setIsInvoice(isInvoice);
      order.setInvoiceTitle(invoiceTitle);
      order.setOrderStatus(OrderStatus.unconfirmed);
      order.setPaymentStatus(PaymentStatus.unpaid);
      order.setShippingStatus(ShippingStatus.unshipped);
      order.setTenantID(tenantId);
      order.setAmountPaid(new BigDecimal(0));
      order.setFee(new BigDecimal(0));
      order.setTax(new BigDecimal(0));
      order.setFreight(new BigDecimal(0));
      order.setPromotionDiscount(new BigDecimal(0));
      order.setCouponDiscount(new BigDecimal(0));
      order.setOffsetAmount(new BigDecimal(0));
      order.setIsAllocatedStock(false);
      order.setShippingMethodName("免运费");
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
          orderItem.setThumbnail(product.getImage());
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
      for (CartItem cartItem : cartList) {
        cartItemDao.remove(cartItem);
      }
      orderDao.persist(order);
      orderIds.add(order.getId());
    }

    if (orderIds.size() > 1) {
      Long mainOrderId = orderIds.get(0);
      for (Long orderId : orderIds) {
        OrderRelation orderRelation = new OrderRelation();
        orderRelation.setMainOrderId(mainOrderId);
        orderRelation.setOrderId(orderId);
        orderRelationDao.persist(orderRelation);
      }
    }

    response.setDesc(orderIds.toString().substring(1, orderIds.toString().length() - 1));
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public Order updatePayStatus(Order order) {
    Wallet wallet = order.getEndUser().getWallet();

    if (PaymentType.WALLET.equals(order.getPaymentType())) {
      BigDecimal walletMoney = new BigDecimal(0);
      Boolean flag = true;
      AccountBalance accountBalance =
          accountBalanceService.getOfflineBalanceByTenant(order.getEndUser(), order.getTenantID());
      if (accountBalance != null && accountBalance.getBalance().compareTo(new BigDecimal(0)) != 0) {
        if (accountBalance.getBalance().compareTo(order.getAmount()) >= 0) {// 混合余额支付，线下余额大于等于支付金额
          order.setOfflineBalance(order.getAmount());
          accountBalance.setBalance(accountBalance.getBalance().subtract(order.getAmount()));
          flag = false;
        } else {// 混合余额支付，线下余额小于支付金额，剩余金额需普通余额支付,还需要支付的金额为walletMoney
          order.setOfflineBalance(accountBalance.getBalance());
          walletMoney = order.getAmount().subtract(accountBalance.getBalance());
          accountBalance.setBalance(new BigDecimal(0));
        }
        accountBalanceService.update(accountBalance);
      }

      WalletRecord walletRecord = new WalletRecord();
      walletRecord.setBalanceType(BalanceType.OUTCOME);
      walletRecord.setWallet(wallet);
      walletRecord.setWalletType(WalletType.MONEY);
      walletRecord.setRemark(Message.success("csh.wallet.purProduct.record").getContent());
      walletRecord.setMoney(order.getAmount());

      if (flag) {
        if (walletMoney.compareTo(new BigDecimal(0)) > 0) {// 余额混合支付
          wallet.setBalanceAmount(wallet.getBalanceAmount().subtract(walletMoney));
        } else {// 普通余额支付
          wallet.setBalanceAmount(wallet.getBalanceAmount().subtract(order.getAmount()));
        }
      }

      wallet.getWalletRecords().add(walletRecord);
      walletDao.merge(wallet);
    }

    // 消费兑换积分.规则 1元=1积分,不足1元送1积分(余额消费也送积分，因为余额充值时没有送了积分)
    if (order.getPaymentType() != null) {

      WalletRecord walletRecord = new WalletRecord();
      walletRecord.setWallet(wallet);
      walletRecord.setBalanceType(BalanceType.INCOME);
      walletRecord.setWalletType(WalletType.SCORE);
      walletRecord.setScore(order.getAmountPaid().setScale(0, BigDecimal.ROUND_UP));
      walletRecord.setRemark(Message.success("csh.wallet.score.comein.product").getContent());
      wallet.getWalletRecords().add(walletRecord);
      wallet.setScore(wallet.getScore().add(walletRecord.getScore())
          .setScale(0, BigDecimal.ROUND_UP));
      walletDao.merge(wallet);
    }

    OrderLog orderLog = new OrderLog();
    orderLog.setTenantID(order.getTenantID());
    orderLog.setType(OrderLogType.payment);
    orderLog.setOrder(order);
    order.getOrderLogs().add(orderLog);
    orderDao.merge(order);

    TenantMsg tenantMsg = new TenantMsg();
    tenantMsg.setIsPush(false);
    tenantMsg.setMsgType(ServiceType.PRODUCT);
    tenantMsg.setTenantID(order.getTenantID());
    tenantMsgDao.persist(tenantMsg);

    return order;
  }


  @SuppressWarnings("unused")
  private synchronized Boolean resetProductForCancelOrder(Product product, Integer quantity) {
    Integer stock = product.getStock() + quantity;
    product.setStock(stock);
    productDao.merge(product);
    return true;

  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public Order operation(Order order, OrderLogType oprType) {
    if (OrderLogType.cancel.equals(oprType)) {
      order.setOrderStatus(OrderStatus.cancelled);
      for (OrderItem orderItem : order.getOrderItems()) {
        Product product = orderItem.getProduct();
        resetProductForCancelOrder(product, orderItem.getQuantity());
      }
    } else if (OrderLogType.received.equals(oprType)) {
      order.setShippingStatus(ShippingStatus.received);
    }
    OrderLog orderLog = new OrderLog();
    orderLog.setTenantID(order.getTenantID());
    orderLog.setType(oprType);
    orderLog.setOrder(order);
    order.getOrderLogs().add(orderLog);
    orderDao.merge(order);
    return order;
  }

}
