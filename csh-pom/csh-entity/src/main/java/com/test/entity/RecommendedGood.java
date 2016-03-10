package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cshm_recommended_goods database table.
 * 
 */
@Entity
@Table(name="cshm_recommended_goods")
@NamedQuery(name="RecommendedGood.findAll", query="SELECT r FROM RecommendedGood r")
public class RecommendedGood implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RecommendedGoodPK id;

	@Column(name="sort_order")
	private byte sortOrder;

	public RecommendedGood() {
	}

	public RecommendedGoodPK getId() {
		return this.id;
	}

	public void setId(RecommendedGoodPK id) {
		this.id = id;
	}

	public byte getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(byte sortOrder) {
		this.sortOrder = sortOrder;
	}

}