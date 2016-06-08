package com.csh.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Indexed;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.SystemType;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "csh_car_washing_coupon")
@Indexed(index = "carWashingCoupon")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_car_washing_coupon_sequence")
public class CarWashingCoupon extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -5184768890066801695L;

  /**
   * 名称
   */
  private String couponName;


  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 商家名称
   */
  private String tenantName;

  /**
   * 备注
   */
  private String remark;

  private SystemType systemType;

  /**
   * 优惠劵与用户对于关系
   */
  private Set<CarWashingCouponEndUser> carWashingCouponEndUser =
      new HashSet<CarWashingCouponEndUser>();

  @JsonProperty
  public String getCouponName() {
    return couponName;
  }

  public void setCouponName(String couponName) {
    this.couponName = couponName;
  }

  @Index(name = "index_car_washing_coupon_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @JsonProperty
  public String getTenantName() {
    return tenantName;
  }

  public void setTenantName(String tenantName) {
    this.tenantName = tenantName;
  }

  @JsonProperty
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public SystemType getSystemType() {
    return systemType;
  }

  public void setSystemType(SystemType systemType) {
    this.systemType = systemType;
  }

  @OneToMany(mappedBy = "carWashingCoupon", cascade = CascadeType.ALL)
  @JsonProperty
  public Set<CarWashingCouponEndUser> getCarWashingCouponEndUser() {
    return carWashingCouponEndUser;
  }

  public void setCarWashingCouponEndUser(Set<CarWashingCouponEndUser> carWashingCouponEndUser) {
    this.carWashingCouponEndUser = carWashingCouponEndUser;
  }



}
