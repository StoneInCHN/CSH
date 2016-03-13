package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class SmsTokenRequest extends BaseRequest{
      
    /**
     * 手机号
     */
    private String mobileNo;
    /**
     * 验证码类型
     */
    private Integer tokenType;

    public Integer getTokenType() {
      return tokenType;
    }

    public void setTokenType(Integer tokenType) {
      this.tokenType = tokenType;
    }

    public String getMobileNo() {
      return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
      this.mobileNo = mobileNo;
    }
    
    
}
