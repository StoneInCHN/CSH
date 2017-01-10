package com.csh.json.request;

import java.util.Date;

import com.csh.entity.commonenum.CommonEnum.OnlineStatus;
import com.csh.json.base.BaseRequest;

/**
 * 车辆信息查询请求 Created by zhangye on 2016/12/25.
 */
public class VehicleRequest extends BaseRequest {


  private Long vehicleId;

  /**
   * 车牌号
   */
  private String plate;

  /**
   * 是否在线
   */
  private OnlineStatus onlineStatus;

  /**
   * 设备号
   */
  private String deviceNo;

  /**
   * 手机号
   */
  private String mobileNum;

  /**
   * 故障码
   */
  private String faultCode;

  /**
   * 录入日期 开始时间
   */
  private Date startTime;

  /**
   * 录入日期 结束时间
   */
  private Date endTime;

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public Long getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(Long vehicleId) {
    this.vehicleId = vehicleId;
  }

  public String getPlate() {
    return plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

  public String getDeviceNo() {
    return deviceNo;
  }

  public void setDeviceNo(String deviceNo) {
    this.deviceNo = deviceNo;
  }

  public String getMobileNum() {
    return mobileNum;
  }

  public void setMobileNum(String mobileNum) {
    this.mobileNum = mobileNum;
  }

  public OnlineStatus getOnlineStatus() {
    return onlineStatus;
  }

  public void setOnlineStatus(OnlineStatus onlineStatus) {
    this.onlineStatus = onlineStatus;
  }

  public String getFaultCode() {
    return faultCode;
  }

  public void setFaultCode(String faultCode) {
    this.faultCode = faultCode;
  }

  @Override
  public String toString() {
    return "VehicleRequest [plate=" + plate + ", startTime=" + startTime + ", endTime=" + endTime
        + ", onlineStatus=" + onlineStatus + ", faultCode=" + faultCode + ", deviceNo=" + deviceNo
        + ", mobileNum=" + mobileNum + "]";
  }

}
