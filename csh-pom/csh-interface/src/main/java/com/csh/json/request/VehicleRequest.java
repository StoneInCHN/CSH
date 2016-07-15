package com.csh.json.request;

import java.util.Date;

import com.csh.json.base.BaseRequest;

public class VehicleRequest extends BaseRequest {

  private Long vehicleId;

  /**
   * 设备号
   */
  private String deviceNo;

  /**
   * 品牌ID
   */
  private Long brandId;
  /**
   * 车系ID
   */
  private Long vehicleLineId;

  /**
   * 车型ID
   */
  private Long brandDetailId;

  /**
   * 车牌号
   */
  private String plateNo;

  /**
   * 车架号
   */
  private String vehicleNo;

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
   * 是否是默认车辆
   */
  private Boolean isDefault;

  /**
   * 搜索日期
   */
  private Date searchDate;

  /**
   * 外部系统调用时所需key
   */
  private String apiKey;

  /**
   * 租户orgCode
   */
  private String orgCode;

  public String getOrgCode() {
    return orgCode;
  }

  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
  }


  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public Date getSearchDate() {
    return searchDate;
  }

  public void setSearchDate(Date searchDate) {
    this.searchDate = searchDate;
  }

  public Boolean getIsDefault() {
    return isDefault;
  }

  public void setIsDefault(Boolean isDefault) {
    this.isDefault = isDefault;
  }

  public Long getBrandId() {
    return brandId;
  }

  public void setBrandId(Long brandId) {
    this.brandId = brandId;
  }

  public String getDeviceNo() {
    return deviceNo;
  }

  public void setDeviceNo(String deviceNo) {
    this.deviceNo = deviceNo;
  }

  public Long getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(Long vehicleId) {
    this.vehicleId = vehicleId;
  }

  public Long getVehicleLineId() {
    return vehicleLineId;
  }

  public void setVehicleLineId(Long vehicleLineId) {
    this.vehicleLineId = vehicleLineId;
  }

  public Long getBrandDetailId() {
    return brandDetailId;
  }

  public void setBrandDetailId(Long brandDetailId) {
    this.brandDetailId = brandDetailId;
  }

  public String getPlateNo() {
    return plateNo;
  }

  public void setPlateNo(String plateNo) {
    this.plateNo = plateNo;
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

  public String getVehicleNo() {
    return vehicleNo;
  }

  public void setVehicleNo(String vehicleNo) {
    this.vehicleNo = vehicleNo;
  }

}
