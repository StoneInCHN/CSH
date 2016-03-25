package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class MsgRequest extends BaseRequest {

  private Long msgId;

  private Long[] msgIds;


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
