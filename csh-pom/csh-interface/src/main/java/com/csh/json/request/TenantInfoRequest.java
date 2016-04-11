package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class TenantInfoRequest extends BaseRequest {

  /**
   * 租户ID
   */
  private Long tenantId;

  /**
   * 用户对租户评分（1-5星）
   */
  private Integer score;

  /**
   * 评价对应的消费记录
   */
  private Long recordId;
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


  public Long getRecordId() {
    return recordId;
  }

  public void setRecordId(Long recordId) {
    this.recordId = recordId;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

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
