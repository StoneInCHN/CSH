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

  /**
   * 极光推送appKey
   */
  private String jpushAppKey;

  /**
   * 极光推送masterSecret
   */
  private String jpushMasterSecret;

  /**
   * 微信支付平台秘钥
   */
  private String wechatKey;

  /**
   * 微信分配的公众账号ID(企业号corpid即为此appId)
   */
  private String wechatAppid;

  /**
   * 微信支付平台商户ID
   */
  private String wechatMchId;


  @Column(length = 100)
  public String getJpushAppKey() {
    return jpushAppKey;
  }

  public void setJpushAppKey(String jpushAppKey) {
    this.jpushAppKey = jpushAppKey;
  }

  @Column(length = 100)
  public String getJpushMasterSecret() {
    return jpushMasterSecret;
  }

  public void setJpushMasterSecret(String jpushMasterSecret) {
    this.jpushMasterSecret = jpushMasterSecret;
  }

  @Column(length = 100)
  public String getWechatKey() {
    return wechatKey;
  }

  public void setWechatKey(String wechatKey) {
    this.wechatKey = wechatKey;
  }

  @Column(length = 100)
  public String getWechatAppid() {
    return wechatAppid;
  }

  public void setWechatAppid(String wechatAppid) {
    this.wechatAppid = wechatAppid;
  }

  @Column(length = 100)
  public String getWechatMchId() {
    return wechatMchId;
  }

  public void setWechatMchId(String wechatMchId) {
    this.wechatMchId = wechatMchId;
  }

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
