package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the csh_wallet_mobile database table.
 * 
 */
@Entity
@Table(name="csh_wallet_mobile")
@NamedQuery(name="WalletMobile.findAll", query="SELECT w FROM WalletMobile w")
public class WalletMobile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_WALLET_MOBILE_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_WALLET_MOBILE_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private String admin;

	@Column(name="allot_time")
	private Timestamp allotTime;

	private String code;

	private float money;

	private String pass;

	private BigInteger uid;

	public WalletMobile() {
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

	public String getAdmin() {
		return this.admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public Timestamp getAllotTime() {
		return this.allotTime;
	}

	public void setAllotTime(Timestamp allotTime) {
		this.allotTime = allotTime;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public float getMoney() {
		return this.money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public BigInteger getUid() {
		return this.uid;
	}

	public void setUid(BigInteger uid) {
		this.uid = uid;
	}

}