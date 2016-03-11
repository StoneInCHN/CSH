package com.csh.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * 故障码数据表
 * 
 */
@Entity
@Table(name="csh_vehicle_dtc")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "csh_vehicle_dtc_sequence")
public class VehicleDtc implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * 车型
	 */
	private VehicleBrand brand;

	/**
	 * 故障码
	 */
	private String code;

	/**
	 * 描述信息
	 */
	private String des;

	private Long tenantID;

  public VehicleBrand getBrand ()
  {
    return brand;
  }

  public void setBrand (VehicleBrand brand)
  {
    this.brand = brand;
  }

  public String getCode ()
  {
    return code;
  }

  public void setCode (String code)
  {
    this.code = code;
  }

  public String getDes ()
  {
    return des;
  }

  public void setDes (String des)
  {
    this.des = des;
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