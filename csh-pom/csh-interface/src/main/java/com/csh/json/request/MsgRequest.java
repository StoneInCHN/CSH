package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class MsgRequest extends BaseRequest {

  private Long msgId;

  /**
   * 消息内容
   */
  private String msgContent;

  /**
   * 设备号
   */
  private String deviceNo;

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
