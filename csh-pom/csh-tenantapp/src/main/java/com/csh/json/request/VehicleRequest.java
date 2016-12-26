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
   * 所有者
   */
  private String endUserName;

  /**
   * 车牌号
   */
  private String plate;

  /**
   * 上牌日期
   */
  private Date plateDateStart;

  /**
   * 上牌日期
   */
  private Date plateDateEnd;

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

  
  public Long getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(Long vehicleId) {
    this.vehicleId = vehicleId;
  }

  public String getEndUserName() {
    return endUserName;
  }

  public void setEndUserName(String endUserName) {
    this.endUserName = endUserName;
  }

  public String getPlate() {
    return plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

  public Date getPlateDateStart() {
    return plateDateStart;
  }

  public void setPlateDateStart(Date plateDateStart) {
    this.plateDateStart = plateDateStart;
  }

  public Date getPlateDateEnd() {
    return plateDateEnd;
  }

  public void setPlateDateEnd(Date plateDateEnd) {
    this.plateDateEnd = plateDateEnd;
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

  @Override
  public String toString() {
    return "VehicleRequest [endUserName=" + endUserName + ", plate=" + plate + ", plateDateStart="
        + plateDateStart + ", plateDateEnd=" + plateDateEnd + ", onlineStatus=" + onlineStatus
        + ", deviceNo=" + deviceNo + ", mobileNum=" + mobileNum + "]";
  }

}
