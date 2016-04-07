package com.csh.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.OilType;


/**
 * 油品信息表
 * 
 */
@Entity
@Table(name = "csh_vehicle_oil")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_vehicle_oil_sequence")
public class VehicleOil extends BaseEntity {
  private static final long serialVersionUID = 1L;


  /**
   * 油品名称 如93#汽油，0#柴油
   */
  private OilType oilType;



  /**
   * 油价
   */
  private BigDecimal price;

  /**
   * 省份
   */
  private String province;


  private String plate;

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  
  public OilType getOilType() {
    return oilType;
  }

  public void setOilType(OilType oilType) {
    this.oilType = oilType;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  @Column(length=8)
  public String getPlate() {
    return plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

}
