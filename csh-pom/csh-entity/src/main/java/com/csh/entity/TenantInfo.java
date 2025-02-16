package com.csh.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;

/**
 * 租户信息
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "csh_tenant_info")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_tenant_info_sequence")
public class TenantInfo extends BaseEntity {


  private static final long serialVersionUID = 3525183712762376969L;

  /**
   * 租户代码
   */
  private String orgCode;

  /**
   * 租户名称
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

  /** 邮编 */
  private String zipCode;

  /** E-mail */
  private String email;
  /**
   * 备注
   */
  private String remark;

  /**
   * 租户账号状态
   */
  private AccountStatus accountStatus;

  /**
   * 版本
   */
  private VersionConfig versionConfig;

  /** 地区 */
  private Area area;

  /**
   * 营业时间
   */
  private String businessTime;

  /**
   * 描述
   */
  private String description;

  /**
   * 拥有者姓名
   */
  private String ownerName;

  /**
   * 好评率
   */
  private Integer praiseRate;

  /**
   * 店铺logo
   */
  private String storeLogo;
  /**
   * 纬度
   */
  private BigDecimal latitude;

  /**
   * 经度
   */
  private BigDecimal longitude;

  /**
   * 营业执照
   */
  private String license;

  /**
   * 门店照片
   */
  private String photo;

  /**
   * 商铺二维码
   */

  private byte[] qrImage;
  /**
   * 店铺汽车服务
   */
  private Set<CarService> carServices = new HashSet<CarService>();

  /**
   * 是否已开管理员账号
   */
  private Boolean isHaveAccount;

  /**
   * 所属分销商
   */
  private Distributor distributor;

  private Set<TenantImage> tenantImages = new HashSet<TenantImage>();


  /**
   * 评价次数
   */
  private Integer rateCounts = 0;

  /**
   * 租户系统展示名称
   */
  private String systemName;
  
  public Integer getRateCounts() {
    return rateCounts;
  }

  public void setRateCounts(Integer rateCounts) {
    this.rateCounts = rateCounts;
  }

  @OneToMany(mappedBy = "tenantInfo")
  public Set<CarService> getCarServices() {
    return carServices;
  }

  public void setCarServices(Set<CarService> carServices) {
    this.carServices = carServices;
  }

  @Column(length = 50)
  public String getBusinessTime() {
    return businessTime;
  }

  public void setBusinessTime(String businessTime) {
    this.businessTime = businessTime;
  }

  @Lob
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(length = 50)
  public String getOwnerName() {
    return ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }

  public Integer getPraiseRate() {
    return praiseRate;
  }

  public void setPraiseRate(Integer praiseRate) {
    this.praiseRate = praiseRate;
  }

  public String getStoreLogo() {
    return storeLogo;
  }

  public void setStoreLogo(String storeLogo) {
    this.storeLogo = storeLogo;
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

  @Column(length = 20)
  public String getOrgCode() {
    return orgCode;
  }

  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
  }

  @Column(length = 50)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Column(length = 30)
  public String getContactPhone() {
    return contactPhone;
  }

  public void setContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
  }

  @Column(length = 80)
  public String getTenantName() {
    return tenantName;
  }

  public void setTenantName(String tenantName) {
    this.tenantName = tenantName;
  }

  @Column(length = 100)
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Column(length = 15)
  public String getContactPerson() {
    return contactPerson;
  }

  public void setContactPerson(String contactPerson) {
    this.contactPerson = contactPerson;
  }

  @Column(length = 20)
  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  @Column(length = 60)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public AccountStatus getAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(AccountStatus accountStatus) {
    this.accountStatus = accountStatus;
  }

  @ManyToOne(cascade = CascadeType.ALL)
  public VersionConfig getVersionConfig() {
    return versionConfig;
  }

  public void setVersionConfig(VersionConfig versionConfig) {
    this.versionConfig = versionConfig;
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

  @Lob
  @Column(length = 100000, columnDefinition = "blob")
  public byte[] getQrImage() {
    return qrImage;
  }

  public void setQrImage(byte[] qrImage) {
    this.qrImage = qrImage;
  }

  public Boolean getIsHaveAccount() {
    return isHaveAccount;
  }

  public void setIsHaveAccount(Boolean isHaveAccount) {
    this.isHaveAccount = isHaveAccount;
  }

  @ManyToOne
  public Distributor getDistributor() {
    return distributor;
  }

  public void setDistributor(Distributor distributor) {
    this.distributor = distributor;
  }

  @OneToMany(mappedBy = "tenantInfo")
  @OrderBy("createDate asc")
  public Set<TenantImage> getTenantImages() {
    return tenantImages;
  }

  public void setTenantImages(Set<TenantImage> tenantImages) {
    this.tenantImages = tenantImages;
  }

  @Column(length=6)
  public String getSystemName ()
  {
    return systemName;
  }

  public void setSystemName (String systemName)
  {
    this.systemName = systemName;
  }

  

}
