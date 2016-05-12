package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class CouponRequest extends BaseRequest {

  /**
   * 是否过期
   */
  private Boolean isOverDue;

  /**
   * 优惠券ID
   */
  private Long couponId;

  /**
   * 服务ID
   */
  private Long serviceId;



  public Long getServiceId() {
    return serviceId;
  }

  public void setServiceId(Long serviceId) {
    this.serviceId = serviceId;
  }

  public Long getCouponId() {
    return couponId;
  }

  public void setCouponId(Long couponId) {
    this.couponId = couponId;
  }

  public Boolean getIsOverDue() {
    return isOverDue;
  }

  public void setIsOverDue(Boolean isOverDue) {
    this.isOverDue = isOverDue;
  }

}
