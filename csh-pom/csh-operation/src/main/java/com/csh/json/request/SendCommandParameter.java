package com.csh.json.request;
/**
 * 发送指令的参数
 *
 */
public class SendCommandParameter {
  /**
   * 标定初始里程的参数
   */
  private String parameterSM;
  /**
   * 切换nobd模式的参数
   */
  private String parameterNOBD;
  
  public String getParameterSM() {
    return parameterSM;
  }
  public void setParameterSM(String parameterSM) {
    this.parameterSM = parameterSM;
  }
  public String getParameterNOBD() {
    return parameterNOBD;
  }
  public void setParameterNOBD(String parameterNOBD) {
    this.parameterNOBD = parameterNOBD;
  }
  
  
}
