package com.csh.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.AdvanceUsageType;
import com.csh.entity.commonenum.CommonEnum.PaymentType;

/**
 * 专用款项消费记录
 * 
 * @author Andrea
 *
 */
@Entity
@Table(name = "csh_advance_deposits", indexes = {@Index(name = "advanceDeposits_tenantid",
    columnList = "tenantID")})
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_advance_deposits_sequence")
public class AdvanceDeposits extends BaseEntity {

  private static final long serialVersionUID = 7282926448628904157L;


  /**
   * 用户
   */
  private EndUser endUser;

  /** 消费金额 */
  private BigDecimal amount = new BigDecimal(0);

  /**
   * 专用款用途
   */
  private AdvanceUsageType usageType;

  /**
   * 交易记录号
   */
  private String recordNo;

  /**
   * 设备号
   */
  private String deviceNo;

  /**
   * 支付方式
   */
  private PaymentType paymentType;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 设备是否已经绑定
   */
  private Boolean isBind;


  public Boolean getIsBind() {
    return isBind;
  }

  public void setIsBind(Boolean isBind) {
    this.isBind = isBind;
  }

  public PaymentType getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
  }

  @Column(scale = 2, precision = 10, nullable = false)
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Column(length = 40)
  public String getRecordNo() {
    return recordNo;
  }

  public void setRecordNo(String recordNo) {
    this.recordNo = recordNo;
  }

  @Column(length = 20)
  public String getDeviceNo() {
    return deviceNo;
  }

  public void setDeviceNo(String deviceNo) {
    this.deviceNo = deviceNo;
  }

  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  public AdvanceUsageType getUsageType() {
    return usageType;
  }

  public void setUsageType(AdvanceUsageType usageType) {
    this.usageType = usageType;
  }

  @ManyToOne
  public EndUser getEndUser() {
    return endUser;
  }

  public void setEndUser(EndUser endUser) {
    this.endUser = endUser;
  }


}
