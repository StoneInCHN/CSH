package com.csh.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.csh.entity.base.BaseEntity;
import com.csh.lucene.DateBridgeImpl;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 车辆保养情况
 * 
 */
@Indexed(index="vehicleMaintain")
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
  private Long lastMaintainMileage;

  /**
   * 上次保养时间
   */
  private Date lastMaintainDate;
  private Long tenantID;

  @JsonProperty
  @ManyToOne(fetch=FetchType.EAGER)
  @IndexedEmbedded
  public Vehicle getVehicle ()
  {
    return vehicle;
  }

  public void setVehicle (Vehicle vehicle)
  {
    this.vehicle = vehicle;
  }

  @JsonProperty
  @Field(index = Index.YES, store = Store.NO,analyze = Analyze.NO)
  @FieldBridge(impl = DateBridgeImpl.class)
  public Date getNextMaintainDate ()
  {
    return nextMaintainDate;
  }

  
  public void setNextMaintainDate (Date nextMaintainDate)
  {
    this.nextMaintainDate = nextMaintainDate;
  }

  @JsonProperty
  public Long getNextMaintainMileage ()
  {
    return nextMaintainMileage;
  }

  public void setNextMaintainMileage (Long nextMaintainMileage)
  {
    this.nextMaintainMileage = nextMaintainMileage;
  }

  @org.hibernate.annotations.Index(name="vehicleMaintain_tenantid")
  @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO,analyze = Analyze.NO)
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  @JsonProperty
  public Long getLastMaintainMileage ()
  {
    return lastMaintainMileage;
  }

  public void setLastMaintainMileage (Long lastMaintainMileage)
  {
    this.lastMaintainMileage = lastMaintainMileage;
  }

  @JsonProperty
  @Field(index = Index.YES, store = Store.NO,analyze = Analyze.NO)
  @FieldBridge(impl = DateBridgeImpl.class)
  public Date getLastMaintainDate ()
  {
    return lastMaintainDate;
  }

  public void setLastMaintainDate (Date lastMaintainDate)
  {
    this.lastMaintainDate = lastMaintainDate;
  }

}