package com.csh.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.Status;

/**
 * 设备类型
 * 
 */
@Entity
@Table (name = "csh_device_type")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "csh_device_type_sequence")
public class DeviceType extends BaseEntity
{
  private static final long serialVersionUID = 1L;

  /**
   * 类型名称
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