package com.csh.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.Gender;

/**
 * 身份证
 * 
 */
@Entity
@Table (name = "csh_identity_card")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_identity_card_sequence")
public class IdentityCard extends BaseEntity
{
  private static final long serialVersionUID = 1L;

  
  /**
   * 地址
   */
  private String address;

  /**
   * 生日
   */
  private Date birthday;

  /**
   * 编号
   */
  private String sn_no;

  /**
   * 失效日期
   */
  private Date expirationDate;

  /**
   * 姓名
   */
  private String name;

  /**
   * 国籍
   */
  private String national;


  /**
   * 性别
   */
  private Gender gender;

  private EndUser endUser;

  private Long tenantID;

  @Index (name = "endUser_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  public String getAddress ()
  {
    return address;
  }

  public void setAddress (String address)
  {
    this.address = address;
  }

  public Date getBirthday ()
  {
    return birthday;
  }

  public void setBirthday (Date birthday)
  {
    this.birthday = birthday;
  }

  public String getSn_no ()
  {
    return sn_no;
  }

  public void setSn_no (String sn_no)
  {
    this.sn_no = sn_no;
  }

  public Date getExpirationDate ()
  {
    return expirationDate;
  }

  public void setExpirationDate (Date expirationDate)
  {
    this.expirationDate = expirationDate;
  }

  public String getName ()
  {
    return name;
  }

  public void setName (String name)
  {
    this.name = name;
  }

  public String getNational ()
  {
    return national;
  }

  public void setNational (String national)
  {
    this.national = national;
  }

  public EndUser getEndUser ()
  {
    return endUser;
  }

  public void setEndUser (EndUser endUser)
  {
    this.endUser = endUser;
  }

  public Gender getGender ()
  {
    return gender;
  }

  public void setGender (Gender gender)
  {
    this.gender = gender;
  }
  
}