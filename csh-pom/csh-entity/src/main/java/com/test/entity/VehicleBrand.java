package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the csh_vehicle_brand database table.
 * 
 */
@Entity
@Table(name="csh_vehicle_brand")
@NamedQuery(name="VehicleBrand.findAll", query="SELECT v FROM VehicleBrand v")
public class VehicleBrand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_VEHICLE_BRAND_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_VEHICLE_BRAND_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private String admin;

	@Column(name="average_oil")
	private float averageOil;

	private String code;

	private float disp;

	private int getmileage;

	private int getoil;

	private int grade;

	private String icon;

	private float maxbv;

	private float minbv;

	private String name;

	private int nobd;

	private float oil;

	private BigInteger parent;

	private String status;

	private float tank;

	private int type;

	@Column(name="use_oil")
	private int useOil;

	public VehicleBrand() {
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

	public float getAverageOil() {
		return this.averageOil;
	}

	public void setAverageOil(float averageOil) {
		this.averageOil = averageOil;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public float getDisp() {
		return this.disp;
	}

	public void setDisp(float disp) {
		this.disp = disp;
	}

	public int getGetmileage() {
		return this.getmileage;
	}

	public void setGetmileage(int getmileage) {
		this.getmileage = getmileage;
	}

	public int getGetoil() {
		return this.getoil;
	}

	public void setGetoil(int getoil) {
		this.getoil = getoil;
	}

	public int getGrade() {
		return this.grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public float getMaxbv() {
		return this.maxbv;
	}

	public void setMaxbv(float maxbv) {
		this.maxbv = maxbv;
	}

	public float getMinbv() {
		return this.minbv;
	}

	public void setMinbv(float minbv) {
		this.minbv = minbv;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNobd() {
		return this.nobd;
	}

	public void setNobd(int nobd) {
		this.nobd = nobd;
	}

	public float getOil() {
		return this.oil;
	}

	public void setOil(float oil) {
		this.oil = oil;
	}

	public BigInteger getParent() {
		return this.parent;
	}

	public void setParent(BigInteger parent) {
		this.parent = parent;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getTank() {
		return this.tank;
	}

	public void setTank(float tank) {
		this.tank = tank;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getUseOil() {
		return this.useOil;
	}

	public void setUseOil(int useOil) {
		this.useOil = useOil;
	}

}