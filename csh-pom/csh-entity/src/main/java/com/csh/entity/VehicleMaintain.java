package com.csh.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;

/**
 * 车辆保养情况
 * 
 */
@Entity
@Table (name = "csh_vehicle_maintain")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "csh_vehicle_maintain_sequence")
public class VehicleMaintain extends BaseEntity
{
  private static final long serialVersionUID = 1L;

  /**
   * 车辆
   */
  private Vehicle vehicle;

  /**
   * 下次保养时间
   */
  private Date nextMaintainDate;
  /**
   * 下次保养里程
   */
  private Long nextMaintainMileage;
  /**
   * 上次保养里程
   */
  private Long maintainMileage;

  private Long tenantID;

  public Vehicle getVehicle ()
  {
    return vehicle;
  }

  public void setVehicle (Vehicle vehicle)
  {
    this.vehicle = vehicle;
  }

  public Date getNextMaintainDate ()
  {
    return nextMaintainDate;
  }

  public void setNextMaintainDate (Date nextMaintainDate)
  {
    this.nextMaintainDate = nextMaintainDate;
  }

  public Long getNextMaintainMileage ()
  {
    return nextMaintainMileage;
  }

  public void setNextMaintainMileage (Long nextMaintainMileage)
  {
    this.nextMaintainMileage = nextMaintainMileage;
  }

  public Long getMaintainMileage ()
  {
    return maintainMileage;
  }

  public void setMaintainMileage (Long maintainMileage)
  {
    this.maintainMileage = maintainMileage;
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