package com.csh.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.GpsSwitch;

/**
 * gps switch record
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "csh_gps_switch_record")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_gps_switch_record_sequence")
public class GpsSwitchRecord extends BaseEntity {

  private static final long serialVersionUID = 7282926448628904157L;


  /**
   * 设备号
   */
  private String deviceNo;

  /**
   * 操作类型
   */
  private GpsSwitch switchOpr;

  /**
   * 操作用户
   */
  private Long userId;

  /**
   * 操作时间
   */
  private Date oprTime;

  @Column(length = 20)
  public String getDeviceNo() {
    return deviceNo;
  }

  public void setDeviceNo(String deviceNo) {
    this.deviceNo = deviceNo;
  }

  public GpsSwitch getSwitchOpr() {
    return switchOpr;
  }

  public void setSwitchOpr(GpsSwitch switchOpr) {
    this.switchOpr = switchOpr;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Date getOprTime() {
    return oprTime;
  }

  public void setOprTime(Date oprTime) {
    this.oprTime = oprTime;
  }

}
