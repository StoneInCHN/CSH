package com.csh.json.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

public class VehicleReportListRequest {

  private Date fromDate;
  private Date toDate;
  private List<String> deviceIds = new ArrayList<String>();

  public Date getFromDate() {
    return fromDate;
  }
  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }
  public Date getToDate() {
    return toDate;
  }
  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }
  public List<String> getDeviceIds() {
    return deviceIds;
  }
  public void setDeviceIds(List<String> deviceIds) {
    this.deviceIds = deviceIds;
  }}
