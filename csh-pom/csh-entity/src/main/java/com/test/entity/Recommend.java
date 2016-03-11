package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cshm_recommend database table.
 * 
 */
@Entity
@Table(name="cshm_recommend")
@NamedQuery(name="Recommend.findAll", query="SELECT r FROM Recommend r")
public class Recommend implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSHM_RECOMMEND_RECOMID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSHM_RECOMMEND_RECOMID_GENERATOR")
	@Column(name="recom_id")
	private int recomId;

	@Column(name="recom_name")
	private String recomName;

	private int seq;

	@Column(name="store_id")
	private int storeId;

	public Recommend() {
	}

	public int getRecomId() {
		return this.recomId;
	}

	public void setRecomId(int recomId) {
		this.recomId = recomId;
	}

	public String getRecomName() {
		return this.recomName;
	}

	public void setRecomName(String recomName) {
		this.recomName = recomName;
	}

	public int getSeq() {
		return this.seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getStoreId() {
		return this.storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

}