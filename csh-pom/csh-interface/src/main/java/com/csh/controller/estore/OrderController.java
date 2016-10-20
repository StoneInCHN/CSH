package com.csh.controller.estore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.aspect.UserValidCheck;
import com.csh.beans.CommonAttributes;
import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.AccountBalance;
import com.csh.entity.EndUser;
import com.csh.entity.Wallet;
import com.csh.entity.commonenum.CommonEnum.OrderLogType;
import com.csh.entity.commonenum.CommonEnum.OrderStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.entity.commonenum.CommonEnum.ShippingStatus;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.OrderItem;
import com.csh.entity.estore.OrderLog;
import com.csh.entity.estore.Product;
import com.csh.entity.estore.Returns;
import com.csh.entity.estore.ReturnsItem;
import com.csh.entity.estore.Review;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.ordering.Ordering.Direction;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.base.BaseResponse;
import com.csh.json.base.PageResponse;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.base.ResponseOne;
import com.csh.json.request.OrderRequest;
import com.csh.service.AccountBalanceService;
import com.csh.service.EndUserService;
import com.csh.service.OrderLogService;
import com.csh.service.OrderService;
import com.csh.service.ProductService;
import com.csh.service.ReturnsService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.PayUtil;
import com.csh.utils.TimeUtils;
import com.csh.utils.TokenGenerator;


/**
 * Controller - 订单
 * 
 * @author sujinxuan
 *
 */
