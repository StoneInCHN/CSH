package com.csh.json.response;

import java.util.Date;

import com.csh.entity.commonenum.CommonEnum.AccStatus;

/**
 * 返回给租户的车辆实时状态数据,用户车辆查询页面显示
 * 
 * @author shijun
 *
 */
public class VehicleStatus {

  /**
   * 设备id
   */
  private Long deviceId;

  /**
   * 主键id
   */
  private String rowId;

  /**
   * 里程
   */
  private Float mileage;

  /**
   * 是否在线
   */
  private Boolean online;

  /**
   * 剩余油量
   */
  private Float remaininggas;

  /**
   * 电瓶电压
   */
  private Float bv;
  /**
   * gps里程
   */
  private Float gpsMileage;
  /**
   * 纬度
   */
  private Float lat;
  /**
   * 经度
   */
  private Float Lon;

  /**
   * 创建时间
   */
  private Date createtime;

  /**
   * 车辆故障码
   */
  private String faultcode;
  /**
   * acc 状态
   */
  private AccStatus accStatus;
  /**
   * 方位角
   */
  private String azimuth;

  /**
   * 实时速度
   */
  private Float speed;


  public Float getSpeed() {
    return speed;
  }

  public void setSpeed(Float speed) {
    this.speed = speed;
  }

  public Long getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(Long deviceId) {
    this.deviceId = deviceId;
  }

  public String getRowId() {
    return rowId;
  }

  public void setRowId(String rowId) {
    this.rowId = rowId;
  }

  public Float getMileage() {
    return mileage;
  }

  public void setMileage(Float mileage) {
    this.mileage = mileage;
  }

  public Boolean getOnline() {
    return online;
  }

  public void setOnline(Boolean online) {
    this.online = online;
  }

  public Float getRemaininggas() {
    return remaininggas;
  }

  public void setRemaininggas(Float remaininggas) {
    this.remaininggas = remaininggas;
  }

  public Float getBv() {
    return bv;
  }

  public void setBv(Float bv) {
    this.bv = bv;
  }

  public Float getGpsMileage() {
    return gpsMileage == null ? 0 : gpsMileage;
  }

  public void setGpsMileage(Float gpsMileage) {
    this.gpsMileage = gpsMileage;
  }

  public Float getLat() {
    return lat;
  }

  public void setLat(Float lat) {
    this.lat = lat;
  }

  public Float getLon() {
    return Lon;
  }

  public void setLon(Float lon) {
    Lon = lon;
  }

  public Date getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Date createtime) {
    this.createtime = createtime;
  }

  public String getFaultcode() {
    return faultcode;
  }

  public void setFaultcode(String faultcode) {
    this.faultcode = faultcode;
  }

  public AccStatus getAccStatus() {
    return accStatus;
  }

  public void setAccStatus(AccStatus accStatus) {
    this.accStatus = accStatus;
  }

  public String getAzimuth() {
    return azimuth;
  }

  public void setAzimuth(String azimuth) {
    this.azimuth = azimuth;
  }

}
