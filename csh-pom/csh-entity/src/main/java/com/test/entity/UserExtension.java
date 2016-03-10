package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the csh_user_extensions database table.
 * 
 */
@Entity
@Table(name="csh_user_extensions")
@NamedQuery(name="UserExtension.findAll", query="SELECT u FROM UserExtension u")
public class UserExtension implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_USER_EXTENSIONS_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_USER_EXTENSIONS_ID_GENERATOR")
	private String id;

	@Column(name="account_name")
	private String accountName;

	@Column(name="account_number")
	private String accountNumber;

	@Column(name="add_time")
	private Timestamp addTime;

	private String bank;

	@Column(name="merchant_umber")
	private String merchantUmber;

	private String statue;

	private String telephone;

	private BigInteger uid;

	@Column(name="user_name")
	private String userName;

	public UserExtension() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getMerchantUmber() {
		return this.merchantUmber;
	}

	public void setMerchantUmber(String merchantUmber) {
		this.merchantUmber = merchantUmber;
	}

	public String getStatue() {
		return this.statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public BigInteger getUid() {
		return this.uid;
	}

	public void setUid(BigInteger uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}