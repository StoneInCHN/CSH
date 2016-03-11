package com.csh.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.csh.entity.base.BaseEntity;

import java.sql.Timestamp;
import java.util.Date;


/**
 * 驾照
 * 
 */
@Entity
@Table(name="csh_driver_license")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_driver_license_sequence")
public class DriverLicense extends BaseEntity {
	private static final long serialVersionUID = 1L;

	 /**
   * 编号
   */
  private String sn_no;
  /**
   * 姓名
   */
  private String name;
  
	/**
	 * 地址
	 */
	private String address;

	/**
	 * 生日
	 */
	private Date birthday;

	/**
	 * 驾驶等级
	 */
	private String grade;

	/**
	 * 过期时间
	 */
	private Date expirationDate;

	private EndUser endUser;

  public String getSn_no ()
  {
    return sn_no;
  }

  public void setSn_no (String sn_no)
  {
    this.sn_no = sn_no;
  }

  public String getName ()
  {
    return name;
  }

  public void setName (String name)
  {
    this.name = name;
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

  public String getGrade ()
  {
    return grade;
  }

  public void setGrade (String grade)
  {
    this.grade = grade;
  }

  public Date getExpirationDate ()
  {
    return expirationDate;
  }

  public void setExpirationDate (Date expirationDate)
  {
    this.expirationDate = expirationDate;
  }

  public EndUser getEndUser ()
  {
    return endUser;
  }

  public void setEndUser (EndUser endUser)
  {
    this.endUser = endUser;
  }


}