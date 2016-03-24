package com.csh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;
import org.springframework.web.multipart.MultipartFile;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.Status;
import com.csh.entity.commonenum.CommonEnum.SystemType;


/**
 * Entity - 广告
 * 
 * @author Andrea
 *
 */
@Entity
@Table(name = "csh_advertisement")
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
  private Long tenantId;
  
  /**
   * 所属系统
   */
  private SystemType systemType;


  @Index(name = "index_ad_tenantid")
  public Long getTenantId() {
    return tenantId;
  }

  public void setTenantId(Long tenantId) {
    this.tenantId = tenantId;
  }

  @Column(length = 400)
  public String getAdvImageUrl() {
    return advImageUrl;
  }

  public void setAdvImageUrl(String advImageUrl) {
    this.advImageUrl = advImageUrl;
  }

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

  
  
}
