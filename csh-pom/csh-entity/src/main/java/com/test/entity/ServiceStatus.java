package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cshm_service_status database table.
 * 
 */
@Entity
@Table(name="cshm_service_status")
@NamedQuery(name="ServiceStatus.findAll", query="SELECT s FROM ServiceStatus s")
public class ServiceStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String status;

	public ServiceStatus() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}