package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;
import java.util.Set;


/**
 * The persistent class for the csh_login database table.
 * 
 */
@Entity
@Table(name="csh_login")
@NamedQuery(name="Login.findAll", query="SELECT l FROM Login l")
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_LOGIN_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_LOGIN_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private String loginname;

	private String password;

	private BigInteger role;

	private int status;

	//bi-directional many-to-one association to LoginState
	@OneToMany(mappedBy="cshLogin")
	private Set<LoginState> cshLoginStates;

	public Login() {
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

	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigInteger getRole() {
		return this.role;
	}

	public void setRole(BigInteger role) {
		this.role = role;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<LoginState> getCshLoginStates() {
		return this.cshLoginStates;
	}

	public void setCshLoginStates(Set<LoginState> cshLoginStates) {
		this.cshLoginStates = cshLoginStates;
	}

	public LoginState addCshLoginState(LoginState cshLoginState) {
		getCshLoginStates().add(cshLoginState);
		cshLoginState.setCshLogin(this);

		return cshLoginState;
	}

	public LoginState removeCshLoginState(LoginState cshLoginState) {
		getCshLoginStates().remove(cshLoginState);
		cshLoginState.setCshLogin(null);

		return cshLoginState;
	}

}