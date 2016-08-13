package com.csh.service;

import com.csh.entity.EndUser;
import com.csh.entity.estore.Order;
import com.csh.framework.service.BaseService;
import com.csh.json.base.BaseResponse;

public interface OrderService extends BaseService<Order, Long> {

  /**
   * 直接创建订单
   * 
   * @param endUser
   * @param productId
   * @param quantity
   * @param receiverId
   * @param isInvoice
   * @param invoiceTitle
   */
  public BaseResponse createOrderDirect(EndUser endUser, Long productId, Integer quantity,
      Long receiverId, Boolean isInvoice, String invoiceTitle);

  /**
   * 通过购物车创建订单，创建成功后删除购物车中对应商品
   * 
   * @param endUser
   * @param cartItems
   * @param receiverId
   * @param isInvoice
   * @param invoiceTitle
   */
  public BaseResponse createOrderByCart(EndUser endUser, Long[] cartItems, Long receiverId,
      Boolean isInvoice, String invoiceTitle);
}
