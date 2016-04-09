package com.csh.json.response;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;

/**
 * 车辆日常报表数据
 * 
 * @author shijun
 *
 */
public class VehicleDailyReport extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -1793383543779831531L;

  /**
   * 设备id
   */
  private String deviceid;

  /**
   * 百公里油耗
   */
  private Float fuelconsumption;

  /**
   * 速度
   */
  private Float speed;

  /**
   * 第一次里程
   */
  private Float firstmileage;

  /**
   * 最后一次里程
   */
  private Float lastmileage;

  /**
   * 运行时间(驾驶时间)
   */
  private Integer runningtime;

  /**
   * 花费
   */
  private Double cost;

  /**
   * 记录创建时间
   */
  private Date createdate;

  /**
   * 记录更新时间
   */
  private Date modifydate;


  /**
   * 第一次剩余油量
   */
  private Float firstremaininggas;

  /**
   * 最后一次剩余油量
   */
  private Float lastremaininggas;

  /**
   * 更新次数
   */
  private Integer updatecount;

  /**
   * 急刹车次数
   */
  private Integer emergencybrakecount;

  /**
   * 急转弯次数
   */
  private Integer suddenturncount;
  
  
  /**
   * 急加速次数
   */
  private Integer rapidlyspeedupcount;

  public String getDeviceid() {
    return deviceid;
  }

  public void setDeviceid(String deviceid) {
    this.deviceid = deviceid;
  }

  public Float getFuelconsumption() {
    return fuelconsumption;
  }

  public void setFuelconsumption(Float fuelconsumption) {
    this.fuelconsumption = fuelconsumption;
  }

  public Float getSpeed() {
    return speed;
  }

  public void setSpeed(Float speed) {
    this.speed = speed;
  }

  public Float getFirstmileage() {
    return firstmileage;
  }

  public void setFirstmileage(Float firstmileage) {
    this.firstmileage = firstmileage;
  }

  public Float getLastmileage() {
    return lastmileage;
  }

  public void setLastmileage(Float lastmileage) {
    this.lastmileage = lastmileage;
  }

  public Integer getRunningtime() {
    return runningtime;
  }

  public void setRunningtime(Integer runningtime) {
    this.runningtime = runningtime;
  }

  public Double getCost() {
    return cost;
  }

  public void setCost(Double cost) {
    this.cost = cost;
  }

  public Date getCreatedate() {
    return createdate;
  }

  public void setCreatedate(Date createdate) {
    this.createdate = createdate;
  }

  public Date getModifydate() {
    return modifydate;
  }

  public void setModifydate(Date modifydate) {
    this.modifydate = modifydate;
  }

  public Float getFirstremaininggas() {
    return firstremaininggas;
  }

  public void setFirstremaininggas(Float firstremaininggas) {
    this.firstremaininggas = firstremaininggas;
  }

  public Float getLastremaininggas() {
    return lastremaininggas;
  }

  public void setLastremaininggas(Float lastremaininggas) {
    this.lastremaininggas = lastremaininggas;
  }

  public Integer getUpdatecount() {
    return updatecount;
  }

  public void setUpdatecount(Integer updatecount) {
    this.updatecount = updatecount;
  }

  public Integer getEmergencybrakecount() {
    return emergencybrakecount;
  }

  public void setEmergencybrakecount(Integer emergencybrakecount) {
    this.emergencybrakecount = emergencybrakecount;
  }

  public Integer getSuddenturncount() {
    return suddenturncount;
  }

  public void setSuddenturncount(Integer suddenturncount) {
    this.suddenturncount = suddenturncount;
  }

  public Integer getRapidlyspeedupcount() {
    return rapidlyspeedupcount;
  }

  public void setRapidlyspeedupcount(Integer rapidlyspeedupcount) {
    this.rapidlyspeedupcount = rapidlyspeedupcount;
  }
}
