package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class TenantInfoRequest extends BaseRequest {

  private Long tenantId;
  /**
   * 纬度
   */
  private String latitude;

  /**
   * 经度
   */
  private String longitude;

  /**
   * 服务类别ID
   */
  private Long serviceCategoryId;


  public Long getTenantId() {
    return tenantId;
  }

  public void setTenantId(Long tenantId) {
    this.tenantId = tenantId;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public Long getServiceCategoryId() {
    return serviceCategoryId;
  }

  public void setServiceCategoryId(Long serviceCategoryId) {
    this.serviceCategoryId = serviceCategoryId;
  }

}
