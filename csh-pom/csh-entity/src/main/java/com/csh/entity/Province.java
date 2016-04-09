package com.csh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;


/**
 * 省份信息表
 * 
 */
@Entity
@Table(name = "csh_province")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_province_sequence")
public class Province extends BaseEntity {
  private static final long serialVersionUID = 1L;



  /**
   * 省份简称
   */
  private String shortName;

  /**
   * 省份全称
   */
  private String fullName;

  /**
   * 省份全称拼音
   */
  private String pinyinName;

  @Column(length = 2)
  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  @Column(length = 10)
  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @Column(length = 50)
  public String getPinyinName() {
    return pinyinName;
  }

  public void setPinyinName(String pinyinName) {
    this.pinyinName = pinyinName;
  }



}
