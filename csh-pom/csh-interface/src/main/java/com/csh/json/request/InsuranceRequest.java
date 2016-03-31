package com.csh.json.request;

import java.math.BigDecimal;

import com.csh.json.base.BaseRequest;

public class InsuranceRequest extends BaseRequest {

  /**
   * 投保公司
   */
  private String insuredCompany;

  /**
   * 身份证照片
   */
  private String IDphoto;

  /**
   * 行驶证照片
   */
  private String drivingLicensePhoto;

  /**
   * 驾驶证照片
   */
  private String driverLicensePhoto;

  /**
   * 是否过户
   */
  private Boolean isOwned;

  /**
   * 是否贷款车
   */
  private Boolean isLoaned;

  private BigDecimal price;

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getInsuredCompany() {
    return insuredCompany;
  }

  public void setInsuredCompany(String insuredCompany) {
    this.insuredCompany = insuredCompany;
  }

  public String getIDphoto() {
    return IDphoto;
  }

  public void setIDphoto(String iDphoto) {
    IDphoto = iDphoto;
  }

  public String getDrivingLicensePhoto() {
    return drivingLicensePhoto;
  }

  public void setDrivingLicensePhoto(String drivingLicensePhoto) {
    this.drivingLicensePhoto = drivingLicensePhoto;
  }

  public String getDriverLicensePhoto() {
    return driverLicensePhoto;
  }

  public void setDriverLicensePhoto(String driverLicensePhoto) {
    this.driverLicensePhoto = driverLicensePhoto;
  }

  public Boolean getIsOwned() {
    return isOwned;
  }

  public void setIsOwned(Boolean isOwned) {
    this.isOwned = isOwned;
  }

  public Boolean getIsLoaned() {
    return isLoaned;
  }

  public void setIsLoaned(Boolean isLoaned) {
    this.isLoaned = isLoaned;
  }


}
