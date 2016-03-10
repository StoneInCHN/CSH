package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;
import java.util.Set;


/**
 * The persistent class for the csh_vehicle database table.
 * 
 */
@Entity
@Table(name="csh_vehicle")
@NamedQuery(name="Vehicle.findAll", query="SELECT v FROM Vehicle v")
public class Vehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_VEHICLE_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_VEHICLE_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private String agent;

	@Column(name="bind_time")
	private Timestamp bindTime;

	private BigInteger brand;

	@Column(name="car_id")
	private String carId;

	private String color;

	private String device;

	@Column(name="driving_license")
	private String drivingLicense;

	private String flag;

	@Column(name="is_default")
	private int isDefault;

	private BigInteger module;

	private String plate;

	private BigInteger series;

	private String status;

	private String vin;

	//bi-directional many-to-one association to DrivingLicense
	@OneToMany(mappedBy="cshVehicle")
	private Set<DrivingLicense> cshDrivingLicenses;

	//bi-directional many-to-one association to UserInfo
	@ManyToOne
	@JoinColumn(name="uid")
	private UserInfo cshUserInfo;

	public Vehicle() {
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

	public String getAgent() {
		return this.agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public Timestamp getBindTime() {
		return this.bindTime;
	}

	public void setBindTime(Timestamp bindTime) {
		this.bindTime = bindTime;
	}

	public BigInteger getBrand() {
		return this.brand;
	}

	public void setBrand(BigInteger brand) {
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

	public String getDrivingLicense() {
		return this.drivingLicense;
	}

	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	public BigInteger getModule() {
		return this.module;
	}

	public void setModule(BigInteger module) {
		this.module = module;
	}

	public String getPlate() {
		return this.plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public BigInteger getSeries() {
		return this.series;
	}

	public void setSeries(BigInteger series) {
		this.series = series;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVin() {
		return this.vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Set<DrivingLicense> getCshDrivingLicenses() {
		return this.cshDrivingLicenses;
	}

	public void setCshDrivingLicenses(Set<DrivingLicense> cshDrivingLicenses) {
		this.cshDrivingLicenses = cshDrivingLicenses;
	}

	public DrivingLicense addCshDrivingLicens(DrivingLicense cshDrivingLicens) {
		getCshDrivingLicenses().add(cshDrivingLicens);
		cshDrivingLicens.setCshVehicle(this);

		return cshDrivingLicens;
	}

	public DrivingLicense removeCshDrivingLicens(DrivingLicense cshDrivingLicens) {
		getCshDrivingLicenses().remove(cshDrivingLicens);
		cshDrivingLicens.setCshVehicle(null);

		return cshDrivingLicens;
	}

	public UserInfo getCshUserInfo() {
		return this.cshUserInfo;
	}

	public void setCshUserInfo(UserInfo cshUserInfo) {
		this.cshUserInfo = cshUserInfo;
	}

}