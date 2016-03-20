package com.csh.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.BalanceType;

/**
 * 钱包流水记录
 * 
 * @author Andrea
 *
 */
@Entity
@Table(name = "csh_wallet_record")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_wallet_record_sequence")
public class WalletRecord extends BaseEntity {

  private static final long serialVersionUID = 7282926448628904157L;

  
  private Wallet wallet;
  
  /** 金钱 */
  private BigDecimal money = new BigDecimal(0);
  
  /**
   * 红包
   */
  private BigDecimal redPacket= new BigDecimal(0);
  /**
   * 积分
   */
  private BigDecimal score = new BigDecimal(0);
  
  /**
   * 收支类型
   */
  private BalanceType type;
  
  /**
   * 说明备注
   */
  private String remark;
  

    @ManyToOne
	public Wallet getWallet() {
		return wallet;
	}
	
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	@Column(scale = 1, precision = 9, nullable = false)
	public BigDecimal getMoney() {
		return money;
	}
	
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	@Column(scale = 1, precision = 9, nullable = false)
	public BigDecimal getRedPacket() {
		return redPacket;
	}
	
	public void setRedPacket(BigDecimal redPacket) {
		this.redPacket = redPacket;
	}
	
	@Column(scale = 1, precision = 9, nullable = false)
	public BigDecimal getScore() {
		return score;
	}
	
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	
	public BalanceType getType() {
		return type;
	}
	
	public void setType(BalanceType type) {
		this.type = type;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
  
  
  
}
