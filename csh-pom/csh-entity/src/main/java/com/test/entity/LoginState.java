package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the csh_login_state database table.
 * 
 */
@Entity
@Table(name="csh_login_state")
@NamedQuery(name="LoginState.findAll", query="SELECT l FROM LoginState l")
public class LoginState implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_LOGIN_STATE_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_LOGIN_STATE_ID_GENERATOR")
	private String id;

	private String imei;

	@Column(name="login_time")
	private Timestamp loginTime;

	private String status;

	private int type;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="lid")
	private Login cshLogin;

	public LoginState() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Timestamp getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Login getCshLogin() {
		return this.cshLogin;
	}

	public void setCshLogin(Login cshLogin) {
		this.cshLogin = cshLogin;
	}

}