package com.csh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.ServiceType;

/**
 * 租户消息
 * 
 * @author tanbiao
 * 
 */
@Entity
@Table(name = "csh_tenant_message")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_tenant_message_sequence")
public class TenantMsg extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -8192567174284286492L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 提示消息相关的服务类型
   */
  private ServiceType msgType;

  /**
   * 是否已推送
   */
  private Boolean isPush = false;

  /**
   * 内容
   */
  private String content;

  /**
   * 备注
   */
  private String remark;

  @Index(name = "tenant_message_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  public ServiceType getMsgType() {
    return msgType;
  }

  public void setMsgType(ServiceType msgType) {
    this.msgType = msgType;
  }

  public Boolean getIsPush() {
    return isPush;
  }

  public void setIsPush(Boolean isPush) {
    this.isPush = isPush;
  }

  @Column(length = 200)
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Column(length = 200)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


}
