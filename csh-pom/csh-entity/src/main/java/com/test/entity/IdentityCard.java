package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the csh_identity_card database table.
 * 
 */
@Entity
@Table(name="csh_identity_card")
@NamedQuery(name="IdentityCard.findAll", query="SELECT i FROM IdentityCard i")
public class IdentityCard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_IDENTITY_CARD_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_IDENTITY_CARD_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private String address;

	private Timestamp birthday;

	private String code;

	@Column(name="end_time")
	private Timestamp endTime;

	private String name;

	private String national;

	private String office;

	private String sex;

	@Column(name="start_time")
	private Timestamp startTime;

	//bi-directional many-to-one association to UserInfo
	@ManyToOne
	@JoinColumn(name="uid")
	private UserInfo cshUserInfo;

	public IdentityCard() {
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

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNational() {
		return this.national;
	}

	public void setNational(String national) {
		this.national = national;
	}

	public String getOffice() {
		return this.office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public UserInfo getCshUserInfo() {
		return this.cshUserInfo;
	}

	public void setCshUserInfo(UserInfo cshUserInfo) {
		this.cshUserInfo = cshUserInfo;
	}

}