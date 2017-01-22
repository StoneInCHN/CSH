package com.csh.json.response;

import java.util.ArrayList;
import java.util.List;

public class VehicleReportList {

  private String deviceId;
  
  private List<VehicleReport> list = new ArrayList<VehicleReport>();

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public List<VehicleReport> getList() {
    return list;
  }

  public void setList(List<VehicleReport> list) {
    this.list = list;
  }
  
}
