package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class WarnMsgRequest extends BaseRequest {

  private Long msgId;
  /**
   * obd消息类型 1->车辆点火 2->车辆熄火 3->警告消息
   */
  private String msgType;

  /**
   * 消息内容
   */
  private String msgContent;

  /**
   * 设备号
   */
  private String deviceNo;


  public Long getMsgId() {
    return msgId;
  }

  public void setMsgId(Long msgId) {
    this.msgId = msgId;
  }

  public String getMsgType() {
    return msgType;
  }

  public void setMsgType(String msgType) {
    this.msgType = msgType;
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

}
