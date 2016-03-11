package com.csh.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the csh_vehicle_maintain database table.
 * 
 */
@Entity
@Table(name="csh_vehicle_maintain")
@NamedQuery(name="VehicleMaintain.findAll", query="SELECT v FROM VehicleMaintain v")
public class VehicleMaintain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_VEHICLE_MAINTAIN_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_VEHICLE_MAINTAIN_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private BigInteger cid;

	private Timestamp inspection;

	private float maintain;

	private float mileage;

	public VehicleMaintain() {
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

	public BigInteger getCid() {
		return this.cid;
	}

	public void setCid(BigInteger cid) {
		this.cid = cid;
	}

	public Timestamp getInspection() {
		return this.inspection;
	}

	public void setInspection(Timestamp inspection) {
		this.inspection = inspection;
	}

	public float getMaintain() {
		return this.maintain;
	}

	public void setMaintain(float maintain) {
		this.maintain = maintain;
	}

	public float getMileage() {
		return this.mileage;
	}

	public void setMileage(float mileage) {
		this.mileage = mileage;
	}

}