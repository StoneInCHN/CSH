package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class UserLoginRequest extends BaseRequest{
    
   /**
    * 登录密码
    */
    private String password;
    
    /**
     * app初始化SDK后生成的client id
     */
    private String appClientId;
    
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
    
    

}
