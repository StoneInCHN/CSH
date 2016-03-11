package com.csh.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;
import com.csh.entity.commonenum.CommonEnum.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 终端用户
 * 
 */
@Entity
@Table(name="csh_end_user")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_end_user_sequence")
public class EndUser extends BaseEntity  {
	private static final long serialVersionUID = 1L;


	/**
   * 租户ID
   */
  private Long tenantID;
	 /**
   * 账号状态
   */
  private AccountStatus accountStatus;
  
  /**
   * 真实姓名
   */
  private String realName;
  
  /**
   * 用户名
   */
  private String userName;
  /**
   * 年龄
   */
  private Integer age;
  /**
   * 性别
   */
  private Gender gender;
  /**
   * 手机号
   */
  private String mobileNum;
  /**
   * 密码
   */
  private String password;
 
  /**
   * 用户头像
   */
  private String photo;
  /**
   * 出生日期
   */
  private Date birthDay;
  /**
   *  地址
   */
  private String address;

  /**
   * QQ号
   */
	private String qq;
	/**
	 * 签名
	 */
	private String signature;

	/**
	 * VIP等级
	 */
	private int vipLevel;
	
	/**
	 * 驾照
	 */
	private DriverLicense driverLicense;

	/**
	 * 车辆
	 */
	private Set<Vehicle> vehicles;

	@Index(name = "endUser_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  @JsonProperty
  public AccountStatus getAccountStatus ()
  {
    return accountStatus;
  }

  public void setAccountStatus (AccountStatus accountStatus)
  {
    this.accountStatus = accountStatus;
  }
  @JsonProperty
  public String getRealName ()
  {
    return realName;
  }

  public void setRealName (String realName)
  {
    this.realName = realName;
  }
  @JsonProperty
  public String getUserName ()
  {
    return userName;
  }

  public void setUserName (String userName)
  {
    this.userName = userName;
  }
  @JsonProperty
  public Integer getAge ()
  {
    return age;
  }

  public void setAge (Integer age)
  {
    this.age = age;
  }
  @JsonProperty
  public Gender getGender ()
  {
    return gender;
  }

  public void setGender (Gender gender)
  {
    this.gender = gender;
  }
  @JsonProperty
  public String getMobileNum ()
  {
    return mobileNum;
  }

  public void setMobileNum (String mobileNum)
  {
    this.mobileNum = mobileNum;
  }

  public String getPassword ()
  {
    return password;
  }

  public void setPassword (String password)
  {
    this.password = password;
  }

  public String getPhoto ()
  {
    return photo;
  }

  public void setPhoto (String photo)
  {
    this.photo = photo;
  }

  public Date getBirthDay ()
  {
    return birthDay;
  }

  public void setBirthDay (Date birthDay)
  {
    this.birthDay = birthDay;
  }

  public String getAddress ()
  {
    return address;
  }

  public void setAddress (String address)
  {
    this.address = address;
  }

  @JsonProperty
  public String getQq ()
  {
    return qq;
  }

  public void setQq (String qq)
  {
    this.qq = qq;
  }

  public String getSignature ()
  {
    return signature;
  }

  public void setSignature (String signature)
  {
    this.signature = signature;
  }

  public int getVipLevel ()
  {
    return vipLevel;
  }

  public void setVipLevel (int vipLevel)
  {
    this.vipLevel = vipLevel;
  }
  
  public DriverLicense getDriverLicense ()
  {
    return driverLicense;
  }

  public void setDriverLicense (DriverLicense driverLicense)
  {
    this.driverLicense = driverLicense;
  }

  @OneToMany(mappedBy="endUser")
  public Set<Vehicle> getVehicles ()
  {
    return vehicles;
  }

  public void setVehicles (Set<Vehicle> vehicles)
  {
    this.vehicles = vehicles;
  }


}