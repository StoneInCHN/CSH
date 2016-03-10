package com.csh.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.Status;

/**
 * The persistent class for the csh_vehicle database table.
 * 
 */
@Entity
@Table (name = "csh_vehicle")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "csh_vehicle_sequence")
public class Vehicle extends BaseEntity
{
  private static final long serialVersionUID = 1L;

  /**
   * 代理商
   
  private String agent;
  */
  /**
   * 绑定时间
   */
  private Date bindTime;

  /**
   * 车辆型号
   */
  private VehicleBrand vehicleBrand;

  /**
   * 颜色
   */
  private String color;

  /**
   * 设备信息
   */
  private DeviceInfo device;

  /**
   * 行驶证
   */
  private DrivingLicense drivingLicense;

  private int isDefault;

  /**
   * 车牌号
   */
  private String plate;

  /**
   * 车辆状态
   */
  private Status status;

  private String vin;

  private EndUser endUser;

  private Long tenantID;

  public Date getBindTime ()
  {
    return bindTime;
  }

  public void setBindTime (Date bindTime)
  {
    this.bindTime = bindTime;
  }

  public VehicleBrand getVehicleBrand ()
  {
    return vehicleBrand;
  }

  public void setVehicleBrand (VehicleBrand vehicleBrand)
  {
    this.vehicleBrand = vehicleBrand;
  }

  public String getColor ()
  {
    return color;
  }

  public void setColor (String color)
  {
    this.color = color;
  }

  public DeviceInfo getDevice ()
  {
    return device;
  }

  public void setDevice (DeviceInfo device)
  {
    this.device = device;
  }

  public DrivingLicense getDrivingLicense ()
  {
    return drivingLicense;
  }

  public void setDrivingLicense (DrivingLicense drivingLicense)
  {
    this.drivingLicense = drivingLicense;
  }

  public int getIsDefault ()
  {
    return isDefault;
  }

  public void setIsDefault (int isDefault)
  {
    this.isDefault = isDefault;
  }

  public String getPlate ()
  {
    return plate;
  }

  public void setPlate (String plate)
  {
    this.plate = plate;
  }

  public Status getStatus ()
  {
    return status;
  }

  public void setStatus (Status status)
  {
    this.status = status;
  }

  public String getVin ()
  {
    return vin;
  }

  public void setVin (String vin)
  {
    this.vin = vin;
  }

  public EndUser getEndUser ()
  {
    return endUser;
  }

  public void setEndUser (EndUser endUser)
  {
    this.endUser = endUser;
  }

  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

}