package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class MsgRequest extends BaseRequest {

  private Long msgId;

  /**
   * obd消息类型 1->车辆点火 2->车辆熄火 3->警告消息
   */
  private String msgType;

  /**
   * 熄火时行驶距离
   */
  private String gpsMileage;

  /**
   * 熄火时行驶时间
   */
  private String travelTime;
  /**
   * 消息内容
   */
  private String msgContent;

  /**
   * 设备号
   */
  private String deviceNo;


  public String getMsgType() {
    return msgType;
  }

  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public String getGpsMileage() {
    return gpsMileage;
  }

  public void setGpsMileage(String gpsMileage) {
    this.gpsMileage = gpsMileage;
  }

  public String getTravelTime() {
    return travelTime;
  }

  public void setTravelTime(String travelTime) {
    this.travelTime = travelTime;
  }

  private Long[] msgIds;

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

  public Long[] getMsgIds() {
    return msgIds;
  }

  public void setMsgIds(Long[] msgIds) {
    this.msgIds = msgIds;
  }

  public Long getMsgId() {
    return msgId;
  }

  public void setMsgId(Long msgId) {
    this.msgId = msgId;
  }


}
