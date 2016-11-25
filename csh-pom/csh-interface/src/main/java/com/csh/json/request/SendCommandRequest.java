package com.csh.json.request;

import com.csh.json.base.BaseRequest;


public class SendCommandRequest extends BaseRequest {

  /**
   * 设备id(必填)
   */
  private String deviceId;

  /**
   * 命令类型(必填)
   */
  private CommandType commandType;

  /**
   * 命令参数(可为null)
   */
  private String parameter;

  /**
   * 命令类型枚举
   */
  public enum CommandType {
    /** 标定初始里程，参数 parameter为设定的初始里程，如果不传参数，代码逻辑默认设定parameter=0 */
    sm,
    /** 车辆设防 ，无参数 */
    arm,
    /** 车辆撤防 ，无参数 */
    disarm,
    /** nobd模式 ,参数 parameter=1 为nobd模式，parameter=0 为obd模式 */
    nobd,
    /** 终端(硬件)重启 ，无参数 */
    rhw
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public CommandType getCommandType() {
    return commandType;
  }

  public void setCommandType(CommandType commandType) {
    this.commandType = commandType;
  }

  public String getParameter() {
    return parameter;
  }

  public void setParameter(String parameter) {
    this.parameter = parameter;
  }

}
