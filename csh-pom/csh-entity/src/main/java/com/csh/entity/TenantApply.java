package com.csh.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.ApplyStatus;

/**
 * 租户申请
 * 
 * @author tanbiao
 * 
 */
@Entity
@Table(name = "csh_tenant_apply")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_tenant_apply_sequence")
public class TenantApply extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -8192567174284286492L;
  /**
   * 租户企业名字
   */
  private String tenantName;
  /**
   * 联系电话
   */
  private String contactPhone;
  /**
   * 租户地址
   */
  private String address;

  /**
   * 联系人
   */
  private String contactPerson;
  /** 地区 */
  private Area area;

  /**
   * 营业执照
   */
  private String license;

  /** 文件 */
  private MultipartFile licenseFile;
  
  /**
   * 门店照片
   */
  private String photo;

  /** 文件 */
  private MultipartFile photoFile;
  
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

  /**
   * 审核状态
   */
  private ApplyStatus applyStatus;
  
  private String notes;
  
  public String getTenantName() {
    return tenantName;
  }

  public void setTenantName(String tenantName) {
    this.tenantName = tenantName;
  }

  public String getContactPhone() {
    return contactPhone;
  }

  public void setContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getContactPerson() {
    return contactPerson;
  }

  public void setContactPerson(String contactPerson) {
    this.contactPerson = contactPerson;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public Area getArea() {
    return area;
  }

  public void setArea(Area area) {
    this.area = area;
  }

  public String getLicense() {
    return license;
  }

  public void setLicense(String license) {
    this.license = license;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  @Column(scale = 6, precision = 10, nullable = true)
  public BigDecimal getLatitude() {
    return latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }

  @Column(scale = 6, precision = 10, nullable = true)
  public BigDecimal getLongitude() {
    return longitude;
  }

  public void setLongitude(BigDecimal longitude) {
    this.longitude = longitude;
  }

  public ApplyStatus getApplyStatus() {
    return applyStatus;
  }

  public void setApplyStatus(ApplyStatus applyStatus) {
    this.applyStatus = applyStatus;
  }

  @Transient
  public MultipartFile getLicenseFile() {
    return licenseFile;
  }

  public void setLicenseFile(MultipartFile licenseFile) {
    this.licenseFile = licenseFile;
  }

  @Transient
  public MultipartFile getPhotoFile() {
    return photoFile;
  }

  public void setPhotoFile(MultipartFile photoFile) {
    this.photoFile = photoFile;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }
  
}
