package com.csh.json.request;

import java.util.Date;

import com.csh.json.base.BaseRequest;

public class DeviceRequest extends BaseRequest {

  /**
   * 设备ID
   */
  private Long deviceId;

  /**
   * 查询时间
   */
  private Date date;

  public Long getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(Long deviceId) {
    this.deviceId = deviceId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

}
