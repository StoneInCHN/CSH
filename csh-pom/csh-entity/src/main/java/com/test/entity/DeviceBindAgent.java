package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the csh_device_bind_agent database table.
 * 
 */
@Entity
@Table(name="csh_device_bind_agent")
@NamedQuery(name="DeviceBindAgent.findAll", query="SELECT d FROM DeviceBindAgent d")
public class DeviceBindAgent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_DEVICE_BIND_AGENT_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_DEVICE_BIND_AGENT_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private String addTime;

	private String agent;

	private String device;

	private String status;

	public DeviceBindAgent() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getAgent() {
		return this.agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getDevice() {
		return this.device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}