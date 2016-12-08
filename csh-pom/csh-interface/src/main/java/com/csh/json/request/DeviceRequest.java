package com.csh.json.request;

import com.csh.entity.commonenum.CommonEnum.GpsSwitch;
import com.csh.json.base.BaseRequest;

public class DeviceRequest extends BaseRequest {

  /**
   * 设备号
   */
  private String deviceNo;

  /**
   * 查询时间
   */
  private String searchDate;

  /**
   * 故障码
   */
  private String faultCode;

  /**
   * gps开关操作(TURNON:打开gps定位; TURNOFF:关闭gps定位)
   */
  private GpsSwitch switchOpr;


  public GpsSwitch getSwitchOpr() {
    return switchOpr;
  }

  public void setSwitchOpr(GpsSwitch switchOpr) {
    this.switchOpr = switchOpr;
  }

  public String getFaultCode() {
    return faultCode;
  }

  public void setFaultCode(String faultCode) {
    this.faultCode = faultCode;
  }

  public String getDeviceNo() {
    return deviceNo;
  }

  public void setDeviceNo(String deviceNo) {
    this.deviceNo = deviceNo;
  }

  public String getSearchDate() {
    return searchDate;
  }

  public void setSearchDate(String searchDate) {
    this.searchDate = searchDate;
  }

}
