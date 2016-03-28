package com.csh.json.request;

import com.csh.entity.commonenum.CommonEnum.SmsTokenType;
import com.csh.entity.commonenum.CommonEnum.TokenSendType;
import com.csh.json.base.BaseRequest;

public class SmsTokenRequest extends BaseRequest{
      
    /**
     * 手机号
     */
    private String mobileNo;
    /**
     * 验证码类型
     */
    private SmsTokenType tokenType;

    /**
     * 发送方式
     */
    private TokenSendType sendType;

    
    public SmsTokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(SmsTokenType tokenType) {
		this.tokenType = tokenType;
	}

	public TokenSendType getSendType() {
		return sendType;
	}

	public void setSendType(TokenSendType sendType) {
		this.sendType = sendType;
	}

	public String getMobileNo() {
      return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
      this.mobileNo = mobileNo;
    }
    
    
}
