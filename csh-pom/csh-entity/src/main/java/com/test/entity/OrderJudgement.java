package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the cshm_order_judgement database table.
 * 
 */
@Entity
@Table(name="cshm_order_judgement")
@NamedQuery(name="OrderJudgement.findAll", query="SELECT o FROM OrderJudgement o")
public class OrderJudgement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSHM_ORDER_JUDGEMENT_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSHM_ORDER_JUDGEMENT_ID_GENERATOR")
	private String id;

	private float attitude;

	private String content;

	private float environment;

	private BigInteger gid;

	private String icon;

	private BigInteger oid;

	private float skill;

	private String status;

	private Timestamp time;

	private BigInteger uid;

	public OrderJudgement() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getAttitude() {
		return this.attitude;
	}

	public void setAttitude(float attitude) {
		this.attitude = attitude;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public float getEnvironment() {
		return this.environment;
	}

	public void setEnvironment(float environment) {
		this.environment = environment;
	}

	public BigInteger getGid() {
		return this.gid;
	}

	public void setGid(BigInteger gid) {
		this.gid = gid;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public BigInteger getOid() {
		return this.oid;
	}

	public void setOid(BigInteger oid) {
		this.oid = oid;
	}

	public float getSkill() {
		return this.skill;
	}

	public void setSkill(float skill) {
		this.skill = skill;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public BigInteger getUid() {
		return this.uid;
	}

	public void setUid(BigInteger uid) {
		this.uid = uid;
	}

}