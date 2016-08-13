package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class ReceiverAddressRequest extends BaseRequest {

  /**
   * 地区Id
   */
  private Long areaId;

  /**
   * 地区名称
   */
  private String areaName;

  /** 收货人 */
  private String consignee;

  /** 地址 */
  private String address;

  /** 电话 */
  private String phone;

  /** 是否默认 */
  private Boolean isDefault;

  /**
   * 邮编
   */
  private String zipCode;

  /**
   * 地址ID
   */
  private Long receiverId;


  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public Long getReceiverId() {
    return receiverId;
  }

  public void setReceiverId(Long receiverId) {
    this.receiverId = receiverId;
  }

  public Long getAreaId() {
    return areaId;
  }

  public void setAreaId(Long areaId) {
    this.areaId = areaId;
  }

  public String getAreaName() {
    return areaName;
  }

  public void setAreaName(String areaName) {
    this.areaName = areaName;
  }

  public String getConsignee() {
    return consignee;
  }

  public void setConsignee(String consignee) {
    this.consignee = consignee;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Boolean getIsDefault() {
    return isDefault;
  }

  public void setIsDefault(Boolean isDefault) {
    this.isDefault = isDefault;
  }


}
