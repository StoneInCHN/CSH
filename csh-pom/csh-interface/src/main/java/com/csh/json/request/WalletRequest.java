package com.csh.json.request;

import java.math.BigDecimal;

import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.entity.commonenum.CommonEnum.WalletType;
import com.csh.json.base.BaseRequest;

public class WalletRequest extends BaseRequest {

  /**
   * 钱包类型
   */
  private WalletType walletType;

  private Long walletId;

  /**
   * 金额
   */
  private BigDecimal amount;

  /**
   * 说明
   */
  private String remark;

  /**
   * 支付方式
   */
  private PaymentType paymentType;

  /**
   * CI:普通充值, PD:购买设备
   */
  private String chargeType;

  /**
   * 充值记录号
   */
  private String recordNo;


  public String getRecordNo() {
    return recordNo;
  }

  public void setRecordNo(String recordNo) {
    this.recordNo = recordNo;
  }

  public String getChargeType() {
    return chargeType;
  }

  public void setChargeType(String chargeType) {
    this.chargeType = chargeType;
  }

  public PaymentType getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Long getWalletId() {
    return walletId;
  }

  public void setWalletId(Long walletId) {
    this.walletId = walletId;
  }

  public WalletType getWalletType() {
    return walletType;
  }

  public void setWalletType(WalletType walletType) {
    this.walletType = walletType;
  }

}
