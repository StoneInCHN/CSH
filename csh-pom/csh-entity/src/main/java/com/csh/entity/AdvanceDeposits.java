package com.csh.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.AdvanceUsageType;

/**
 * 预存专用款
 * 
 * @author Andrea
 *
 */
@Entity
@Table(name = "csh_advance_deposits")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_advance_deposits_sequence")
public class AdvanceDeposits extends BaseEntity {

  private static final long serialVersionUID = 7282926448628904157L;


  /**
   * 用户
   */
  private EndUser endUser;

  /** 账户余额 */
  private BigDecimal balanceAmount = new BigDecimal(0);

  /**
   * 预存转款用途
   */
  private AdvanceUsageType usageType;


  public AdvanceUsageType getUsageType() {
    return usageType;
  }

  public void setUsageType(AdvanceUsageType usageType) {
    this.usageType = usageType;
  }

  @OneToOne
  public EndUser getEndUser() {
    return endUser;
  }

  public void setEndUser(EndUser endUser) {
    this.endUser = endUser;
  }

  @Column(scale = 2, precision = 10, nullable = false)
  public BigDecimal getBalanceAmount() {
    return balanceAmount;
  }

  public void setBalanceAmount(BigDecimal balanceAmount) {
    this.balanceAmount = balanceAmount;
  }

}
