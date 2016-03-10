package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cshm_goods_statistics database table.
 * 
 */
@Entity
@Table(name="cshm_goods_statistics")
@NamedQuery(name="GoodsStatistic.findAll", query="SELECT g FROM GoodsStatistic g")
public class GoodsStatistic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSHM_GOODS_STATISTICS_GOODSID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSHM_GOODS_STATISTICS_GOODSID_GENERATOR")
	@Column(name="goods_id")
	private int goodsId;

	private int carts;

	private int collects;

	private int comments;

	private int orders;

	private int sales;

	private int views;

	public GoodsStatistic() {
	}

	public int getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getCarts() {
		return this.carts;
	}

	public void setCarts(int carts) {
		this.carts = carts;
	}

	public int getCollects() {
		return this.collects;
	}

	public void setCollects(int collects) {
		this.collects = collects;
	}

	public int getComments() {
		return this.comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public int getOrders() {
		return this.orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public int getSales() {
		return this.sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public int getViews() {
		return this.views;
	}

	public void setViews(int views) {
		this.views = views;
	}

}