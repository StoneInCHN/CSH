package com.csh.beans;

import java.io.Serializable;

/**
 * 系统设置
 * 
 */
public class Setting implements Serializable {

  private static final long serialVersionUID = -1478999889661796840L;

  /**
   * 验证码类型
   */
  public enum CaptchaType {

    /** 后台登录 */
    adminLogin,

    /** 后台注册 */
    adminReg,

    /** 找回密码 */
    findPassword,

    /** 重置密码 */
    resetPassword,

    /** 其它 */
    other
  }

  public enum ImageType {
    /**
     * vendor
     */
    vendor
  }


  /** 缓存名称 */
  public static final String CACHE_NAME = "setting";

  /** 缓存Key */
  public static final Integer CACHE_KEY = 0;

  /** 分隔符 */
  private static final String SEPARATOR = ",";


  /** 密码最大长度 */
  private Integer passwordMaxlength;

  /** 密码最小长度 */
  private Integer passwordMinlength;


  /** 用户名手机号正则表达式 */
  private String mobilePattern;

  /** 验证码类型 */
  private CaptchaType[] captchaTypes;



  public Integer getPasswordMaxlength() {
    return passwordMaxlength;
  }

  public void setPasswordMaxlength(Integer passwordMaxlength) {
    this.passwordMaxlength = passwordMaxlength;
  }

  public Integer getPasswordMinlength() {
    return passwordMinlength;
  }

  public void setPasswordMinlength(Integer passwordMinlength) {
    this.passwordMinlength = passwordMinlength;
  }

  public String getMobilePattern() {
    return mobilePattern;
  }

  public void setMobilePattern(String mobilePattern) {
    this.mobilePattern = mobilePattern;
  }

  public CaptchaType[] getCaptchaTypes() {
    return captchaTypes;
  }

  public void setCaptchaTypes(CaptchaType[] captchaTypes) {
    this.captchaTypes = captchaTypes;
  }

}
