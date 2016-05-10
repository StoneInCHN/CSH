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
 * 优惠劵
 * 
 * @author Andrea
 *
 */
@Entity
@Table(name = "csh_coupon_enduser")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_red_package_enduser_sequence")
public class CouponEndUser extends BaseEntity {

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
   * 优惠劵
   */
  private Coupon coupon;

  /**
   * 优惠劵对应的消费服务，每个优惠劵只能用于一次消费,一次消费只能使用一个优惠劵
   */
  private CarServiceRecord carServiceRecord;


  @OneToOne(mappedBy = "couponEndUser")
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

  @Column(length = 100)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @ManyToOne
  public Coupon getCoupon() {
    return coupon;
  }

  public void setCoupon(Coupon coupon) {
    this.coupon = coupon;
  }

}
