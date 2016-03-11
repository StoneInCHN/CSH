package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the cshm_goods database table.
 * 
 */
@Entity
@Table(name="cshm_goods")
@NamedQuery(name="Good.findAll", query="SELECT g FROM Good g")
public class Good implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSHM_GOODS_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSHM_GOODS_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private String brand;

	@Column(name="cate_id")
	private int cateId;

	@Column(name="cate_id_1")
	private int cateId1;

	@Column(name="cate_id_2")
	private int cateId2;

	@Column(name="cate_id_3")
	private int cateId3;

	@Column(name="cate_id_4")
	private int cateId4;

	@Column(name="cate_name")
	private String cateName;

	@Column(name="close_reason")
	private String closeReason;

	@Column(name="default_image")
	private String defaultImage;

	@Column(name="default_spec")
	private int defaultSpec;

	@Lob
	private String description;

	@Column(name="discount_price")
	private float discountPrice;

	@Column(name="goods_name")
	private String goodsName;

	@Column(name="if_show")
	private byte ifShow;

	@Column(name="is_discount_price")
	private int isDiscountPrice;

	@Column(name="last_update")
	private int lastUpdate;

	private float price;

	private byte recommended;

	@Column(name="spec_name_1")
	private String specName1;

	@Column(name="spec_name_2")
	private String specName2;

	@Column(name="spec_qty")
	private byte specQty;

	private byte status;

	@Column(name="store_id")
	private BigInteger storeId;

	@Column(name="support_red")
	private int supportRed;

	private String tags;

	private String type;

	public Good() {
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

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getCateId() {
		return this.cateId;
	}

	public void setCateId(int cateId) {
		this.cateId = cateId;
	}

	public int getCateId1() {
		return this.cateId1;
	}

	public void setCateId1(int cateId1) {
		this.cateId1 = cateId1;
	}

	public int getCateId2() {
		return this.cateId2;
	}

	public void setCateId2(int cateId2) {
		this.cateId2 = cateId2;
	}

	public int getCateId3() {
		return this.cateId3;
	}

	public void setCateId3(int cateId3) {
		this.cateId3 = cateId3;
	}

	public int getCateId4() {
		return this.cateId4;
	}

	public void setCateId4(int cateId4) {
		this.cateId4 = cateId4;
	}

	public String getCateName() {
		return this.cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getCloseReason() {
		return this.closeReason;
	}

	public void setCloseReason(String closeReason) {
		this.closeReason = closeReason;
	}

	public String getDefaultImage() {
		return this.defaultImage;
	}

	public void setDefaultImage(String defaultImage) {
		this.defaultImage = defaultImage;
	}

	public int getDefaultSpec() {
		return this.defaultSpec;
	}

	public void setDefaultSpec(int defaultSpec) {
		this.defaultSpec = defaultSpec;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getDiscountPrice() {
		return this.discountPrice;
	}

	public void setDiscountPrice(float discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public byte getIfShow() {
		return this.ifShow;
	}

	public void setIfShow(byte ifShow) {
		this.ifShow = ifShow;
	}

	public int getIsDiscountPrice() {
		return this.isDiscountPrice;
	}

	public void setIsDiscountPrice(int isDiscountPrice) {
		this.isDiscountPrice = isDiscountPrice;
	}

	public int getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(int lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public byte getRecommended() {
		return this.recommended;
	}

	public void setRecommended(byte recommended) {
		this.recommended = recommended;
	}

	public String getSpecName1() {
		return this.specName1;
	}

	public void setSpecName1(String specName1) {
		this.specName1 = specName1;
	}

	public String getSpecName2() {
		return this.specName2;
	}

	public void setSpecName2(String specName2) {
		this.specName2 = specName2;
	}

	public byte getSpecQty() {
		return this.specQty;
	}

	public void setSpecQty(byte specQty) {
		this.specQty = specQty;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public BigInteger getStoreId() {
		return this.storeId;
	}

	public void setStoreId(BigInteger storeId) {
		this.storeId = storeId;
	}

	public int getSupportRed() {
		return this.supportRed;
	}

	public void setSupportRed(int supportRed) {
		this.supportRed = supportRed;
	}

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}