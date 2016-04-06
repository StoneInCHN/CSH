package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class DeviceRequest extends BaseRequest {

  /**
   * 设备ID
   */
  private Long deviceId;

  /**
   * 查询时间
   */
  private String searchDate;

  public Long getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(Long deviceId) {
    this.deviceId = deviceId;
  }

  public String getSearchDate() {
    return searchDate;
  }

  public void setSearchDate(String searchDate) {
    this.searchDate = searchDate;
  }

}
