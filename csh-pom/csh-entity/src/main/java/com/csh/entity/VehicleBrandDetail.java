package com.csh.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.OilType;
import com.csh.entity.commonenum.CommonEnum.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 车辆型号详情表
 * 
 */
@Entity
@Table (name = "csh_vehicle_brand_detail")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "csh_vehicle_brand_detail_sequence")
public class VehicleBrandDetail extends BaseEntity
{
  private static final long serialVersionUID = 1L;


  /**
   * 排量
   */
  private String disp;


  /**
   * 是否支持获取油量
   */
  private Boolean canGetoil;


  /**
   * 名称
   */
  private String name;

  
  /**
   * 百公里油耗
   */
  private Float oilPerHundred;

  private VehicleLine vehicleLine;

  /**
   * 车型状态
   */
  private Status status;

  /**
   * 油箱容积
   */
  private Float tank;
  
  private Set<Vehicle> vehicles = new HashSet<Vehicle> ();

  /**
   * 油型号
   */
  private OilType oilType;
  

  public Boolean getCanGetoil ()
  {
    return canGetoil;
  }

  public void setCanGetoil (Boolean canGetoil)
  {
    this.canGetoil = canGetoil;
  }


  @JsonProperty
  @Field(index=org.hibernate.search.annotations.Index.TOKENIZED,analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getName ()
  {
    return name;
  }

  public void setName (String name)
  {
    this.name = name;
  }

  public Float getOilPerHundred ()
  {
    return oilPerHundred;
  }

  public void setOilPerHundred (Float oilPerHundred)
  {
    this.oilPerHundred = oilPerHundred;
  }

 
  public Status getStatus ()
  {
    return status;
  }

  public void setStatus (Status status)
  {
    this.status = status;
  }

  public Float getTank ()
  {
    return tank;
  }

  public void setTank (Float tank)
  {
    this.tank = tank;
  }

  @OneToMany(mappedBy="vehicleBrandDetail")
  public Set<Vehicle> getVehicles ()
  {
    return vehicles;
  }

  public void setVehicles (Set<Vehicle> vehicles)
  {
    this.vehicles = vehicles;
  }

  @JsonProperty
  @ManyToOne(fetch=FetchType.EAGER)
  public VehicleLine getVehicleLine ()
  {
    return vehicleLine;
  }

  public void setVehicleLine (VehicleLine vehicleLine)
  {
    this.vehicleLine = vehicleLine;
  }

  @Column(length=2)
  public OilType getOilType() {
    return oilType;
  }

  public void setOilType(OilType oilType) {
    this.oilType = oilType;
  }

  public String getDisp ()
  {
    return disp;
  }

  public void setDisp (String disp)
  {
    this.disp = disp;
  }
  
}