package com.csh.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.CouponOverDueType;
import com.csh.entity.commonenum.CommonEnum.CouponSendType;
import com.csh.entity.commonenum.CommonEnum.CouponType;

/**
 * 优惠劵
 * 
 * @author Andrea
 *
 */
@Entity
@Table(name = "csh_coupon")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_coupon_sequence")
public class Coupon extends BaseEntity {

  private static final long serialVersionUID = 7282926448628904157L;


  /**
   * 优惠劵金额
   */
  private BigDecimal amount = new BigDecimal(0);

  /**
   * 优惠劵类型
   */
  private CouponType type;

  /**
   * 优惠劵过期方式
   */
  private CouponOverDueType overDueType;

  /**
   * 优惠劵发送方式
   */
  private CouponSendType sendType;

  /**
   * 是否启用
   */
  private Boolean isEnabled;

  /**
   * 过期天数
   */
  private Integer overDueDay;

  /**
   * 过期日期
   */
  private Date overDueTime;

  /**
   * 优惠劵领取截止时间
   */
  private Date deadlineTime;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 说明
   */
  private String remark;


  /**
   * 优惠劵与用户对于关系
   */
  private Set<CouponEndUser> couponEndUsers = new HashSet<CouponEndUser>();

  /**
   * 汽车服务
   */
  private Set<CarService> carServices = new HashSet<CarService>();

  /**
   * 优惠券发放数量
   */
  private Integer counts;


  public Integer getCounts() {
    return counts;
  }

  public void setCounts(Integer counts) {
    this.counts = counts;
  }

  public Date getDeadlineTime() {
    return deadlineTime;
  }

  public void setDeadlineTime(Date deadlineTime) {
    this.deadlineTime = deadlineTime;
  }

  @Column(scale = 2, precision = 10, nullable = false)
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Boolean getIsEnabled ()
  {
    return isEnabled;
  }

  public void setIsEnabled (Boolean isEnabled)
  {
    this.isEnabled = isEnabled;
  }

  public Integer getOverDueDay() {
    return overDueDay;
  }

  public void setOverDueDay(Integer overDueDay) {
    this.overDueDay = overDueDay;
  }

  public Date getOverDueTime() {
    return overDueTime;
  }

  public void setOverDueTime(Date overDueTime) {
    this.overDueTime = overDueTime;
  }

  @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
  public Set<CouponEndUser> getCouponEndUsers() {
    return couponEndUsers;
  }

  public void setCouponEndUsers(Set<CouponEndUser> couponEndUsers) {
    this.couponEndUsers = couponEndUsers;
  }

  @ManyToMany
  public Set<CarService> getCarServices() {
    return carServices;
  }

  public void setCarServices(Set<CarService> carServices) {
    this.carServices = carServices;
  }


  @Index(name = "index_coupon_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @Column(length = 100)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public CouponType getType() {
    return type;
  }

  public void setType(CouponType type) {
    this.type = type;
  }

  public CouponOverDueType getOverDueType() {
    return overDueType;
  }

  public void setOverDueType(CouponOverDueType overDueType) {
    this.overDueType = overDueType;
  }

  public CouponSendType getSendType() {
    return sendType;
  }

  public void setSendType(CouponSendType sendType) {
    this.sendType = sendType;
  }

}
