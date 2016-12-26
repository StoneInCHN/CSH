package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class TenantLoginRequest extends BaseRequest {

  /**
   * 登录密码
   */
  private String password;

  /**
   * 修改时新密码
   */
  private String password_new;
  /**
   * 修改时确认密码
   */
  private String password_confirm;

  /**
   * app初始化SDK后生成的client id
   */
  private String appClientId;

  /**
   * 租户组织机构代码
   */
  private String orgCode;


  public String getPassword_new() {
    return password_new;
  }

  public void setPassword_new(String password_new) {
    this.password_new = password_new;
  }

  public String getPassword_confirm() {
    return password_confirm;
  }

  public void setPassword_confirm(String password_confirm) {
    this.password_confirm = password_confirm;
  }

  public String getOrgCode() {
    return orgCode;
  }

  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
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


}
