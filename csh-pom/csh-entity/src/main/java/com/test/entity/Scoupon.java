package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the cshm_scoupon database table.
 * 
 */
@Entity
@Table(name="cshm_scoupon")
@NamedQuery(name="Scoupon.findAll", query="SELECT s FROM Scoupon s")
public class Scoupon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSHM_SCOUPON_COUPONID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSHM_SCOUPON_COUPONID_GENERATOR")
	@Column(name="coupon_id")
	private int couponId;

	@Column(name="coupon_name")
	private String couponName;

	@Column(name="coupon_value")
	private BigDecimal couponValue;

	@Column(name="end_time")
	private int endTime;

	@Column(name="if_issue")
	private byte ifIssue;

	@Column(name="min_amount")
	private BigDecimal minAmount;

	@Column(name="start_time")
	private int startTime;

	@Column(name="store_id")
	private int storeId;

	@Column(name="use_times")
	private int useTimes;

	public Scoupon() {
	}

	public int getCouponId() {
		return this.couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}

	public String getCouponName() {
		return this.couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public BigDecimal getCouponValue() {
		return this.couponValue;
	}

	public void setCouponValue(BigDecimal couponValue) {
		this.couponValue = couponValue;
	}

	public int getEndTime() {
		return this.endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public byte getIfIssue() {
		return this.ifIssue;
	}

	public void setIfIssue(byte ifIssue) {
		this.ifIssue = ifIssue;
	}

	public BigDecimal getMinAmount() {
		return this.minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	public int getStartTime() {
		return this.startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getStoreId() {
		return this.storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getUseTimes() {
		return this.useTimes;
	}

	public void setUseTimes(int useTimes) {
		this.useTimes = useTimes;
	}

}