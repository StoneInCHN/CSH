package com.csh.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

import com.csh.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "csh_car_washing_coupon_enduser")
@Indexed(index = "carWashingCouponEndUser")
@SequenceGenerator(name = "sequenceGenerator",
    sequenceName = "csh_car_washing_coupon_enduser_sequence")
public class CarWashingCouponEndUser extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 2109525481569005538L;


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
   * 洗车劵
   */
  private CarWashingCoupon carWashingCoupon;

  /**
   * 总数量
   */
  private Integer counts;

  /**
   * 剩余数量
   */
  private Integer remainNum;

  @JsonProperty
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @JsonProperty
  public Boolean getIsUsed() {
    return isUsed;
  }

  public void setIsUsed(Boolean isUsed) {
    this.isUsed = isUsed;
  }

  @ManyToOne
  @JsonProperty
  public EndUser getEndUser() {
    return endUser;
  }

  public void setEndUser(EndUser endUser) {
    this.endUser = endUser;
  }

  @ManyToOne
  public CarWashingCoupon getCarWashingCoupon() {
    return carWashingCoupon;
  }

  public void setCarWashingCoupon(CarWashingCoupon carWashingCoupon) {
    this.carWashingCoupon = carWashingCoupon;
  }

  @JsonProperty
  public Integer getCounts() {
    return counts;
  }

  public void setCounts(Integer counts) {
    this.counts = counts;
  }

  @JsonProperty
  public Integer getRemainNum() {
    return remainNum;
  }

  public void setRemainNum(Integer remainNum) {
    this.remainNum = remainNum;
  }

}
