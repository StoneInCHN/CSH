package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cshm_goods_attr database table.
 * 
 */
@Entity
@Table(name="cshm_goods_attr")
@NamedQuery(name="GoodsAttr.findAll", query="SELECT g FROM GoodsAttr g")
public class GoodsAttr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSHM_GOODS_ATTR_GATTRID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSHM_GOODS_ATTR_GATTRID_GENERATOR")
	@Column(name="gattr_id")
	private int gattrId;

	@Column(name="attr_id")
	private int attrId;

	@Column(name="attr_name")
	private String attrName;

	@Column(name="attr_value")
	private String attrValue;

	@Column(name="goods_id")
	private int goodsId;

	@Column(name="sort_order")
	private byte sortOrder;

	public GoodsAttr() {
	}

	public int getGattrId() {
		return this.gattrId;
	}

	public void setGattrId(int gattrId) {
		this.gattrId = gattrId;
	}

	public int getAttrId() {
		return this.attrId;
	}

	public void setAttrId(int attrId) {
		this.attrId = attrId;
	}

	public String getAttrName() {
		return this.attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrValue() {
		return this.attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public int getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public byte getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(byte sortOrder) {
		this.sortOrder = sortOrder;
	}

}