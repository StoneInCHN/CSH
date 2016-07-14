package com.csh.json.response;

import java.util.Date;

/**
 * 实时车况
 * 
 * @author shijun
 *
 */
public class RealTimeCarCondition {

  /**
   * 里程
   */
  private Float mileAge;

  /**
   * 运行时间
   */
  private Integer engineRuntime;

  /**
   * 平均油耗
   */
  private Float averageOil;

  /**
   * 速度
   */
  private Float speed;
  
  /**
   * 经度
   */
  private Double lon;
  
  /**
   * 纬度
   */
  private Double lat;
  
  /**
   * 方位角
   */
  private Float azimuth;
  
  /**
   * 是否启动 1启动  0熄火
   */
  private String acc;
  
  /**
   * 创建时间
   */
  private Date createtime;
  
  private Boolean isNeedToAddInitMileAge;
  public Float getMileAge() {
    return mileAge;
  }

  public void setMileAge(Float mileAge) {
    this.mileAge = mileAge;
  }

  public Integer getEngineRuntime() {
    return engineRuntime;
  }

  public void setEngineRuntime(Integer engineRuntime) {
    this.engineRuntime = engineRuntime;
  }

  public Float getAverageOil() {
    return averageOil;
  }

  public void setAverageOil(Float averageOil) {
    this.averageOil = averageOil;
  }

  public Float getSpeed() {
    return speed;
  }

  public void setSpeed(Float speed) {
    this.speed = speed;
  }

  public Double getLon() {
    return lon;
  }

  public void setLon(Double lon) {
    this.lon = lon;
  }

  public Double getLat() {
    return lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }

  public Float getAzimuth() {
    return azimuth;
  }

  public void setAzimuth(Float azimuth) {
    this.azimuth = azimuth;
  }

  public String getAcc() {
    return acc;
  }

  public void setAcc(String acc) {
    this.acc = acc;
  }

  public Boolean getIsNeedToAddInitMileAge ()
  {
    return isNeedToAddInitMileAge;
  }

  public void setIsNeedToAddInitMileAge (Boolean isNeedToAddInitMileAge)
  {
    this.isNeedToAddInitMileAge = isNeedToAddInitMileAge;
  }

  public Date getCreatetime ()
  {
    return createtime;
  }

  public void setCreatetime (Date createtime)
  {
    this.createtime = createtime;
  }
  
}
