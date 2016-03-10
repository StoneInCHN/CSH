package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the cshm_store database table.
 * 
 */
@Entity
@Table(name="cshm_store")
@NamedQuery(name="Store.findAll", query="SELECT s FROM Store s")
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSHM_STORE_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSHM_STORE_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private String address;

	@Column(name="apply_remark")
	private String applyRemark;

	@Column(name="business_time")
	private String businessTime;

	private String certification;

	@Column(name="close_reason")
	private String closeReason;

	@Column(name="credit_value")
	private int creditValue;

	@Lob
	private String description;

	private String domain;

	@Column(name="enable_groupbuy")
	private byte enableGroupbuy;

	@Column(name="enable_radar")
	private byte enableRadar;

	@Column(name="end_time")
	private Timestamp endTime;

	@Column(name="im_lat")
	private String imLat;

	@Column(name="im_lng")
	private String imLng;

	@Column(name="im_qq")
	private String imQq;

	@Column(name="im_type")
	private String imType;

	@Column(name="image_1")
	private String image1;

	@Column(name="image_2")
	private String image2;

	@Column(name="image_3")
	private String image3;

	@Column(name="owner_card")
	private String ownerCard;

	@Column(name="owner_name")
	private String ownerName;

	@Column(name="praise_rate")
	private BigDecimal praiseRate;

	private byte recommended;

	@Column(name="region_id")
	private int regionId;

	@Column(name="region_name")
	private String regionName;

	private byte sgrade;

	@Column(name="sort_order")
	private int sortOrder;

	private byte state;

	@Column(name="store_banner")
	private String storeBanner;

	@Column(name="store_code")
	private String storeCode;

	@Column(name="store_hot_images")
	private String storeHotImages;

	@Column(name="store_logo")
	private String storeLogo;

	@Column(name="store_name")
	private String storeName;

	@Column(name="store_new_images")
	private String storeNewImages;

	@Column(name="store_recommend_images")
	private String storeRecommendImages;

	private String tel;

	private String theme;

	private BigInteger uid;

	private String zipcode;

	public Store() {
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

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getApplyRemark() {
		return this.applyRemark;
	}

	public void setApplyRemark(String applyRemark) {
		this.applyRemark = applyRemark;
	}

	public String getBusinessTime() {
		return this.businessTime;
	}

	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}

	public String getCertification() {
		return this.certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public String getCloseReason() {
		return this.closeReason;
	}

	public void setCloseReason(String closeReason) {
		this.closeReason = closeReason;
	}

	public int getCreditValue() {
		return this.creditValue;
	}

	public void setCreditValue(int creditValue) {
		this.creditValue = creditValue;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public byte getEnableGroupbuy() {
		return this.enableGroupbuy;
	}

	public void setEnableGroupbuy(byte enableGroupbuy) {
		this.enableGroupbuy = enableGroupbuy;
	}

	public byte getEnableRadar() {
		return this.enableRadar;
	}

	public void setEnableRadar(byte enableRadar) {
		this.enableRadar = enableRadar;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getImLat() {
		return this.imLat;
	}

	public void setImLat(String imLat) {
		this.imLat = imLat;
	}

	public String getImLng() {
		return this.imLng;
	}

	public void setImLng(String imLng) {
		this.imLng = imLng;
	}

	public String getImQq() {
		return this.imQq;
	}

	public void setImQq(String imQq) {
		this.imQq = imQq;
	}

	public String getImType() {
		return this.imType;
	}

	public void setImType(String imType) {
		this.imType = imType;
	}

	public String getImage1() {
		return this.image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return this.image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return this.image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getOwnerCard() {
		return this.ownerCard;
	}

	public void setOwnerCard(String ownerCard) {
		this.ownerCard = ownerCard;
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public BigDecimal getPraiseRate() {
		return this.praiseRate;
	}

	public void setPraiseRate(BigDecimal praiseRate) {
		this.praiseRate = praiseRate;
	}

	public byte getRecommended() {
		return this.recommended;
	}

	public void setRecommended(byte recommended) {
		this.recommended = recommended;
	}

	public int getRegionId() {
		return this.regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public byte getSgrade() {
		return this.sgrade;
	}

	public void setSgrade(byte sgrade) {
		this.sgrade = sgrade;
	}

	public int getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public byte getState() {
		return this.state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public String getStoreBanner() {
		return this.storeBanner;
	}

	public void setStoreBanner(String storeBanner) {
		this.storeBanner = storeBanner;
	}

	public String getStoreCode() {
		return this.storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreHotImages() {
		return this.storeHotImages;
	}

	public void setStoreHotImages(String storeHotImages) {
		this.storeHotImages = storeHotImages;
	}

	public String getStoreLogo() {
		return this.storeLogo;
	}

	public void setStoreLogo(String storeLogo) {
		this.storeLogo = storeLogo;
	}

	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreNewImages() {
		return this.storeNewImages;
	}

	public void setStoreNewImages(String storeNewImages) {
		this.storeNewImages = storeNewImages;
	}

	public String getStoreRecommendImages() {
		return this.storeRecommendImages;
	}

	public void setStoreRecommendImages(String storeRecommendImages) {
		this.storeRecommendImages = storeRecommendImages;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public BigInteger getUid() {
		return this.uid;
	}

	public void setUid(BigInteger uid) {
		this.uid = uid;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}