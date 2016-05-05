package com.csh.entity;

import java.math.BigDecimal;
import java.sql.Date;
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
import com.csh.entity.commonenum.CommonEnum.RedPackageOverDueType;
import com.csh.entity.commonenum.CommonEnum.RedPackageSendType;
import com.csh.entity.commonenum.CommonEnum.RedPackageType;

/**
 * 红包
 * 
 * @author Andrea
 *
 */
@Entity
@Table(name = "csh_red_package")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_red_package_sequence")
public class RedPackage extends BaseEntity {

  private static final long serialVersionUID = 7282926448628904157L;


  /**
   * 红包金额
   */
  private BigDecimal redAmount = new BigDecimal(0);

  /**
   * 红包类型
   */
  private RedPackageType type;

  /**
   * 红包过期方式
   */
  private RedPackageOverDueType overDueType;

  /**
   * 红包发送方式
   */
  private RedPackageSendType sendType;

  /**
   * 注册及绑定类红包是否发送的开关
   */
  private Boolean sendFlag;

  /**
   * 过期天数
   */
  private Integer overDueDay;

  /**
   * 过期日期
   */
  private Date overDueTime;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 说明
   */
  private String remark;


  /**
   * 红包与用户对于关系
   */
  private Set<RedPackageEndUser> redPackageEndUsers = new HashSet<RedPackageEndUser>();

  /**
   * 汽车服务
   */
  private Set<CarService> carServices = new HashSet<CarService>();


  public Boolean getSendFlag() {
    return sendFlag;
  }

  public void setSendFlag(Boolean sendFlag) {
    this.sendFlag = sendFlag;
  }

  public RedPackageSendType getSendType() {
    return sendType;
  }

  public void setSendType(RedPackageSendType sendType) {
    this.sendType = sendType;
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

  public RedPackageOverDueType getOverDueType() {
    return overDueType;
  }

  public void setOverDueType(RedPackageOverDueType overDueType) {
    this.overDueType = overDueType;
  }

  @OneToMany(mappedBy = "redPackage", cascade = CascadeType.ALL)
  public Set<RedPackageEndUser> getRedPackageEndUsers() {
    return redPackageEndUsers;
  }

  public void setRedPackageEndUsers(Set<RedPackageEndUser> redPackageEndUsers) {
    this.redPackageEndUsers = redPackageEndUsers;
  }

  @ManyToMany
  public Set<CarService> getCarServices() {
    return carServices;
  }

  public void setCarServices(Set<CarService> carServices) {
    this.carServices = carServices;
  }

  @Column(scale = 2, precision = 10, nullable = false)
  public BigDecimal getRedAmount() {
    return redAmount;
  }

  public void setRedAmount(BigDecimal redAmount) {
    this.redAmount = redAmount;
  }

  public RedPackageType getType() {
    return type;
  }

  public void setType(RedPackageType type) {
    this.type = type;
  }

  @Index(name = "index_redPackage_tenantid")
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


}
