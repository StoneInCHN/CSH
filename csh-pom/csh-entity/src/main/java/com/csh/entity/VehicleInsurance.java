package com.csh.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Indexed;

import com.csh.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 车辆保险
 * 
 */
@Indexed(index="vehicleInsurance")
@Entity
@Table (name = "csh_vehicle_insurance")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "csh_vehicle_insurance_sequence")
public class VehicleInsurance extends BaseEntity
{

  private static final long serialVersionUID = 1L;

  /**
   * 保险服务记录
   */
  private CarServiceRecord carServiceRecord;
  
  /**
   * 投保人
   */
  private EndUser endUser;
  /**
   * 投保公司
   */
  private String insuredCompany;
  /**
   * 投保车辆
   */
  private Vehicle vehicle;
  /**
   * 保险开始日期
   */
  private Date insuranceStartDate;
  
  /**
   * 保险到期时间
   */
  private Date insuranceEndDate;

  /**
   * 身份证照片
   */
  private String IDphoto;
  
  /**
   * 行驶证照片
   */
  private String drivingLicensePhoto;
  
  /**
   * 驾驶证照片
   */
  private String driverLicensePhoto;
  
  /**
   * 是否过户
   */
  private Boolean isOwned;
  
  /**
   * 是否贷款车
   */
  private Boolean isLoaned;

  /**
   * 租户ID
   */
  private Long tenantID;
  
  @OneToOne
  public CarServiceRecord getCarServiceRecord ()
  {
    return carServiceRecord;
  }

  public void setCarServiceRecord (CarServiceRecord carServiceRecord)
  {
    this.carServiceRecord = carServiceRecord;
  }

  @JsonProperty
  public String getInsuredCompany ()
  {
    return insuredCompany;
  }

  public void setInsuredCompany (String insuredCompany)
  {
    this.insuredCompany = insuredCompany;
  }

  @ManyToOne
  @JsonProperty
  public Vehicle getVehicle ()
  {
    return vehicle;
  }

  public void setVehicle (Vehicle vehicle)
  {
    this.vehicle = vehicle;
  }

  @JsonProperty
  public Date getInsuranceStartDate ()
  {
    return insuranceStartDate;
  }

  public void setInsuranceStartDate (Date insuranceStartDate)
  {
    this.insuranceStartDate = insuranceStartDate;
  }

  @JsonProperty
  public Date getInsuranceEndDate ()
  {
    return insuranceEndDate;
  }

  public void setInsuranceEndDate (Date insuranceEndDate)
  {
    this.insuranceEndDate = insuranceEndDate;
  }

  public String getIDphoto ()
  {
    return IDphoto;
  }

  public void setIDphoto (String iDphoto)
  {
    IDphoto = iDphoto;
  }

  public String getDrivingLicensePhoto ()
  {
    return drivingLicensePhoto;
  }

  public void setDrivingLicensePhoto (String drivingLicensePhoto)
  {
    this.drivingLicensePhoto = drivingLicensePhoto;
  }

  public String getDriverLicensePhoto ()
  {
    return driverLicensePhoto;
  }

  public void setDriverLicensePhoto (String driverLicensePhoto)
  {
    this.driverLicensePhoto = driverLicensePhoto;
  }

  @JsonProperty
  public Boolean getIsOwned ()
  {
    return isOwned;
  }

  public void setIsOwned (Boolean isOwned)
  {
    this.isOwned = isOwned;
  }

  @JsonProperty
  public Boolean getIsLoaned ()
  {
    return isLoaned;
  }

  public void setIsLoaned (Boolean isLoaned)
  {
    this.isLoaned = isLoaned;
  }

  @Index(name="vehicleInsurance_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  @ManyToOne
  @JsonProperty
  public EndUser getEndUser ()
  {
    return endUser;
  }

  public void setEndUser (EndUser endUser)
  {
    this.endUser = endUser;
  }
  
}
