package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class MsgRequest extends BaseRequest{
    
    private Long msgId;

    public Long getMsgId() {
      return msgId;
    }

    public void setMsgId(Long msgId) {
      this.msgId = msgId;
    }
    
    
}
