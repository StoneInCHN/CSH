package com.csh.json.request;

import java.math.BigDecimal;

import com.csh.entity.commonenum.CommonEnum.ChargeStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.json.base.BaseRequest;

public class CarServiceRequest extends BaseRequest {

  /**
   * 服务ID
   */
  private Long serviceId;

  /**
   * 服务记录ID
   */
  private Long recordId;

  /**
   * 支付方式
   */
  private PaymentType paymentType;

  /**
   * 付款状态
   */
  private ChargeStatus chargeStatus;

  /**
   * 服务单价
   */
  private BigDecimal price;

  /**
   * 预约时间
   */
  private String subscribeDate;


  public String getSubscribeDate() {
    return subscribeDate;
  }

  public void setSubscribeDate(String subscribeDate) {
    this.subscribeDate = subscribeDate;
  }

  public Long getRecordId() {
    return recordId;
  }

  public void setRecordId(Long recordId) {
    this.recordId = recordId;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Long getServiceId() {
    return serviceId;
  }

  public void setServiceId(Long serviceId) {
    this.serviceId = serviceId;
  }

  public PaymentType getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
  }

  public ChargeStatus getChargeStatus() {
    return chargeStatus;
  }

  public void setChargeStatus(ChargeStatus chargeStatus) {
    this.chargeStatus = chargeStatus;
  }



}
