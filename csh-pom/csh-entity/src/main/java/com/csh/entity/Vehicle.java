package com.csh.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the csh_vehicle database table.
 * 
 */
@Indexed(index="vehicle")
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

  @JsonProperty
  public Date getBindTime ()
  {
    return bindTime;
  }

  public void setBindTime (Date bindTime)
  {
    this.bindTime = bindTime;
  }

  @JsonProperty
  @ManyToOne
  @IndexedEmbedded
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

  @JsonProperty
  @OneToOne()
  public DeviceInfo getDevice ()
  {
    return device;
  }

  public void setDevice (DeviceInfo device)
  {
    this.device = device;
  }

  @OneToOne(mappedBy="vehicle")
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

  @JsonProperty
  @Field(index=org.hibernate.search.annotations.Index.TOKENIZED,analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getPlate ()
  {
    return plate;
  }

  public void setPlate (String plate)
  {
    this.plate = plate;
  }

  @JsonProperty
  @Field(index=org.hibernate.search.annotations.Index.TOKENIZED,analyzer = @Analyzer(impl = IKAnalyzer.class))
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

  @JsonProperty
  @ManyToOne
  @IndexedEmbedded
  public EndUser getEndUser ()
  {
    return endUser;
  }

  public void setEndUser (EndUser endUser)
  {
    this.endUser = endUser;
  }
  @Index(name="vehicle_tenantid")
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

}