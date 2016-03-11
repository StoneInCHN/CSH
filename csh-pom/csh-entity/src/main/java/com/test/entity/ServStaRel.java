package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cshm_serv_sta_rel database table.
 * 
 */
@Entity
@Table(name="cshm_serv_sta_rel")
@NamedQuery(name="ServStaRel.findAll", query="SELECT s FROM ServStaRel s")
public class ServStaRel implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	@Column(name="serv_id")
	private int servId;

	private int sort;

	@Column(name="status_id")
	private int statusId;

	public ServStaRel() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getServId() {
		return this.servId;
	}

	public void setServId(int servId) {
		this.servId = servId;
	}

	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getStatusId() {
		return this.statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

}