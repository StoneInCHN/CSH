package com.csh.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.NotEmpty;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 租户用户登录账户
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "csh_tenant_account")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_tenant_account_sequence")
@Indexed(index = "tenantAccount")
public class TenantAccount extends BaseEntity {

  private static final long serialVersionUID = -665961639617388534L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 用户名
   */
  private String userName;

  /**
   * 密码
   */
  private String password;

  /** 角色 */
  private Set<Role> roles = new HashSet<Role>();

  /**
   * 账号状态
   */
  private AccountStatus accoutStatus;

  /** 姓名 */
  private String realName;

  /** 最后登录日期 */
  private Date loginDate;

  /** 最后登录IP */
  private String loginIp;

  /** 员工编号 */
  private String staffID;

  /**
   * 是否为内置账户
   */
  private Boolean isSystem;

  /**
   * 租户用户
   */
  private TenantUser tenantUser;

  /**
   * 极光push注册ID
   */
  private String jpushRegId;

  @Column(length = 100)
  public String getjpushRegId() {
    return jpushRegId;
  }

  public void setjpushRegId(String jPushRegId) {
    this.jpushRegId = jPushRegId;
  }

  @OneToOne
  @JsonProperty
  public TenantUser getTenantUser() {
    return tenantUser;
  }

  public void setTenantUser(TenantUser tenantUser) {
    this.tenantUser = tenantUser;
  }

  @Column(length = 30)
  public String getStaffID() {
    return staffID;
  }

  public void setStaffID(String staffID) {
    this.staffID = staffID;
  }

  @JsonProperty
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.NO)
  public AccountStatus getAccoutStatus() {
    return accoutStatus;
  }

  public void setAccoutStatus(AccountStatus accoutStatus) {
    this.accoutStatus = accoutStatus;
  }

  @Column(length = 20)
  @JsonProperty
  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  @JsonProperty
  public Date getLoginDate() {
    return loginDate;
  }

  public void setLoginDate(Date loginDate) {
    this.loginDate = loginDate;
  }

  @Column(length = 20)
  @JsonProperty
  public String getLoginIp() {
    return loginIp;
  }

  public void setLoginIp(String loginIp) {
    this.loginIp = loginIp;
  }

  @JsonProperty
  public Boolean getIsSystem() {
    return isSystem;
  }

  public void setIsSystem(Boolean isSystem) {
    this.isSystem = isSystem;
  }

  /**
   * 获取角色
   * 
   * @return 角色
   */
  @NotEmpty
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "csh_tenant_account_role")
  @JsonProperty
  public Set<Role> getRoles() {
    return roles;
  }

  /**
   * 设置角色
   * 
   * @param roles 角色
   */
  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  @Index(name = "tenant_account_tenantid")
  @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.NO)
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @Column(length = 20)
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.NO)
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Column(length = 128)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
