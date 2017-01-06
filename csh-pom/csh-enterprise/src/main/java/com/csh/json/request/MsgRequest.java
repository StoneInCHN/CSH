package com.csh.json.request;



public class MsgRequest {

  /**
   * 消息内容
   */
  private String msgContent;

  /**
   * 设备号
   */
  private String deviceNo;

  /**
   * 设备类型，
   */
  private String msgType;

  /**
   * 经度
   */
  private String lon;

  /**
   * 纬度
   */
  private String lat;

  public String getLon() {
    return lon;
  }

  public void setLon(String lon) {
    this.lon = lon;
  }

  public String getLat() {
    return lat;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  public String getMsgContent() {
    return msgContent;
  }

  public void setMsgContent(String msgContent) {
    this.msgContent = msgContent;
  }

  public String getDeviceNo() {
    return deviceNo;
  }

  public void setDeviceNo(String deviceNo) {
    this.deviceNo = deviceNo;
  }

  public String getMsgType() {
    return msgType;
  }

  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

}
