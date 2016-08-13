package com.csh.json.request;

import com.csh.entity.commonenum.CommonEnum.OrderLogType;
import com.csh.json.base.BaseRequest;

public class OrderRequest extends BaseRequest {


  /**
   * 购物车项目IDs
   */
  private Long[] itemIds;

  /**
   * 订单明细项IDs
   */
  private Long[] orderItemIds;

  /**
   * 收货地址ID
   */
  private Long receiverId;
  /**
   * 商品ID
   */
  private Long productId;

  /**
   * 购买数量
   */
  private Integer quantity;

  /**
   * 订单查询状态（0：全部，1：待付款（PaymentStatus为unpaid），2：待发货（PaymentStatus为paid且OrderStatus为confirmed）， 3：待收货
   * （ShippingStatus为shipped），4：待评价（ShippingStatus为received））
   */
  private Integer status;

  /** 是否开据发票 */
  private Boolean isInvoice;

  /** 发票抬头 */
  private String invoiceTitle;

  /**
   * 订单操作类型（取消订单,确认收货）
   */
  private OrderLogType oprType;

  /**
   * 订单ID
   */
  private Long orderId;

  /**
   * 评分
   */
  private Integer reviewScore;

  /**
   * 评论内容
   */
  private String reviewContent;

  /**
   * 退款单
   */
  private Long returnsId;

  /**
   * 退款运单号
   */
  private String trackingNo;

  /** 物流公司 */
  private String deliveryCorp;

  public String getTrackingNo() {
    return trackingNo;
  }

  public void setTrackingNo(String trackingNo) {
    this.trackingNo = trackingNo;
  }

  public String getDeliveryCorp() {
    return deliveryCorp;
  }

  public void setDeliveryCorp(String deliveryCorp) {
    this.deliveryCorp = deliveryCorp;
  }

  public Long getReturnsId() {
    return returnsId;
  }

  public void setReturnsId(Long returnsId) {
    this.returnsId = returnsId;
  }

  public Long[] getOrderItemIds() {
    return orderItemIds;
  }

  public void setOrderItemIds(Long[] orderItemIds) {
    this.orderItemIds = orderItemIds;
  }

  public Integer getReviewScore() {
    return reviewScore;
  }

  public void setReviewScore(Integer reviewScore) {
    this.reviewScore = reviewScore;
  }

  public String getReviewContent() {
    return reviewContent;
  }

  public void setReviewContent(String reviewContent) {
    this.reviewContent = reviewContent;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public OrderLogType getOprType() {
    return oprType;
  }

  public void setOprType(OrderLogType oprType) {
    this.oprType = oprType;
  }

  public Boolean getIsInvoice() {
    return isInvoice;
  }

  public void setIsInvoice(Boolean isInvoice) {
    this.isInvoice = isInvoice;
  }

  public String getInvoiceTitle() {
    return invoiceTitle;
  }

  public void setInvoiceTitle(String invoiceTitle) {
    this.invoiceTitle = invoiceTitle;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Long getReceiverId() {
    return receiverId;
  }

  public void setReceiverId(Long receiverId) {
    this.receiverId = receiverId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long[] getItemIds() {
    return itemIds;
  }

  public void setItemIds(Long[] itemIds) {
    this.itemIds = itemIds;
  }

}
