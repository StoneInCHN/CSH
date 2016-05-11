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
