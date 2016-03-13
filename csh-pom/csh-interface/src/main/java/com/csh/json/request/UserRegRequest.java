package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class UserRegRequest extends BaseRequest{

  /**
   * 密码
   */
  private String password;
  
  /**
   * 确认密码
   */
  private String password_confirm;
  
  /**
   * 手机验证码
   */
  private String smsToken;
  
  /**
   * app初始化个推SDK后生成的client id
   */
  private String appClientId;
  
  
    public String getPassword_confirm() {
	  return password_confirm;
    }

	public void setPassword_confirm(String password_confirm) {
		this.password_confirm = password_confirm;
	}
	
	public String getAppClientId() {
	    return appClientId;
	}

  public void setAppClientId(String appClientId) {
    this.appClientId = appClientId;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSmsToken() {
    return smsToken;
  }

  public void setSmsToken(String smsToken) {
    this.smsToken = smsToken;
  }

 

}
