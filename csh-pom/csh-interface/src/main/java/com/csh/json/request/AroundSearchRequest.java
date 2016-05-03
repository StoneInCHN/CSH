package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class AroundSearchRequest extends BaseRequest {

  /**
   * 关键字
   */
  private String keyWord;

  /**
   * 纬度
   */
  private String latitude;

  /**
   * 经度
   */
  private String longitude;

  public String getKeyWord() {
    return keyWord;
  }

  public void setKeyWord(String keyWord) {
    this.keyWord = keyWord;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }


}
