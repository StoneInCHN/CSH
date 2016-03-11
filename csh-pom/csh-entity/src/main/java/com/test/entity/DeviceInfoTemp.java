package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the csh_device_info_temp database table.
 * 
 */
@Entity
@Table(name="csh_device_info_temp")
@NamedQuery(name="DeviceInfoTemp.findAll", query="SELECT d FROM DeviceInfoTemp d")
public class DeviceInfoTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_DEVICE_INFO_TEMP_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_DEVICE_INFO_TEMP_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private String admin;

	@Column(name="back_time")
	private Timestamp backTime;

	@Column(name="bind_time")
	private Timestamp bindTime;

	@Column(name="device_no")
	private String deviceNo;

	@Column(name="device_status")
	private int deviceStatus;

	@Column(name="import_id")
	private String importId;

	@Column(name="import_reason")
	private String importReason;

	@Column(name="import_result")
	private int importResult;

	@Column(name="issued_agent")
	private BigInteger issuedAgent;

	@Column(name="issued_time")
	private Timestamp issuedTime;

	private String note;

	@Column(name="out_agent")
	private BigInteger outAgent;

	@Column(name="out_time")
	private Timestamp outTime;

	@Column(name="sim_no")
	private String simNo;

	private BigInteger type;

	private BigInteger vehicle;

	public DeviceInfoTemp() {
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

	public Timestamp getBackTime() {
		return this.backTime;
	}

	public void setBackTime(Timestamp backTime) {
		this.backTime = backTime;
	}

	public Timestamp getBindTime() {
		return this.bindTime;
	}

	public void setBindTime(Timestamp bindTime) {
		this.bindTime = bindTime;
	}

	public String getDeviceNo() {
		return this.deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public int getDeviceStatus() {
		return this.deviceStatus;
	}

	public void setDeviceStatus(int deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getImportId() {
		return this.importId;
	}

	public void setImportId(String importId) {
		this.importId = importId;
	}

	public String getImportReason() {
		return this.importReason;
	}

	public void setImportReason(String importReason) {
		this.importReason = importReason;
	}

	public int getImportResult() {
		return this.importResult;
	}

	public void setImportResult(int importResult) {
		this.importResult = importResult;
	}

	public BigInteger getIssuedAgent() {
		return this.issuedAgent;
	}

	public void setIssuedAgent(BigInteger issuedAgent) {
		this.issuedAgent = issuedAgent;
	}

	public Timestamp getIssuedTime() {
		return this.issuedTime;
	}

	public void setIssuedTime(Timestamp issuedTime) {
		this.issuedTime = issuedTime;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigInteger getOutAgent() {
		return this.outAgent;
	}

	public void setOutAgent(BigInteger outAgent) {
		this.outAgent = outAgent;
	}

	public Timestamp getOutTime() {
		return this.outTime;
	}

	public void setOutTime(Timestamp outTime) {
		this.outTime = outTime;
	}

	public String getSimNo() {
		return this.simNo;
	}

	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}

	public BigInteger getType() {
		return this.type;
	}

	public void setType(BigInteger type) {
		this.type = type;
	}

	public BigInteger getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(BigInteger vehicle) {
		this.vehicle = vehicle;
	}

}