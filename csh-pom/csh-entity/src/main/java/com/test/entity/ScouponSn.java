package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cshm_scoupon_sn database table.
 * 
 */
@Entity
@Table(name="cshm_scoupon_sn")
@NamedQuery(name="ScouponSn.findAll", query="SELECT s FROM ScouponSn s")
public class ScouponSn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSHM_SCOUPON_SN_COUPONSN_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSHM_SCOUPON_SN_COUPONSN_GENERATOR")
	@Column(name="coupon_sn")
	private String couponSn;

	@Column(name="coupon_id")
	private int couponId;

	@Column(name="remain_times")
	private int remainTimes;

	public ScouponSn() {
	}

	public String getCouponSn() {
		return this.couponSn;
	}

	public void setCouponSn(String couponSn) {
		this.couponSn = couponSn;
	}

	public int getCouponId() {
		return this.couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}

	public int getRemainTimes() {
		return this.remainTimes;
	}

	public void setRemainTimes(int remainTimes) {
		this.remainTimes = remainTimes;
	}

}