package com.csh.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.Status;

import java.sql.Timestamp;


/**
 * 油品信息表
 * 
 */
@Entity
@Table(name="csh_vehicle_oil")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "csh_vehicle_oil_sequence")
public class VehicleOil extends BaseEntity {
	private static final long serialVersionUID = 1L;


	/**
	 * 油品名称 如93#汽油，0#柴油
	 */
	private String name;

	/**
	 * 状态
	 */
	private Status status;

	private Long tenantID;

  public String getName ()
  {
    return name;
  }

  public void setName (String name)
  {
    this.name = name;
  }

  public Status getStatus ()
  {
    return status;
  }

  public void setStatus (Status status)
  {
    this.status = status;
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