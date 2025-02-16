package com.csh.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.SystemType;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity - 角色
 * 
 */
@Indexed(index="role")
@Entity
@Table(name = "csh_role")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_role_sequence")
public class Role extends BaseEntity {

  private static final long serialVersionUID = -6614052029623997372L;

  /** 名称 */
  private String name;

  /** 是否内置 */
  private Boolean isSystem;

  /** 描述 */
  private String description;

  /** 租户系统权限 */
  private Set<ConfigMeta> configMetas = new HashSet<ConfigMeta>();

  /** 租户账号 */
//  private Set<TenantAccount> tenantAccounts = new HashSet<TenantAccount>();
  
  /** 后台管理系统权限 */
  private List<String> authorities = new ArrayList<String>();
  
  /**
   * 所属系统
   */
  private SystemType systemType;
  
  /**
   * 租户ID
   */
  private Long tenantID;
  
  @Index(name="role_tenantid")
  @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO,analyze = Analyze.NO)
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  /**
   * 获取名称
   * 
   * @return 名称
   */
  @JsonProperty
  @NotEmpty
  @Length(max = 200)
  @Column(nullable = false)
  @Field(index=org.hibernate.search.annotations.Index.YES,analyze = Analyze.NO)
  public String getName() {
    return name;
  }

  /**
   * 设置名称
   * 
   * @param name 名称
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * 获取是否内置
   * 
   * @return 是否内置
   */
  @Column(nullable = false, updatable = false)
  public Boolean getIsSystem() {
    return isSystem;
  }

  /**
   * 设置是否内置
   * 
   * @param isSystem 是否内置
   */
  public void setIsSystem(Boolean isSystem) {
    this.isSystem = isSystem;
  }

  /**
   * 获取描述
   * 
   * @return 描述
   */
  @JsonProperty
  @Length(max = 200)
  public String getDescription() {
    return description;
  }

  /**
   * 设置描述
   * 
   * @param description 描述
   */
  public void setDescription(String description) {
    this.description = description;
  }
  
  /**
   * 获取权限
   * 
   * @return 权限
   */
  @JsonProperty
  @NotEmpty
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "csh_role_config_meta")
  public Set<ConfigMeta> getConfigMetas ()
  {
    return configMetas;
  }

  public void setConfigMetas (Set<ConfigMeta> configMetas)
  {
    this.configMetas = configMetas;
  }

 /* @ManyToMany(mappedBy = "roles")
  public Set<TenantAccount> getTenantAccounts() {
    return tenantAccounts;
  }

  public void setTenantAccounts(Set<TenantAccount> tenantAccounts) {
    this.tenantAccounts = tenantAccounts;
  }*/
  
  /**
   * 获取权限
   * 
   * @return 权限
   */
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "csh_role_authority")
  public List<String> getAuthorities() {
      return authorities;
  }

  /**
   * 设置权限
   * 
   * @param authorities
   *            权限
   */
  public void setAuthorities(List<String> authorities) {
      this.authorities = authorities;
  }
  

  @Column(length=2)
  public SystemType getSystemType() {
    return systemType;
  }

  public void setSystemType(SystemType systemType) {
    this.systemType = systemType;
  }
  
}
