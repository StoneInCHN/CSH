package com.csh.entity;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.ServiceStatus;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 汽车服务
 *
 */
@Indexed(index = "carService")
@Entity
@Table(name = "csh_car_service")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_car_service_sequence")
public class CarService extends BaseEntity {

  /**
     *
     */
  private static final long serialVersionUID = 4496524488163375888L;

  /**
   * 服务名称
   */
  private String serviceName;
  /**
   * 服务简介
   */
  private String serviceDesc;

  /**
   * 服务介绍展示图片
   */
  private String imgPath;


  /**
   * 商家
   */
  private TenantInfo tenantInfo;

  /**
   * 服务分类
   */
  private ServiceCategory serviceCategory;

  /**
   * 服务单价
   */
  private BigDecimal price;

  /**
   * 活动优惠单价
   */
  private BigDecimal promotionPrice;

  /**
   * 评分
   */
  private Integer rate;

  /**
   * 服务状态
   */
  private ServiceStatus serviceStatus;

  /**
   * 汽车服务购买记录
   */
  private Set<CarServiceRecord> carServiceRecords = new HashSet<CarServiceRecord>();

  /**
   * 优惠券
   */
  private Set<Coupon> coupons = new HashSet<Coupon>();

  /**
   * 购买次数
   */
  private Integer purchaseCounts = 0;

  /**
   * 是否支持红包
   */
  private Boolean isAllowedRedPackage;
  
  /**
   * 红包最多抵扣额度
   */
  private BigDecimal redPackageMax;
  
  private Set<CarServiceItem> carServiceItems = new HashSet<CarServiceItem>();


  public Integer getPurchaseCounts() {
    return purchaseCounts;
  }

  public void setPurchaseCounts(Integer purchaseCounts) {
    this.purchaseCounts = purchaseCounts;
  }

  @ManyToMany(mappedBy = "carServices")
  public Set<Coupon> getCoupons() {
    return coupons;
  }

  public void setCoupons(Set<Coupon> coupons) {
    this.coupons = coupons;
  }

  @OneToMany(mappedBy = "carService")
  public Set<CarServiceRecord> getCarServiceRecords() {
    return carServiceRecords;
  }

  public void setCarServiceRecords(Set<CarServiceRecord> carServiceRecords) {
    this.carServiceRecords = carServiceRecords;
  }

  @Column(length = 50)
  @JsonProperty
  @Field(store = Store.NO, index = Index.YES, analyze = Analyze.NO, analyzer = @Analyzer(
      impl = IKAnalyzer.class))
  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  @Column(length = 1000)
  public String getServiceDesc() {
    return serviceDesc;
  }

  public void setServiceDesc(String serviceDesc) {
    this.serviceDesc = serviceDesc;
  }

  public String getImgPath() {
    return imgPath;
  }

  public void setImgPath(String imgPath) {
    this.imgPath = imgPath;
  }

  @ManyToOne
  @IndexedEmbedded
  public TenantInfo getTenantInfo() {
    return tenantInfo;
  }

  public void setTenantInfo(TenantInfo tenantInfo) {
    this.tenantInfo = tenantInfo;
  }

  @ManyToOne
  @JsonProperty
  @IndexedEmbedded
  public ServiceCategory getServiceCategory() {
    return serviceCategory;
  }

  public void setServiceCategory(ServiceCategory serviceCategory) {
    this.serviceCategory = serviceCategory;
  }

  @Column(scale = 2, precision = 10, nullable = false)
  @JsonProperty
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Column(scale = 2, precision = 10, nullable = false)
  @JsonProperty
  public BigDecimal getPromotionPrice() {
    return promotionPrice;
  }

  public void setPromotionPrice(BigDecimal promotionPrice) {
    this.promotionPrice = promotionPrice;
  }

  @JsonProperty
  public Integer getRate() {
    return rate;
  }

  public void setRate(Integer rate) {
    this.rate = rate;
  }

  @JsonProperty
  @Field(store = Store.NO, index = Index.YES, analyze = Analyze.NO, analyzer = @Analyzer(
      impl = IKAnalyzer.class))
  public ServiceStatus getServiceStatus() {
    return serviceStatus;
  }

  public void setServiceStatus(ServiceStatus serviceStatus) {
    this.serviceStatus = serviceStatus;
  }

  @OneToMany(mappedBy = "carService")
  public Set<CarServiceItem> getCarServiceItems() {
    return carServiceItems;
  }

  public void setCarServiceItems(Set<CarServiceItem> carServiceItems) {
    this.carServiceItems = carServiceItems;
  }

  public Boolean getIsAllowedRedPackage() {
	return isAllowedRedPackage;
  }

  public void setIsAllowedRedPackage(Boolean isAllowedRedPackage) {
	this.isAllowedRedPackage = isAllowedRedPackage;
  }

  @Column(scale = 6, precision = 14)
  @JsonProperty
  public BigDecimal getRedPackageMax() {
	return redPackageMax;
  }

  public void setRedPackageMax(BigDecimal redPackageMax) {
	this.redPackageMax = redPackageMax;
  }
  
}
