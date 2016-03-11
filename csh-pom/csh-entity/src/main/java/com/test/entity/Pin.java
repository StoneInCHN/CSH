package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the csh_pin database table.
 * 
 */
@Entity
@Table(name="csh_pin")
@NamedQuery(name="Pin.findAll", query="SELECT p FROM Pin p")
public class Pin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_PIN_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_PIN_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private String called;

	private String code;

	private String path;

	private String respCode;

	private String status;

	private int type;

	private String ucid;

	public Pin() {
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

	public String getCalled() {
		return this.called;
	}

	public void setCalled(String called) {
		this.called = called;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRespCode() {
		return this.respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUcid() {
		return this.ucid;
	}

	public void setUcid(String ucid) {
		this.ucid = ucid;
	}

}