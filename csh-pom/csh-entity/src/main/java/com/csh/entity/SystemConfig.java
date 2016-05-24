package com.csh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.SystemConfigKey;
import com.csh.lucene.LowCaseBridgeImpl;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 数据字典配置
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "csh_system_config")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_system_config_sequence")
public class SystemConfig extends BaseEntity {


  private static final long serialVersionUID = -1684057707764082356L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 配置项值
   */
  private SystemConfigKey configKey;
  /**
   * 配置项值
   */
  private String configValue;

  /**
   * 排序
   */
  private Integer configOrder;

  /**
   * 是否启用
   */
  private Boolean isEnabled;


  public SystemConfigKey getConfigKey() {
    return configKey;
  }

  public void setConfigKey(SystemConfigKey configKey) {
    this.configKey = configKey;
  }

  @JsonProperty
  public Boolean getIsEnabled() {
    return isEnabled;
  }

  public void setIsEnabled(Boolean isEnabled) {
    this.isEnabled = isEnabled;
  }

  public Integer getConfigOrder() {
    return configOrder;
  }

  public void setConfigOrder(Integer configOrder) {
    this.configOrder = configOrder;
  }

  @JsonProperty
  @Column(length = 20)
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.UN_TOKENIZED,
      analyzer = @Analyzer(impl = IKAnalyzer.class))
  @FieldBridge(impl = LowCaseBridgeImpl.class)
  public String getConfigValue() {
    return configValue;
  }

  public void setConfigValue(String configValue) {
    this.configValue = configValue;
  }

  @Index(name = "system_config_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

}