@Controller("OrderController")
@RequestMapping("/estore/order")
public class OrderController extends MobileBaseController {

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "productServiceImpl")
  private ProductService productService;

  @Resource(name = "orderServiceImpl")
  private OrderService orderService;

  @Resource(name = "orderLogServiceImpl")
  private OrderLogService orderLogService;

  @Resource(name = "returnsServiceImpl")
  private ReturnsService returnsService;

  @Resource(name = "accountBalanceServiceImpl")
  private AccountBalanceService accountBalanceService;


  private ResponseOne<Map<String, Object>> payForMultiTenant(Long userId, Long[] orderIds,
      PaymentType paymentType, HttpServletRequest httpReq, String userToken) {
    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
    if (LogUtil.isDebugEnabled(OrderController.class)) {
      LogUtil.debug(OrderController.class, "payForMultiTenant",
          "pay for multi tenant orders.UserId: %s,paymentType: %s", userId, paymentType);
    }

    BigDecimal payAmount = new BigDecimal(0);
    EndUser endUser = endUserService.find(userId);
    Wallet wallet = endUser.getWallet();
    BigDecimal walletMoney = wallet.getBalanceAmount();
    List<Order> orders = new ArrayList<Order>();
    for (Long orderId : orderIds) {
      Order order = orderService.find(orderId);
      orders.add(order);
      if (LogUtil.isDebugEnabled(OrderController.class)) {
        LogUtil.debug(OrderController.class, "payForMultiTenant",
            "pay for multi tenant order.UserId: %s,orderId: %s,paymentType: %s,amount: %s", userId,
            orderId, paymentType, order.getAmount());
      }

      payAmount = payAmount.add(order.getAmount());
      /**
       * 余额支付
       */
      if (PaymentType.WALLET.equals(paymentType)) {
        AccountBalance accountBalance =
            accountBalanceService.getOfflineBalanceByTenant(endUser, order.getTenantID());
        if (accountBalance != null) {
          walletMoney = walletMoney.add(accountBalance.getBalance());
        }
      }
    }

    if (payAmount.compareTo(walletMoney) > 0) {// 用户钱包余额不足
      response.setCode(CommonAttributes.FAIL_COMMON);
      response.setDesc(Message.error("csh.wallet.money.insufficient").getContent());
      return response;
    }

    Order order = orders.get(0);
    if (payAmount.compareTo(new BigDecimal(0)) <= 0) {// 抵扣完之后订单金额
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("out_trade_no", order.getSn());
      map.put("isNeedPay", false);
      response.setMsg(map);
      response.setCode(CommonAttributes.SUCCESS);
    } else {
      if (PaymentType.WECHAT.equals(paymentType)) {// 微信支付
        try {
          BigDecimal weChatPrice = order.getAmount().multiply(new BigDecimal(100));
          response =
              PayUtil.wechat(
                  "E" + order.getSn() + "_" + TimeUtils.format("mmss", new Date().getTime()),
                  order.getSn(), httpReq.getRemoteAddr(), order.getId().toString(),
                  weChatPrice.intValue() + "");
          response.getMsg().put("isNeedPay", true);
        } catch (Exception e) {
          e.printStackTrace();
        }

      } else {// 支付宝和余额支付
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("out_trade_no", "2_" + order.getSn());
        map.put("isNeedPay", true);
        response.setMsg(map);
        response.setCode(CommonAttributes.SUCCESS);
      }
    }

    for (Order updateOrder : orders) {
      updateOrder.setPaymentType(paymentType);
      orderService.update(updateOrder);
    }


    String newtoken = TokenGenerator.generateToken(userToken);
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);

    return response;

  }

  /**
   * 用户支付订单
   * 
   * @return
   */
  @RequestMapping(value = "/pay", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseOne<Map<String, Object>> pay(@RequestBody OrderRequest request,
      HttpServletRequest httpReq) {

    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();

    Long userId = request.getUserId();
    String token = request.getToken();
    Long[] orderIds = request.getOrderIds();
    PaymentType paymentType = request.getPaymentType();


    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    /**
     * 包含多个租户订单
     */
    if (orderIds.length != 1) {
      return payForMultiTenant(userId, orderIds, paymentType, httpReq, userToken);
    }

    Long orderId = orderIds[0];
    Order order = orderService.find(orderId);
    if (LogUtil.isDebugEnabled(OrderController.class)) {
      LogUtil.debug(OrderController.class, "pay",
          "pay order.UserId: %s,orderId: %s,paymentType: %s,amount: %s", userId, orderId,
          paymentType, order.getAmount());
    }

    EndUser endUser = endUserService.find(userId);
    /**
     * 余额支付
     */
    if (PaymentType.WALLET.equals(paymentType)) {
      Wallet wallet = endUser.getWallet();
      AccountBalance accountBalance =
          accountBalanceService.getOfflineBalanceByTenant(endUser, order.getTenantID());
      BigDecimal walletMoney = wallet.getBalanceAmount();
      if (accountBalance != null) {
        walletMoney = wallet.getBalanceAmount().add(accountBalance.getBalance());
      }

      if (order.getAmount().compareTo(walletMoney) > 0) {// 用户钱包余额不足
        response.setCode(CommonAttributes.FAIL_COMMON);
        response.setDesc(Message.error("csh.wallet.money.insufficient").getContent());
        return response;
      }
    }

    BigDecimal amount = order.getAmount();
    if (amount.compareTo(new BigDecimal(0)) <= 0) {// 抵扣完之后订单金额
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("out_trade_no", order.getSn());
      map.put("isNeedPay", false);
      response.setMsg(map);
      response.setCode(CommonAttributes.SUCCESS);
    } else {
      if (PaymentType.WECHAT.equals(paymentType)) {// 微信支付
        try {
          BigDecimal weChatPrice = order.getAmount().multiply(new BigDecimal(100));
          response =
              PayUtil.wechat(
                  "E" + order.getSn() + "_" + TimeUtils.format("mmss", new Date().getTime()),
                  order.getSn(), httpReq.getRemoteAddr(), order.getId().toString(),
                  weChatPrice.intValue() + "");
          response.getMsg().put("isNeedPay", true);
        } catch (Exception e) {
          e.printStackTrace();
        }

      } else {// 支付宝和余额支付
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("out_trade_no", "2_" + order.getSn());
        map.put("isNeedPay", true);
        response.setMsg(map);
        response.setCode(CommonAttributes.SUCCESS);
      }
    }

    order.setPaymentType(paymentType);
    orderService.update(order);

    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    return response;
  }

  /**
   * 更新支付状态
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/updatePayStatus", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse updatePayStatus(@RequestBody OrderRequest request) {

    BaseResponse response = new BaseResponse();
    Long userId = request.getUserId();
    String token = request.getToken();
    Long[] orderIds = request.getOrderIds();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    if (orderIds != null && orderIds.length > 0) {
      for (Long orderId : orderIds) {
        Order order = orderService.find(orderId);
        order.setPaymentStatus(PaymentStatus.paid);
        order.setAmountPaid(order.getAmount());
        if (LogUtil.isDebugEnabled(OrderController.class)) {
          LogUtil
              .debug(
                  OrderController.class,
                  "updatePayStatus",
                  "Update Order pay status. UserName: %s, TenantId: %s, orderId: %s, amount: %s, paymentType: %s, paymentStatus: %s, sn: %s",
                  endUser.getUserName(), order.getTenantID(), order.getId(), order.getAmount(),
                  order.getPaymentType(), order.getPaymentStatus(), order.getSn());
        }

        orderService.updatePayStatus(order);
      }
    }



    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 用户创建订单
   * 
   * @return
   */
  @RequestMapping(value = "/create", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse create(@RequestBody OrderRequest request) {

    BaseResponse response = new BaseResponse();

    Long userId = request.getUserId();
    String token = request.getToken();
    Long productId = request.getProductId();
    Integer quantity = request.getQuantity();
    Long receiverId = request.getReceiverId();
    Long[] cartItemIds = request.getItemIds();
    Boolean isInvoice = request.getIsInvoice();
    String invoiceTitle = request.getInvoiceTitle();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    String cartItemIdsStr = null;
    if (cartItemIds != null) {
      for (Long cartItemId : cartItemIds) {
        cartItemIdsStr += cartItemId + " ";
      }
    }

    if (LogUtil.isDebugEnabled(OrderController.class)) {
      LogUtil.debug(OrderController.class, "create",
          "confirm order.UserId: %s,productId: %s,quantity: %s,cartItemIds: %s", userId, productId,
          quantity, cartItemIdsStr);
    }

    EndUser endUser = endUserService.find(userId);

    if (productId != null && quantity != null) {// 直接下订单
      response =
          orderService.createOrderDirect(endUser, productId, quantity, receiverId, isInvoice,
              invoiceTitle);
    } else if (cartItemIds != null && cartItemIds.length > 0) {// 通过购物车下订单
      response =
          orderService.createOrderByCart(endUser, cartItemIds, receiverId, isInvoice, invoiceTitle);
    }
    if (CommonAttributes.FAIL_COMMON.equals(response.getCode())) {
      response.setDesc(Message.error("csh.estore.product.stock.insufficient", response.getDesc())
          .getContent());
      return response;
    }

    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  /**
   * 用户评价
   * 
   * @return
   */
  @RequestMapping(value = "/review", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse review(@RequestBody OrderRequest request,
      HttpServletRequest httpServletRequest) {

    BaseResponse response = new BaseResponse();

    Long userId = request.getUserId();
    String token = request.getToken();
    Long productId = request.getProductId();
    Integer reviewScore = request.getReviewScore();
    String reviewContent = request.getReviewContent();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    if (LogUtil.isDebugEnabled(OrderController.class)) {
      LogUtil.debug(OrderController.class, "review",
          "review. UserId: %s,productId: %s,score: %s,content: %s", userId, productId, reviewScore,
          reviewContent);
    }

    Product product = productService.find(productId);
    EndUser endUser = endUserService.find(userId);
    Review review = new Review();
    review.setContent(reviewContent);
    review.setScore(reviewScore);
    review.setIsShow(true);
    review.setIp(httpServletRequest.getRemoteAddr());
    review.setMember(endUser);
    review.setProduct(product);
    review.setTenantID(product.getTenantID());

    product.setScoreCount(product.getScoreCount() + 1);
    product.setTotalScore(product.getTotalScore() + reviewScore);
    product.setScore((float) (product.getTotalScore() / product.getScoreCount()));
    product.getReviews().add(review);
    productService.update(product);

    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  /**
   * 用户操作订单
   * 
   * @return
   */
  @RequestMapping(value = "/operation", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse operation(@RequestBody OrderRequest request) {

    BaseResponse response = new BaseResponse();

    Long userId = request.getUserId();
    String token = request.getToken();
    Long orderId = request.getOrderId();
    OrderLogType oprType = request.getOprType();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    if (LogUtil.isDebugEnabled(OrderController.class)) {
      LogUtil.debug(OrderController.class, "operation",
          "operation order. UserId: %s,orderId: %s,operation: %s", userId, orderId, oprType);
    }


    Order order = orderService.find(orderId);
    if (OrderLogType.cancel.equals(oprType)
        && !PaymentStatus.unpaid.equals(order.getPaymentStatus())) {
      response.setCode(CommonAttributes.FAIL_COMMON);
      response.setDesc(Message.error("csh.estore.order.opr.invalid").getContent());
      return response;

    } else if (OrderLogType.received.equals(oprType)
        && !ShippingStatus.shipped.equals(order.getShippingStatus())) {
      response.setCode(CommonAttributes.FAIL_COMMON);
      response.setDesc(Message.error("csh.estore.order.opr.invalid").getContent());
      return response;
    }
    orderService.operation(order, oprType);

    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }



  /**
   * 用户订单列表
   * 
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> list(@RequestBody OrderRequest request) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();

    Long userId = request.getUserId();
    String token = request.getToken();
    Integer status = request.getStatus();
    Integer pageSize = request.getPageSize();
    Integer pageNumber = request.getPageNumber();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    if (LogUtil.isDebugEnabled(OrderController.class)) {
      LogUtil.debug(OrderController.class, "list",
          "Search User Product Orders with Params: userId: %s, status: %s", userId, status);
    }

    List<Filter> filters = new ArrayList<Filter>();
    Filter userFilter = new Filter("endUser", Operator.eq, userId);
    filters.add(userFilter);

    if ("1".equals(status.toString())) {// 待付款（PaymentStatus为unpaid）
      Filter paymentFilter = new Filter("paymentStatus", Operator.eq, PaymentStatus.unpaid);
      filters.add(paymentFilter);
    } else if ("2".equals(status.toString())) {// 待发货（PaymentStatus为paid且OrderStatus为confirmed）
      Filter paymentFilter = new Filter("paymentStatus", Operator.eq, PaymentStatus.paid);
      Filter statusFilter = new Filter("orderStatus", Operator.eq, OrderStatus.confirmed);
      filters.add(paymentFilter);
      filters.add(statusFilter);
    } else if ("3".equals(status.toString())) {// 待收货（ShippingStatus为shipped）
      Filter shippedFilter = new Filter("shippingStatus", Operator.eq, ShippingStatus.shipped);
      filters.add(shippedFilter);
    } else if ("4".equals(status.toString())) {// 待评价（ShippingStatus为received）
      Filter shippedFilter = new Filter("shippingStatus", Operator.eq, ShippingStatus.received);
      filters.add(shippedFilter);
    }

    Pageable pageable = new Pageable();
    pageable.setPageNumber(pageNumber);
    pageable.setPageSize(pageSize);
    pageable.setFilters(filters);
    pageable.setOrderProperty("createDate");
    pageable.setOrderDirection(Direction.desc);
    Page<Order> orderList = orderService.findPage(pageable);
    checkOverDue(orderList.getContent());

    String[] propertys =
        {"id", "createDate", "sn", "consignee", "phone", "areaName", "address", "paymentStatus",
            "orderStatus", "shippingStatus", "freight"};

    String[] itemPropertys = {"id", "name", "price", "thumbnail", "quantity", "product.id"};

    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

    for (Order order : orderList.getContent()) {
      Map<String, Object> map = FieldFilterUtils.filterEntityMap(propertys, order);
      Integer productCount = 0;
      for (OrderItem item : order.getOrderItems()) {
        productCount += item.getQuantity();
      }
      map.put("amount", order.getAmount());
      map.put("productCount", productCount);
      map.put("orderItem",
          FieldFilterUtils.filterCollectionMap(itemPropertys, order.getOrderItems()));
      result.add(map);
    }
    response.setMsg(result);

    PageResponse page = new PageResponse();
    page.setPageNumber(request.getPageNumber());
    page.setPageSize(request.getPageSize());
    page.setTotal((int) orderList.getTotal());
    response.setPage(page);

    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 用户申请退货/完善退货单
   * 
   * @return
   */
  @RequestMapping(value = "/applyReturns", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse returns(@RequestBody OrderRequest request) {

    BaseResponse response = new BaseResponse();

    Long userId = request.getUserId();
    String token = request.getToken();
    Long[] orderItemIds = request.getOrderItemIds();
    Long orderId = request.getOrderId();
    Long returnsId = request.getReturnsId();
    String trackingNo = request.getTrackingNo();
    String deliveryCorp = request.getDeliveryCorp();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    String itemIdsStr = null;
    if (orderItemIds != null) {
      for (Long itemId : orderItemIds) {
        itemIdsStr += itemId + "";
      }
    }

    if (LogUtil.isDebugEnabled(OrderController.class)) {
      LogUtil
          .debug(
              OrderController.class,
              "returns",
              "order returns. UserId: %s,orderId: %s,orderItemIds: %s, returnsId: %s, trackingNo: %s, deliveryCorp: %s",
              userId, orderId, itemIdsStr, returnsId, trackingNo, deliveryCorp);
    }

    returnsService.createOrEdit(orderId, orderItemIds, returnsId, userId, trackingNo, deliveryCorp);
    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 用户查看退货单
   * 
   * @return
   */
  @RequestMapping(value = "/getReturnsBill", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> getReturnsBill(
      @RequestBody OrderRequest request) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();

    Long userId = request.getUserId();
    String token = request.getToken();
    Integer pageSize = request.getPageSize();
    Integer pageNumber = request.getPageNumber();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    List<Filter> filters = new ArrayList<Filter>();
    Filter userFilter = new Filter("operator", Operator.eq, endUser.getUserName());
    filters.add(userFilter);

    Pageable pageable = new Pageable();
    pageable.setPageNumber(pageNumber);
    pageable.setPageSize(pageSize);
    pageable.setOrderProperty("createDate");
    pageable.setOrderDirection(Direction.desc);
    Page<Returns> returnsList = returnsService.findPage(pageable);
    String[] propertys =
        {"id", "sn", "deliveryCorp", "trackingNo", "returnsStatus", "returnAmount"};

    String[] itemPropertys = {"id", "name", "price", "thumbnail", "quantity"};
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    for (Returns returns : returnsList.getContent()) {
      Map<String, Object> map = FieldFilterUtils.filterEntityMap(propertys, returns);
      List<ReturnsItem> list = returns.getReturnsItems();
      List<Map<String, Object>> items = FieldFilterUtils.filterCollectionMap(itemPropertys, list);
      map.put("returnsItem", items);
      result.add(map);
    }
    response.setMsg(result);

    PageResponse page = new PageResponse();
    page.setPageNumber(request.getPageNumber());
    page.setPageSize(request.getPageSize());
    page.setTotal((int) returnsList.getTotal());
    response.setPage(page);

    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  private void checkOverDue(List<Order> recordList) {
    for (Order order : recordList) {
      Date currentDate = new Date();
      if (order.getPaymentStatus() == PaymentStatus.unpaid && order.isExpired()) {
        order.setOrderStatus(OrderStatus.failure);
        OrderLog orderLog = new OrderLog();
        orderLog.setTenantID(order.getTenantID());
        orderLog.setType(OrderLogType.overdue);
        orderLog.setOrder(order);
        order.getOrderLogs().add(orderLog);
        orderService.update(order);
      }
      if (order.getShippingStatus() == ShippingStatus.shipped) {
        OrderLog shippedLog = orderLogService.getLogByOrderOpr(order, OrderLogType.shipping);
        if ((currentDate.getTime() - shippedLog.getCreateDate().getTime()) / (1000 * 60 * 60 * 24) >= setting
            .getOrderReceiveTimeOut()) {
          order.setShippingStatus(ShippingStatus.received);
          OrderLog orderLog = new OrderLog();
          orderLog.setTenantID(order.getTenantID());
          orderLog.setType(OrderLogType.received);
          orderLog.setOrder(order);
          Long receivedTime =
              shippedLog.getCreateDate().getTime() + setting.getOrderReceiveTimeOut() * 1000 * 60
                  * 60 * 24;
          orderLog.setCreateDate(new Date(receivedTime));
          order.getOrderLogs().add(orderLog);
          orderService.update(order);
        }
      }

      if (order.getShippingStatus() == ShippingStatus.received
          && OrderStatus.completed != order.getOrderStatus()) {
        OrderLog receivedLog = orderLogService.getLogByOrderOpr(order, OrderLogType.received);
        if ((currentDate.getTime() - receivedLog.getCreateDate().getTime()) / (1000 * 60 * 60 * 24) >= setting
            .getOrderCompleteTimeOut()) {
          order.setOrderStatus(OrderStatus.completed);
          OrderLog orderLog = new OrderLog();
          orderLog.setTenantID(order.getTenantID());
          orderLog.setType(OrderLogType.complete);
          orderLog.setOrder(order);
          Long completeTime =
              receivedLog.getCreateDate().getTime() + setting.getOrderCompleteTimeOut() * 1000 * 60
                  * 60 * 24;
          orderLog.setCreateDate(new Date(completeTime));
          order.getOrderLogs().add(orderLog);
          orderService.update(order);
        }
      }

    }
  }



}
