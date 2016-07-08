package com.csh.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.csh.entity.base.BaseEntity;
import com.csh.lucene.LowCaseBridgeImpl;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the csh_vehicle database table.
 * 
 */
@Indexed(index = "vehicle")
@Entity
@Table(name = "csh_vehicle")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_vehicle_sequence")
public class Vehicle extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /**
   * 代理商
   * 
   * private String agent;
   */

  /**
   * 车辆型号
   */
  private VehicleBrandDetail vehicleBrandDetail;

  /**
   * 车架号
   */
  private String vehicleNo;
  /**
   * 颜色
   */
  private String color;

  /**
   * 设备信息
   */
  private DeviceInfo device;

  /**
   * 行驶证
   */
  private DrivingLicense drivingLicense;

  /**
   * 是否默认显示
   */
  private Boolean isDefault;

  /**
   * 车牌号
   */
  private String plate;


  private String vin;

  /**
   * 仪表盘里程
   */
  private Float dashboardMileage;

  /**
   * 仪表盘电压
   */
  private Float dashboardBV;

  /**
   * 仪表盘油量
   */
  private Float dashboradOil;

  /**
   * 生产日期
   */
  private Date produceDate;

  /**
   * 上牌日期
   */
  private Date plateDate;
  private EndUser endUser;

  private Long tenantID;

  private Set<VehicleMaintain> vehicleMaintain = new HashSet<VehicleMaintain>();

  /**
   * 设备编号，冗余字段，方便查询
   */
  private String deviceNo;

  /**
   * 品牌图标
   */
  private String brandIcon;
  /**
   * 车辆车型全称
   */
  private String vehicleFullBrand;

  /**
   * 交强险到期时间
   */
  private String trafficInsuranceExpiration;
  /**
   * 商业险到期时间
   */
  private String commercialInsuranceExpiration;

  /**
   * 下次年检时间
   */
  private String nextAnnualInspection;

  /**
   * 行驶里程
   */
  private Long driveMileage;
  /**
   * 上次保养里程
   */
  private Long lastMaintainMileage;

  /**
   * 警告信息
   */
  private String wainingInfo;
  /**
   * 绑定的设备是否在线
   */
  private Boolean isOnline;
  /**
   * 初始设备里程
   */
  private Long initMileage;

  /**
   * 是否获取到红包
   */
  private Boolean isGetCoupon;

  /**
   * 商家名字
   */
  private String tenantName;
  /**
   * 分销商名字
   */
  private String distributorName;

  @Transient
  public Boolean getIsGetCoupon() {
    return isGetCoupon;
  }

  public void setIsGetCoupon(Boolean isGetCoupon) {
    this.isGetCoupon = isGetCoupon;
  }

  @Column(length = 200)
  public String getBrandIcon() {
    return brandIcon;
  }

  public void setBrandIcon(String brandIcon) {
    this.brandIcon = brandIcon;
  }

  public String getTrafficInsuranceExpiration() {
    return trafficInsuranceExpiration;
  }

  public void setTrafficInsuranceExpiration(String trafficInsuranceExpiration) {
    this.trafficInsuranceExpiration = trafficInsuranceExpiration;
  }

  public String getCommercialInsuranceExpiration() {
    return commercialInsuranceExpiration;
  }

  public void setCommercialInsuranceExpiration(String commercialInsuranceExpiration) {
    this.commercialInsuranceExpiration = commercialInsuranceExpiration;
  }

  public String getNextAnnualInspection() {
    return nextAnnualInspection;
  }

  public void setNextAnnualInspection(String nextAnnualInspection) {
    this.nextAnnualInspection = nextAnnualInspection;
  }

  public Long getDriveMileage() {
    return driveMileage;
  }

  public void setDriveMileage(Long driveMileage) {
    this.driveMileage = driveMileage;
  }

  public Long getLastMaintainMileage() {
    return lastMaintainMileage;
  }

  public void setLastMaintainMileage(Long lastMaintainMileage) {
    this.lastMaintainMileage = lastMaintainMileage;
  }

  @Transient
  public String getVehicleFullBrand() {
    VehicleLine vl = vehicleBrandDetail.getVehicleLine();
    if (vl == null) {
      return null;
    }
    vehicleFullBrand = vl.getName();
    if (vl.getParent() != null) {
      VehicleLine vl_parent = vl.getParent();
      vehicleFullBrand = vl_parent.getName() + "-" + vehicleFullBrand;
    }
    return vehicleFullBrand;
  }

  public void setVehicleFullBrand(String vehicleFullBrand) {
    this.vehicleFullBrand = vehicleFullBrand;
  }

  @JsonProperty
  @ManyToOne(fetch = FetchType.EAGER)
  @IndexedEmbedded
  public VehicleBrandDetail getVehicleBrandDetail() {
    return vehicleBrandDetail;
  }

  public void setVehicleBrandDetail(VehicleBrandDetail vehicleBrandDetail) {
    this.vehicleBrandDetail = vehicleBrandDetail;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @OneToOne(mappedBy = "vehicle", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  public DeviceInfo getDevice() {
    return device;
  }

  public void setDevice(DeviceInfo device) {
    this.device = device;
  }

  @OneToOne(mappedBy = "vehicle")
  public DrivingLicense getDrivingLicense() {
    return drivingLicense;
  }

  public void setDrivingLicense(DrivingLicense drivingLicense) {
    this.drivingLicense = drivingLicense;
  }

  public Boolean getIsDefault() {
    return isDefault;
  }

  public void setIsDefault(Boolean isDefault) {
    this.isDefault = isDefault;
  }

  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.NO)
  @FieldBridge(impl = LowCaseBridgeImpl.class)
  public String getPlate() {
    return plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

  @JsonProperty
  @ManyToOne
  @IndexedEmbedded
  public EndUser getEndUser() {
    return endUser;
  }

  public void setEndUser(EndUser endUser) {
    this.endUser = endUser;
  }

  @Index(name = "index_vehicle_tenantid")
  @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.NO)
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @OneToMany(mappedBy = "vehicle")
  public Set<VehicleMaintain> getVehicleMaintain() {
    return vehicleMaintain;
  }

  public void setVehicleMaintain(Set<VehicleMaintain> vehicleMaintain) {
    this.vehicleMaintain = vehicleMaintain;
  }

  @JsonProperty
  @Transient
  public String getDeviceNo() {
    if (device != null) {
      return device.getDeviceNo();
    } else {
      return null;
    }

  }

  public void setDeviceNo(String deviceNo) {
    this.deviceNo = deviceNo;
  }

  @JsonProperty
  public String getVehicleNo() {
    return vehicleNo;
  }

  public void setVehicleNo(String vehicleNo) {
    this.vehicleNo = vehicleNo;
  }

  @JsonProperty
  public Float getDashboardMileage() {
    return dashboardMileage;
  }

  public void setDashboardMileage(Float dashboardMileage) {
    this.dashboardMileage = dashboardMileage;
  }

  @JsonProperty
  public Float getDashboardBV() {
    return dashboardBV;
  }

  public void setDashboardBV(Float dashboardBV) {
    this.dashboardBV = dashboardBV;
  }

  @JsonProperty
  public Float getDashboradOil() {
    return dashboradOil;
  }

  public void setDashboradOil(Float dashboradOil) {
    this.dashboradOil = dashboradOil;
  }

  public Date getProduceDate() {
    return produceDate;
  }

  public void setProduceDate(Date produceDate) {
    this.produceDate = produceDate;
  }


  public Date getPlateDate() {
    return plateDate;
  }

  public void setPlateDate(Date plateDate) {
    this.plateDate = plateDate;
  }

  @Transient
  @JsonProperty
  public Boolean getIsOnline() {
    return isOnline;
  }

  public void setIsOnline(Boolean isOnline) {
    this.isOnline = isOnline;
  }

  @JsonProperty
  public String getWainingInfo() {
    return wainingInfo;
  }

  public void setWainingInfo(String wainingInfo) {
    this.wainingInfo = wainingInfo;
  }

  public Long getInitMileage() {
    return initMileage;
  }

  public void setInitMileage(Long initMileage) {
    this.initMileage = initMileage;
  }

  @Transient
  public String getTenantName() {


    return tenantName;
  }

  public void setTenantName(String tenantName) {
    this.tenantName = tenantName;
  }

  @Transient
  public String getDistributorName() {
    return distributorName;
  }

  public void setDistributorName(String distributorName) {
    this.distributorName = distributorName;
  }

}
