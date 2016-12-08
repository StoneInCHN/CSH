package com.csh.json.response;

import java.util.Date;

public class VehicleDailyReport {

  /**
   * 总里程
   */
  private Float totalMileAge;
  
  /**
   * 今日里程
   */
  private Float mileAge;
  
  private Integer runningTime;

  /**
   * 平均油耗
   */
  private Float averageFuelConsumption;

  /**
   * 当日油耗
   */
  private Float fuelConsumption;

  /**
   * 当日油费
   */
  private Double cost;

  /**
   * 平均速度
   */
  private Float averageSpeed;
  
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

  /**
   * 疲劳驾驶次数
   */
  private Integer fatiguedrivingcount;
  
  /**
   * 驾驶得分
   */
  private Integer score;
  /**
   * OBD 故障码
   */
  private String faultcode;
  
  private Boolean isNeedToAddInitMileAge;
  
  /**
   * 时间
   */
  private Date reportDate;
  
  private String deviceId;
  


  public Float getAverageFuelConsumption() {
    return averageFuelConsumption;
  }


  public void setAverageFuelConsumption(Float averageFuelConsumption) {
    this.averageFuelConsumption = averageFuelConsumption;
  }


  public Float getFuelConsumption() {
    return fuelConsumption;
  }


  public void setFuelConsumption(Float fuelConsumption) {
    this.fuelConsumption = fuelConsumption;
  }


  public Double getCost() {
    return cost;
  }


  public void setCost(Double cost) {
    this.cost = cost;
  }


  public Float getAverageSpeed() {
    return averageSpeed;
  }


  public void setAverageSpeed(Float averageSpeed) {
    this.averageSpeed = averageSpeed;
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


  public Date getReportDate ()
  {
    return reportDate;
  }


  public void setReportDate (Date reportDate)
  {
    this.reportDate = reportDate;
  }


  public String getDeviceId ()
  {
    return deviceId;
  }


  public void setDeviceId (String deviceId)
  {
    this.deviceId = deviceId;
  }


  public Integer getFatiguedrivingcount ()
  {
    return fatiguedrivingcount;
  }


  public void setFatiguedrivingcount (Integer fatiguedrivingcount)
  {
    this.fatiguedrivingcount = fatiguedrivingcount;
  }


  public String getFaultcode ()
  {
    return faultcode;
  }


  public void setFaultcode (String faultcode)
  {
    this.faultcode = faultcode;
  }


  public Boolean getIsNeedToAddInitMileAge ()
  {
    return isNeedToAddInitMileAge;
  }


  public void setIsNeedToAddInitMileAge (Boolean isNeedToAddInitMileAge)
  {
    this.isNeedToAddInitMileAge = isNeedToAddInitMileAge;
  }


  public Float getTotalMileAge ()
  {
    return totalMileAge;
  }


  public void setTotalMileAge (Float totalMileAge)
  {
    this.totalMileAge = totalMileAge;
  }


  public Float getMileAge ()
  {
    return mileAge;
  }


  public void setMileAge (Float mileAge)
  {
    this.mileAge = mileAge;
  }


  public Integer getRunningTime ()
  {
    return runningTime;
  }


  public void setRunningTime (Integer runningTime)
  {
    this.runningTime = runningTime;
  }


  public Integer getScore ()
  {
    return score;
  }

  public void setScore (Integer score)
  {
    this.score = score;
  }
  
}
