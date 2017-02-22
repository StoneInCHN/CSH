package com.csh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;

@Entity
@Table(name = "csh_fault_code")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_fault_code_sequence")
public class FaultCode extends BaseEntity{

  /**
   * 
   */
  private static final long serialVersionUID = 2857218353963389324L;
  
  /**
   * 故障代码关键字
   */
  private String code;
  
  /**
   * 适用范围
   */
  private String rangeScope;
  
  /**
   * 中文定义
   */
  private String defineCh;

  /**
   * 英文定义
   */
  private String  defineEn;
  
  /**
   * 范畴
   */
  private String scope;
  
  /**
   * 背景知识
   */
  private String info;

  @Column(length=20,nullable=false)
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Column(length=200)
  public String getDefineCh() {
    return defineCh;
  }

  public void setDefineCh(String defineCh) {
    this.defineCh = defineCh;
  }

  @Column(length=200)
  public String getDefineEn() {
    return defineEn;
  }

  public void setDefineEn(String defineEn) {
    this.defineEn = defineEn;
  }

  @Column(length=200)
  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  @Column(length=500)
  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  @Column(length=200)
  public String getRangeScope() {
    return rangeScope;
  }

  public void setRangeScope(String rangeScope) {
    this.rangeScope = rangeScope;
  }
}
