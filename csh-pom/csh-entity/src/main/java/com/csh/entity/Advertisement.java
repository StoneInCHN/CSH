package com.csh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.springframework.web.multipart.MultipartFile;

import com.csh.entity.commonenum.CommonEnum.AdType;
import com.csh.entity.commonenum.CommonEnum.Status;
import com.csh.entity.commonenum.CommonEnum.SystemType;
import com.csh.lucene.LowCaseBridgeImpl;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Entity - 广告
 * 
 * @author Andrea
 *
 */
@Entity
@Table(name = "csh_advertisement")
@Indexed(index = "advertisement")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_advertisement_sequence")
public class Advertisement extends OrderEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 4509383295262124028L;



  /** 广告名字 */
  private String advName;

  /** 广告图片Url */
  private String advImageUrl;

  /** 广告图片 */
  private MultipartFile advImage;

  /** 广告内容link */
  private String advContentLink;


  /** 状态 */
  private Status status;

  /**
   * 备注
   */
  private String remark;

  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 所属系统
   */
  private SystemType systemType;
  /**
   * 广告类型
   */
  private AdType adType;
  /**
   * 广告分辨率
   */
  private ResolutionConfig resolutionConfig;
  

  @Index(name = "index_ad_tenantid")
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.YES,analyze = Analyze.NO)
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  @Column(length = 400)
  public String getAdvImageUrl() {
    return advImageUrl;
  }

  public void setAdvImageUrl(String advImageUrl) {
    this.advImageUrl = advImageUrl;
  }

  @JsonProperty
  @Field(store = Store.NO,index = org.hibernate.search.annotations.Index.YES,analyze = Analyze.NO)
  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Column(length = 100)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Column(length = 50)
  @JsonProperty
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.YES,analyze = Analyze.NO)
  @FieldBridge(impl = LowCaseBridgeImpl.class)
  public String getAdvName() {
    return advName;
  }

  public void setAdvName(String advName) {
    this.advName = advName;
  }

  @Transient
  public MultipartFile getAdvImage() {
    return advImage;
  }

  public void setAdvImage(MultipartFile advImage) {
    this.advImage = advImage;
  }


  @Column(length = 300)
  public String getAdvContentLink() {
    return advContentLink;
  }

  public void setAdvContentLink(String advContentLink) {
    this.advContentLink = advContentLink;
  }

  public SystemType getSystemType() {
    return systemType;
  }

  public void setSystemType(SystemType systemType) {
    this.systemType = systemType;
  }

  public AdType getAdType() {
    return adType;
  }

  public void setAdType(AdType adType) {
    this.adType = adType;
  }
  
  @ManyToOne
  public ResolutionConfig getResolutionConfig() {
    return resolutionConfig;
  }

  public void setResolutionConfig(ResolutionConfig resolutionConfig) {
    this.resolutionConfig = resolutionConfig;
  }

  
  
}
