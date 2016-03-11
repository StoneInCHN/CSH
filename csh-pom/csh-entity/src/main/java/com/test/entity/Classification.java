package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the cshm_classification database table.
 * 
 */
@Entity
@Table(name="cshm_classification")
@NamedQuery(name="Classification.findAll", query="SELECT c FROM Classification c")
public class Classification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSHM_CLASSIFICATION_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSHM_CLASSIFICATION_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	@Column(name="classification_name")
	private String classificationName;

	private String level;

	private String status;

	@Column(name="superior_id")
	private BigInteger superiorId;

	private String type;

	public Classification() {
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

	public String getClassificationName() {
		return this.classificationName;
	}

	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigInteger getSuperiorId() {
		return this.superiorId;
	}

	public void setSuperiorId(BigInteger superiorId) {
		this.superiorId = superiorId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}