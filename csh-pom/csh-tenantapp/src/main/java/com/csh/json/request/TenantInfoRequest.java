package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class TenantInfoRequest extends BaseRequest {
  /**
   * 租户系统展示名称
   */
  private String systemName;
  /**
   * 备注
   */
  private String remark;
  /**
   * 联系电话
   */
  private String contactPhone;
  /**
   * 联系人
   */
  private String contactPerson;
  /** E-mail */
  private String email;
  /**
   * 营业时间
   */
  private String businessTime;
  /**
   * 描述
   */
  private String description;

  public String getSystemName() {
    return systemName;
  }

  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getContactPhone() {
    return contactPhone;
  }

  public void setContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
  }

  public String getContactPerson() {
    return contactPerson;
  }

  public void setContactPerson(String contactPerson) {
    this.contactPerson = contactPerson;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getBusinessTime() {
    return businessTime;
  }

  public void setBusinessTime(String businessTime) {
    this.businessTime = businessTime;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


}
