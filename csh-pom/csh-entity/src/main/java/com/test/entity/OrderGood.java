package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the cshm_order_goods database table.
 * 
 */
@Entity
@Table(name="cshm_order_goods")
@NamedQuery(name="OrderGood.findAll", query="SELECT o FROM OrderGood o")
public class OrderGood implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSHM_ORDER_GOODS_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSHM_ORDER_GOODS_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private String comment;

	@Column(name="credit_value")
	private byte creditValue;

	private byte evaluation;

	@Column(name="goods_id")
	private int goodsId;

	@Column(name="goods_image")
	private String goodsImage;

	@Column(name="goods_name")
	private String goodsName;

	@Column(name="is_valid")
	private byte isValid;

	@Column(name="order_id")
	private BigInteger orderId;

	private float price;

	private int quantity;

	@Column(name="spec_id")
	private int specId;

	private String specification;

	public OrderGood() {
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

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public byte getCreditValue() {
		return this.creditValue;
	}

	public void setCreditValue(byte creditValue) {
		this.creditValue = creditValue;
	}

	public byte getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(byte evaluation) {
		this.evaluation = evaluation;
	}

	public int getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsImage() {
		return this.goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public byte getIsValid() {
		return this.isValid;
	}

	public void setIsValid(byte isValid) {
		this.isValid = isValid;
	}

	public BigInteger getOrderId() {
		return this.orderId;
	}

	public void setOrderId(BigInteger orderId) {
		this.orderId = orderId;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSpecId() {
		return this.specId;
	}

	public void setSpecId(int specId) {
		this.specId = specId;
	}

	public String getSpecification() {
		return this.specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

}