package com.csh.json.request;

import com.csh.entity.commonenum.CommonEnum.AppPlatform;
import com.csh.json.base.BaseRequest;

public class JpushRequest extends BaseRequest {

  /**
   * 极光推送手机注册ID
   */
  private String regId;

  /**
   * 版本名称
   */
  private String versionName;

  /**
   * 版本序列号
   */
  private Integer versionCode;

  /**
   * 手机平台
   */
  private AppPlatform appPlatform;

  /**
   * 宽（像素）
   */
  private Integer piWidth;
  /**
   * 高（像素）
   */
  private Integer piHeight;


  public Integer getPiWidth() {
    return piWidth;
  }

  public void setPiWidth(Integer piWidth) {
    this.piWidth = piWidth;
  }

  public Integer getPiHeight() {
    return piHeight;
  }

  public void setPiHeight(Integer piHeight) {
    this.piHeight = piHeight;
  }

  public AppPlatform getAppPlatform() {
    return appPlatform;
  }

  public void setAppPlatform(AppPlatform appPlatform) {
    this.appPlatform = appPlatform;
  }

  public String getVersionName() {
    return versionName;
  }

  public void setVersionName(String versionName) {
    this.versionName = versionName;
  }

  public Integer getVersionCode() {
    return versionCode;
  }

  public void setVersionCode(Integer versionCode) {
    this.versionCode = versionCode;
  }

  public String getRegId() {
    return regId;
  }

  public void setRegId(String regId) {
    this.regId = regId;
  }

}
