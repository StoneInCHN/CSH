package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class VehicleRequest extends BaseRequest {

  /**
   * 是否在线
   */
  private boolean online;

  /**
   * 车牌号
   */
  private String plate;

  /**
   * 设备号
   */
  private String deviceNo;

  /**
   * 手机号
   */
  private String mobileNum;

  public boolean isOnline() {
    return online;
  }

  public void setOnline(boolean online) {
    this.online = online;
  }

  public String getPlate() {
    return plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

  public String getDeviceNo() {
    return deviceNo;
  }

  public void setDeviceNo(String deviceNo) {
    this.deviceNo = deviceNo;
  }

  public String getMobileNum() {
    return mobileNum;
  }

  public void setMobileNum(String mobileNum) {
    this.mobileNum = mobileNum;
  }

}
