package com.csh.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.Status;

/**
 * 行驶证
 * 
 */
@Entity
@Table (name = "csh_driving_license")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "csh_driver_license_sequence")
public class DrivingLicense extends BaseEntity
{
  private static final long serialVersionUID = 1L;

  /**
   * 地址
   */
  private String address;

  /**
   * 车辆型号
   */
  private VehicleBrand brand;

  private String engine;

  /**
   * 所有人
   */
  private String owner;

  /**
   * 车牌号
   */
  private String plate;

  /**
   * 注册日期
   */
  private Date regist;

  /**
   * 状态
   */
  private Status status;

  private String type;

  private String useful;

  private String vin;

  private Long tenantID;

  private Vehicle vehicle;

  public String getAddress ()
  {
    return address;
  }

  public void setAddress (String address)
  {
    this.address = address;
  }

  public VehicleBrand getBrand ()
  {
    return brand;
  }

  public void setBrand (VehicleBrand brand)
  {
    this.brand = brand;
  }

  public String getEngine ()
  {
    return engine;
  }

  public void setEngine (String engine)
  {
    this.engine = engine;
  }

  public String getOwner ()
  {
    return owner;
  }

  public void setOwner (String owner)
  {
    this.owner = owner;
  }

  public String getPlate ()
  {
    return plate;
  }

  public void setPlate (String plate)
  {
    this.plate = plate;
  }

  public Date getRegist ()
  {
    return regist;
  }

  public void setRegist (Date regist)
  {
    this.regist = regist;
  }

  public Status getStatus ()
  {
    return status;
  }

  public void setStatus (Status status)
  {
    this.status = status;
  }

  public String getType ()
  {
    return type;
  }

  public void setType (String type)
  {
    this.type = type;
  }

  public String getUseful ()
  {
    return useful;
  }

  public void setUseful (String useful)
  {
    this.useful = useful;
  }

  public String getVin ()
  {
    return vin;
  }

  public void setVin (String vin)
  {
    this.vin = vin;
  }

  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  public Vehicle getVehicle ()
  {
    return vehicle;
  }

  public void setVehicle (Vehicle vehicle)
  {
    this.vehicle = vehicle;
  }

}