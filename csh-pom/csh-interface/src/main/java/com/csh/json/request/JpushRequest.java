package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class JpushRequest extends BaseRequest {

  /**
   * 极光推送手机注册ID
   */
  private String regId;

  public String getRegId() {
    return regId;
  }

  public void setRegId(String regId) {
    this.regId = regId;
  }



}
