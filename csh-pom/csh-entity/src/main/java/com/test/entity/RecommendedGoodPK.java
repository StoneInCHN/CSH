package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cshm_recommended_goods database table.
 * 
 */
@Embeddable
public class RecommendedGoodPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="recom_id")
	private int recomId;

	@Column(name="goods_id")
	private int goodsId;

	public RecommendedGoodPK() {
	}
	public int getRecomId() {
		return this.recomId;
	}
	public void setRecomId(int recomId) {
		this.recomId = recomId;
	}
	public int getGoodsId() {
		return this.goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RecommendedGoodPK)) {
			return false;
		}
		RecommendedGoodPK castOther = (RecommendedGoodPK)other;
		return 
			(this.recomId == castOther.recomId)
			&& (this.goodsId == castOther.goodsId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.recomId;
		hash = hash * prime + this.goodsId;
		
		return hash;
	}
}