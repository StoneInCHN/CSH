package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the cshm_service_profile database table.
 * 
 */
@Entity
@Table(name="cshm_service_profile")
@NamedQuery(name="ServiceProfile.findAll", query="SELECT s FROM ServiceProfile s")
public class ServiceProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	private BigInteger id;

	@Column(name="s_name")
	private String sName;

	private String type;

	@Temporal(TemporalType.DATE)
	@Column(name="vali_date")
	private Date valiDate;

	private int version;

	public ServiceProfile() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigInteger getId() {
		return this.id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getSName() {
		return this.sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getValiDate() {
		return this.valiDate;
	}

	public void setValiDate(Date valiDate) {
		this.valiDate = valiDate;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}