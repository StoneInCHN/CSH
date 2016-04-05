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
