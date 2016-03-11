package com.csh.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.entity.base.BaseEntity;

/**
 * 商家
 * 
 * @author tanbiao
 *
 */
@Indexed(index = "Vendor")
@Entity
@Table(name = "csh_vendor")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_vendor_sequence")
public class Vendor extends BaseEntity {

  private static final long serialVersionUID = -7728373010431015082L;

  /**
   * 名称
   */
  private String vendorName;

  /**
   * 简介
   */
  private String vendorIntro;

  /**
   * 联系电话
   */
  private String vendorPhone;

  /**
   * 地址
   */
  private String vendorAddress;


  /**
   * 用户
   */
  private Admin admin;

  /** 地区 */
  private Area area;



  /**
   * 纬度
   */
  @Column(name = "Latitude", scale = 6, precision = 10, nullable = true)
  private BigDecimal latitude;

  /**
   * 经度
   */
  @Column(name = "Longitude", scale = 6, precision = 10, nullable = true)
  private BigDecimal longitude;

  



  @Field(store = Store.YES, index = Index.TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getVendorName() {
    return vendorName;
  }

  public void setVendorName(String vendorName) {
    this.vendorName = vendorName;
  }

  public String getVendorIntro() {
    return vendorIntro;
  }

  public void setVendorIntro(String vendorIntro) {
    this.vendorIntro = vendorIntro;
  }

  public String getVendorPhone() {
    return vendorPhone;
  }

  public void setVendorPhone(String vendorPhone) {
    this.vendorPhone = vendorPhone;
  }

  public String getVendorAddress() {
    return vendorAddress;
  }

  public void setVendorAddress(String vendorAddress) {
    this.vendorAddress = vendorAddress;
  }


  public BigDecimal getLatitude() {
    return latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }

  public BigDecimal getLongitude() {
    return longitude;
  }

  public void setLongitude(BigDecimal longitude) {
    this.longitude = longitude;
  }

  @OneToOne(fetch = FetchType.LAZY)
  public Admin getAdmin() {
    return admin;
  }

  public void setAdmin(Admin admin) {
    this.admin = admin;
  }

  /**
   * 获取地区
   * 
   * @return 地区
   */
  @ManyToOne(fetch = FetchType.LAZY)
  public Area getArea() {
    return area;
  }

  /**
   * 设置地区
   * 
   * @param area 地区
   */
  public void setArea(Area area) {
    this.area = area;
  }



}
