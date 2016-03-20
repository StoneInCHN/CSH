package com.csh.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;

/**
 * 我的钱包
 * @author Andrea
 *
 */
@Entity
@Table(name = "csh_wallet")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_wallet_sequence")
public class Wallet extends BaseEntity {

  private static final long serialVersionUID = 7282926448628904157L;


  /**
   * 用户
   */
  private EndUser endUser;

  /** 账户余额 */
  private BigDecimal balanceAmount = new BigDecimal(0);
  
  /**
   * 红包金额
   */
  private BigDecimal giftAmount= new BigDecimal(0);
  /**
   * 积分
   */
  private BigDecimal score = new BigDecimal(0);
  
  /**
   * 钱包流水记录
   */
  private Set<WalletRecord> walletRecords = new HashSet<WalletRecord>();
  
    @OneToMany(mappedBy="wallet",cascade=CascadeType.ALL)
    public Set<WalletRecord> getWalletRecords() {
    	return walletRecords;
    }

	public void setWalletRecords(Set<WalletRecord> walletRecords) {
		this.walletRecords = walletRecords;
	}

	@OneToOne
	public EndUser getEndUser() {
		return endUser;
	}
	
	public void setEndUser(EndUser endUser) {
		this.endUser = endUser;
	}
	
	@Column(scale = 1, precision = 9, nullable = false)
	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}
	
	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	
	@Column(scale = 1, precision = 9, nullable = false)
	public BigDecimal getGiftAmount() {
		return giftAmount;
	}
	
	public void setGiftAmount(BigDecimal giftAmount) {
		this.giftAmount = giftAmount;
	}
	
	@Column(scale = 1, precision = 9, nullable = false)
	public BigDecimal getScore() {
		return score;
	}
	
	public void setScore(BigDecimal score) {
		this.score = score;
	}
  
  
}
