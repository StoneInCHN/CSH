package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the xa_user database table.
 * 
 */
@Entity
@Table(name="xa_user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="XA_USER_UID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="XA_USER_UID_GENERATOR")
	private String uid;

	@Column(name="app_key")
	private String appKey;

	private String imei;

	@Column(name="is_bound")
	private int isBound;

	@Column(name="is_login")
	private int isLogin;

	@Column(name="is_push")
	private int isPush;

	@Column(name="nick_name")
	private String nickName;

	private String note;

	@Column(name="p_status")
	private String pStatus;

	private String photo;

	private String psw;

	private int status;

	private String tel;

	private String type;

	@Column(name="xa_no")
	private String xaNo;

	public User() {
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAppKey() {
		return this.appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public int getIsBound() {
		return this.isBound;
	}

	public void setIsBound(int isBound) {
		this.isBound = isBound;
	}

	public int getIsLogin() {
		return this.isLogin;
	}

	public void setIsLogin(int isLogin) {
		this.isLogin = isLogin;
	}

	public int getIsPush() {
		return this.isPush;
	}

	public void setIsPush(int isPush) {
		this.isPush = isPush;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPStatus() {
		return this.pStatus;
	}

	public void setPStatus(String pStatus) {
		this.pStatus = pStatus;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPsw() {
		return this.psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getXaNo() {
		return this.xaNo;
	}

	public void setXaNo(String xaNo) {
		this.xaNo = xaNo;
	}

}