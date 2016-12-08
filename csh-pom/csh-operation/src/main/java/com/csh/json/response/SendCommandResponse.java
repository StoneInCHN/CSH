package com.csh.json.response;

public class SendCommandResponse {
  /**
   * 设备id
   */
  private String deviceId;
  /**
   * 返回结果 (成功:success 失败:error)
   */
  private String result;
  /**
   * 结果详情
   */
  private String resultMsg;
  
  public String getDeviceId() {
    return deviceId;
  }
  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }
  public String getResult() {
    return result;
  }
  public void setResult(String result) {
    this.result = result;
  }
  public String getResultMsg() {
    return resultMsg;
  }
  public void setResultMsg(String resultMsg) {
    this.resultMsg = resultMsg;
  }
  
  
}
