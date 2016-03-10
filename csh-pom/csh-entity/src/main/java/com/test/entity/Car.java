package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the xa_car database table.
 * 
 */
@Entity
@Table(name="xa_car")
@NamedQuery(name="Car.findAll", query="SELECT c FROM Car c")
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="XA_CAR_CID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="XA_CAR_CID_GENERATOR")
	private String cid;

	@Column(name="add_time")
	private String addTime;

	@Column(name="bound_json")
	private String boundJson;

	private String brand;

	@Column(name="car_id")
	private String carId;

	private String color;

	private String device;

	private String flag;

	@Column(name="is_bound")
	private int isBound;

	@Column(name="is_default")
	private int isDefault;

	private double mile;

	private String module;

	private String note;

	@Column(name="obd_end")
	private String obdEnd;

	@Column(name="obd_start")
	private String obdStart;

	private String plate;

	private String serial;

	private String series;

	@Column(name="sim_no")
	private String simNo;

	private BigInteger uid;

	@Column(name="vin_no")
	private String vinNo;

	public Car() {
	}

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getBoundJson() {
		return this.boundJson;
	}

	public void setBoundJson(String boundJson) {
		this.boundJson = boundJson;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCarId() {
		return this.carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDevice() {
		return this.device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getIsBound() {
		return this.isBound;
	}

	public void setIsBound(int isBound) {
		this.isBound = isBound;
	}

	public int getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	public double getMile() {
		return this.mile;
	}

	public void setMile(double mile) {
		this.mile = mile;
	}

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getObdEnd() {
		return this.obdEnd;
	}

	public void setObdEnd(String obdEnd) {
		this.obdEnd = obdEnd;
	}

	public String getObdStart() {
		return this.obdStart;
	}

	public void setObdStart(String obdStart) {
		this.obdStart = obdStart;
	}

	public String getPlate() {
		return this.plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getSeries() {
		return this.series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getSimNo() {
		return this.simNo;
	}

	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}

	public BigInteger getUid() {
		return this.uid;
	}

	public void setUid(BigInteger uid) {
		this.uid = uid;
	}

	public String getVinNo() {
		return this.vinNo;
	}

	public void setVinNo(String vinNo) {
		this.vinNo = vinNo;
	}

}