package com.csh.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;

/**
 * Entity - 登录统计
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "csh_login_statistics")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_login_statistics_sequence")
public class LoginStatistics extends BaseEntity {

  private static final long serialVersionUID = -761304021347883183L;
  /**
   * 登录日期
   */
  private Date loginDate;
  /**
   * 登录IP
   */
  private String loginIp;
  /**
   * 登录用户
   */
  private EndUser endUser;

  /**
   * 手机IMEI号
   */
  private String imei;

  public String getImei() {
    return imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
  }

  public Date getLoginDate() {
    return loginDate;
  }

  public void setLoginDate(Date loginDate) {
    this.loginDate = loginDate;
  }

  @Column(length = 50)
  public String getLoginIp() {
    return loginIp;
  }

  public void setLoginIp(String loginIp) {
    this.loginIp = loginIp;
  }

  @ManyToOne
  public EndUser getEndUser() {
    return endUser;
  }

  public void setEndUser(EndUser endUser) {
    this.endUser = endUser;
  }

}
