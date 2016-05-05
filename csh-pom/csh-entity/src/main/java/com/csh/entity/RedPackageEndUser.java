package com.csh.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;

/**
 * 红包
 * 
 * @author Andrea
 *
 */
@Entity
@Table(name = "csh_red_package_enduser")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_red_package_enduser_sequence")
public class RedPackageEndUser extends BaseEntity {

  private static final long serialVersionUID = 7282926448628904157L;

  /**
   * 是否过期
   */
  private Boolean isOverDue;

  /**
   * 过期时间
   */
  private Date overDueTime;

  /**
   * 说明
   */
  private String remark;

  /**
   * 是否已经使用
   */
  private Boolean isUsed;

  /**
   * 手机用户
   */
  private EndUser endUser;

  /**
   * 红包
   */
  private RedPackage redPackage;

  /**
   * 红包对应的消费服务，每个红包只能用于一次消费,一次消费只能使用一个红包
   */
  private CarServiceRecord carServiceRecord;


  @OneToOne(mappedBy = "redPackageEndUser")
  public CarServiceRecord getCarServiceRecord() {
    return carServiceRecord;
  }

  public void setCarServiceRecord(CarServiceRecord carServiceRecord) {
    this.carServiceRecord = carServiceRecord;
  }

  public Boolean getIsOverDue() {
    return isOverDue;
  }

  public void setIsOverDue(Boolean isOverDue) {
    this.isOverDue = isOverDue;
  }

  public Date getOverDueTime() {
    return overDueTime;
  }

  public void setOverDueTime(Date overDueTime) {
    this.overDueTime = overDueTime;
  }

  public Boolean getIsUsed() {
    return isUsed;
  }

  public void setIsUsed(Boolean isUsed) {
    this.isUsed = isUsed;
  }

  @ManyToOne
  public EndUser getEndUser() {
    return endUser;
  }

  public void setEndUser(EndUser endUser) {
    this.endUser = endUser;
  }

  @ManyToOne
  public RedPackage getRedPackage() {
    return redPackage;
  }

  public void setRedPackage(RedPackage redPackage) {
    this.redPackage = redPackage;
  }

  @Column(length = 100)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


}
