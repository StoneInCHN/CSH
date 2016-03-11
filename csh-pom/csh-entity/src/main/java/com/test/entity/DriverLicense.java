package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the csh_driver_license database table.
 * 
 */
@Entity
@Table(name="csh_driver_license")
@NamedQuery(name="DriverLicense.findAll", query="SELECT d FROM DriverLicense d")
public class DriverLicense implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_DRIVER_LICENSE_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_DRIVER_LICENSE_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private String address;

	private Timestamp birthday;

	@Column(name="class")
	private String class_;

	@Column(name="end_time")
	private Timestamp endTime;

	@Column(name="first_used")
	private Timestamp firstUsed;

	private String name;

	@Column(name="start_time")
	private Timestamp startTime;

	//bi-directional many-to-one association to UserInfo
	@ManyToOne
	@JoinColumn(name="uid")
	private UserInfo cshUserInfo;

	public DriverLicense() {
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

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Timestamp getFirstUsed() {
		return this.firstUsed;
	}

	public void setFirstUsed(Timestamp firstUsed) {
		this.firstUsed = firstUsed;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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