package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class CouponRequest extends BaseRequest {

  /**
   * 是否过期
   */
  private Boolean isOverDue;

  public Boolean getIsOverDue() {
    return isOverDue;
  }

  public void setIsOverDue(Boolean isOverDue) {
    this.isOverDue = isOverDue;
  }

}
