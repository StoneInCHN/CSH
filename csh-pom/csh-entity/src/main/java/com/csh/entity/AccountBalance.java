package com.csh.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;

@Entity
@Table(name = "csh_account_balance")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_account_balance_sequence")
public class AccountBalance extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 5619363454338668510L;

  /**
   * 终端用户ID
   */
  private EndUser endUser;
  /**
   * 租户ID
   */
  private Long tenantID;
  /**
   * 余额
   */
  private BigDecimal balance;

  @ManyToOne 
  public EndUser getEndUser() {
    return endUser;
  }

  public void setEndUser(EndUser endUser) {
    this.endUser = endUser;
  }

  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

}
