package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the csh_call_bill database table.
 * 
 */
@Entity
@Table(name="csh_call_bill")
@NamedQuery(name="CallBill.findAll", query="SELECT c FROM CallBill c")
public class CallBill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_CALL_BILL_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_CALL_BILL_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private String appid;

	private String called;

	private int calledtype;

	private String caller;

	private int callertype;

	private String callid;

	private int calltype;

	private int length;

	private int reason;

	private String recordurl;

	private String starttime;

	private String status;

	private String stoptime;

	private int subreason;

	private String userData;

	//bi-directional many-to-one association to UserInfo
	@ManyToOne
	@JoinColumn(name="uid")
	private UserInfo cshUserInfo;

	public CallBill() {
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

	public String getAppid() {
		return this.appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getCalled() {
		return this.called;
	}

	public void setCalled(String called) {
		this.called = called;
	}

	public int getCalledtype() {
		return this.calledtype;
	}

	public void setCalledtype(int calledtype) {
		this.calledtype = calledtype;
	}

	public String getCaller() {
		return this.caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public int getCallertype() {
		return this.callertype;
	}

	public void setCallertype(int callertype) {
		this.callertype = callertype;
	}

	public String getCallid() {
		return this.callid;
	}

	public void setCallid(String callid) {
		this.callid = callid;
	}

	public int getCalltype() {
		return this.calltype;
	}

	public void setCalltype(int calltype) {
		this.calltype = calltype;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getReason() {
		return this.reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	public String getRecordurl() {
		return this.recordurl;
	}

	public void setRecordurl(String recordurl) {
		this.recordurl = recordurl;
	}

	public String getStarttime() {
		return this.starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStoptime() {
		return this.stoptime;
	}

	public void setStoptime(String stoptime) {
		this.stoptime = stoptime;
	}

	public int getSubreason() {
		return this.subreason;
	}

	public void setSubreason(int subreason) {
		this.subreason = subreason;
	}

	public String getUserData() {
		return this.userData;
	}

	public void setUserData(String userData) {
		this.userData = userData;
	}

	public UserInfo getCshUserInfo() {
		return this.cshUserInfo;
	}

	public void setCshUserInfo(UserInfo cshUserInfo) {
		this.cshUserInfo = cshUserInfo;
	}

}