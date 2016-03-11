package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the csh_vehicle_dtc database table.
 * 
 */
@Entity
@Table(name="csh_vehicle_dtc")
@NamedQuery(name="VehicleDtc.findAll", query="SELECT v FROM VehicleDtc v")
public class VehicleDtc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_VEHICLE_DTC_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_VEHICLE_DTC_ID_GENERATOR")
	private String id;

	private String brand;

	private String code;

	private String des;

	public VehicleDtc() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

}