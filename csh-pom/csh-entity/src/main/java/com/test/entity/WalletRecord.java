package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the csh_wallet_record database table.
 * 
 */
@Entity
@Table(name="csh_wallet_record")
@NamedQuery(name="WalletRecord.findAll", query="SELECT w FROM WalletRecord w")
public class WalletRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_WALLET_RECORD_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_WALLET_RECORD_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private String describe;

	private float money;

	@Column(name="payment_type")
	private int paymentType;

	private float red;

	private float score;

	private int type;

	private BigInteger wid;

	public WalletRecord() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public float getMoney() {
		return this.money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public int getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	public float getRed() {
		return this.red;
	}

	public void setRed(float red) {
		this.red = red;
	}

	public float getScore() {
		return this.score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public BigInteger getWid() {
		return this.wid;
	}

	public void setWid(BigInteger wid) {
		this.wid = wid;
	}

}