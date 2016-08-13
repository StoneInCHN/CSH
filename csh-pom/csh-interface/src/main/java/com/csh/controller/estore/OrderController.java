package com.csh.controller.estore;

import java.util.ArrayList;
import java.util.Date;
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
import com.csh.entity.EndUser;
import com.csh.entity.commonenum.CommonEnum.OrderLogType;
import com.csh.entity.commonenum.CommonEnum.OrderStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentStatus;
import com.csh.entity.commonenum.CommonEnum.ShippingStatus;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.OrderItem;
import com.csh.entity.estore.OrderLog;
import com.csh.entity.estore.Product;
import com.csh.entity.estore.Review;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.ordering.Ordering.Direction;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.base.BaseResponse;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.request.OrderRequest;
import com.csh.service.EndUserService;
import com.csh.service.OrderLogService;
import com.csh.service.OrderService;
import com.csh.service.ProductService;
import com.csh.utils.FieldFilterUtils;
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
    for (Long cartItemId : cartItemIds) {
      cartItemIdsStr += cartItemId + " ";
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
    if (OrderLogType.cancel.equals(oprType)) {
      order.setOrderStatus(OrderStatus.cancelled);
    } else if (OrderLogType.received.equals(oprType)) {
      order.setShippingStatus(ShippingStatus.received);
    }
    OrderLog orderLog = new OrderLog();
    orderLog.setTenantID(order.getTenantID());
    orderLog.setType(oprType);
    orderLog.setOrder(order);
    order.getOrderLogs().add(orderLog);
    orderService.update(order);

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

    if ("1".equals(status)) {// 待付款（PaymentStatus为unpaid）
      Filter paymentFilter = new Filter("paymentStatus", Operator.eq, PaymentStatus.unpaid);
      filters.add(paymentFilter);
    } else if ("2".equals(status)) {// 待发货（PaymentStatus为paid且OrderStatus为confirmed）
      Filter paymentFilter = new Filter("paymentStatus", Operator.eq, PaymentStatus.paid);
      Filter statusFilter = new Filter("orderStatus", Operator.eq, OrderStatus.confirmed);
      filters.add(paymentFilter);
      filters.add(statusFilter);
    } else if ("3".equals(status)) {// 待收货（ShippingStatus为shipped）
      Filter shippedFilter = new Filter("shippingStatus", Operator.eq, ShippingStatus.shipped);
      filters.add(shippedFilter);
    } else if ("4".equals(status)) {// 待评价（ShippingStatus为received）
      Filter shippedFilter = new Filter("shippingStatus", Operator.eq, ShippingStatus.received);
      filters.add(shippedFilter);
    }

    Pageable pageable = new Pageable();
    pageable.setPageNumber(pageNumber);
    pageable.setPageSize(pageSize);
    pageable.setFilters(filters);
    pageable.setOrderProperty("createDate");
    pageable.setOrderDirection(Direction.desc);
    Page<Order> page = orderService.findPage(pageable);
    checkOverDue(page.getContent());

    String[] propertys =
        {"id", "createDate", "sn", "consignee", "phone", "areaName", "address", "paymentStatus",
            "orderStatus", "shippingStatus", "freight"};

    String[] itemPropertys = {"id", "name", "price", "thumbnail", "quantity"};

    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

    for (Order order : page.getContent()) {
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

    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 用户申请退款
   * 
   * @return
   */
  @RequestMapping(value = "/returns", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse returns(@RequestBody OrderRequest request) {

    BaseResponse response = new BaseResponse();

    Long userId = request.getUserId();
    String token = request.getToken();
    Long[] orderItemIds = request.getOrderItemIds();
    Long orderId = request.getOrderId();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    String itemIdsStr = null;
    for (Long itemId : orderItemIds) {
      itemIdsStr += itemId + "";
    }
    if (LogUtil.isDebugEnabled(OrderController.class)) {
      LogUtil.debug(OrderController.class, "returns",
          "order returns. UserId: %s,orderId: %s,orderItemIds: %s", userId, orderId, itemIdsStr);
    }



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
