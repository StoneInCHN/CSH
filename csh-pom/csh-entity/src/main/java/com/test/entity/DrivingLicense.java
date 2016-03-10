package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the csh_driving_license database table.
 * 
 */
@Entity
@Table(name="csh_driving_license")
@NamedQuery(name="DrivingLicense.findAll", query="SELECT d FROM DrivingLicense d")
public class DrivingLicense implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_DRIVING_LICENSE_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_DRIVING_LICENSE_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private String address;

	private String brand;

	private String engine;

	private Timestamp issue;

	private String owner;

	private String plate;

	private Timestamp regist;

	private String status;

	private String type;

	private String useful;

	private String vin;

	//bi-directional many-to-one association to Vehicle
	@ManyToOne
	@JoinColumn(name="cid")
	private Vehicle cshVehicle;

	public DrivingLicense() {
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

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getEngine() {
		return this.engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public Timestamp getIssue() {
		return this.issue;
	}

	public void setIssue(Timestamp issue) {
		this.issue = issue;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getPlate() {
		return this.plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public Timestamp getRegist() {
		return this.regist;
	}

	public void setRegist(Timestamp regist) {
		this.regist = regist;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUseful() {
		return this.useful;
	}

	public void setUseful(String useful) {
		this.useful = useful;
	}

	public String getVin() {
		return this.vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Vehicle getCshVehicle() {
		return this.cshVehicle;
	}

	public void setCshVehicle(Vehicle cshVehicle) {
		this.cshVehicle = cshVehicle;
	}

}