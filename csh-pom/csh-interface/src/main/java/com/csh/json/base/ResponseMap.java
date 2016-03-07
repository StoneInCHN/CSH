package com.csh.json.base;

import java.util.HashMap;
import java.util.Map;


/**
 * 返回Map 结果
 * @author
 *
 * 
 */
public class ResponseMap extends BaseResponse{

  private Map<String, Object> msg = new HashMap<String, Object>();
  
  public Map<String, Object> getMsg() {
    return msg;
  }

  public void setMsg(Map<String, Object> msg) {
    this.msg = msg;
  }

  
}
