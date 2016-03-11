package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the csh_login_history database table.
 * 
 */
@Entity
@Table(name="csh_login_history")
@NamedQuery(name="LoginHistory.findAll", query="SELECT l FROM LoginHistory l")
public class LoginHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_LOGIN_HISTORY_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_LOGIN_HISTORY_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private String app;

	private String imei;

	private BigInteger lid;

	private String phone;

	private String system;

	public LoginHistory() {
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

	public String getApp() {
		return this.app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public BigInteger getLid() {
		return this.lid;
	}

	public void setLid(BigInteger lid) {
		this.lid = lid;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSystem() {
		return this.system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

}