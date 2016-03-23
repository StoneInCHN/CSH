package com.csh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.csh.entity.base.BaseEntity;

/**
 * app
 * 
 * @author huyong
 *
 */
@Entity
@Table(name = "csh_app")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_app_sequence")
public class App extends BaseEntity {

  private static final long serialVersionUID = 7282926448628904157L;


  /**
   * 租户ID
   */
  private Long tenantID;

  /** app 名称 */
  private String appTitleName;

  /**
   * 租户的
   */
  private String logo;

  @Index(name = "app_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @Column(length = 10)
  public String getAppTitleName() {
    return appTitleName;
  }

  public void setAppTitleName(String appTitleName) {
    this.appTitleName = appTitleName;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

}
