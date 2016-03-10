package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cshm_gcategory database table.
 * 
 */
@Entity
@Table(name="cshm_gcategory")
@NamedQuery(name="Gcategory.findAll", query="SELECT g FROM Gcategory g")
public class Gcategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSHM_GCATEGORY_CATEID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSHM_GCATEGORY_CATEID_GENERATOR")
	@Column(name="cate_id")
	private int cateId;

	@Column(name="cate_images")
	private String cateImages;

	@Column(name="cate_name")
	private String cateName;

	@Column(name="if_show")
	private byte ifShow;

	@Column(name="parent_id")
	private int parentId;

	@Column(name="sort_order")
	private byte sortOrder;

	@Column(name="store_id")
	private int storeId;

	public Gcategory() {
	}

	public int getCateId() {
		return this.cateId;
	}

	public void setCateId(int cateId) {
		this.cateId = cateId;
	}

	public String getCateImages() {
		return this.cateImages;
	}

	public void setCateImages(String cateImages) {
		this.cateImages = cateImages;
	}

	public String getCateName() {
		return this.cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public byte getIfShow() {
		return this.ifShow;
	}

	public void setIfShow(byte ifShow) {
		this.ifShow = ifShow;
	}

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public byte getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(byte sortOrder) {
		this.sortOrder = sortOrder;
	}

	public int getStoreId() {
		return this.storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

}