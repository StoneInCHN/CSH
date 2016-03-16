package com.csh.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

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

  private Set<DeviceInfo> deviceInfos = new HashSet<DeviceInfo> ();
  @JsonProperty
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

  @OneToMany(mappedBy= "type")
  public Set<DeviceInfo> getDeviceInfos ()
  {
    return deviceInfos;
  }

  public void setDeviceInfos (Set<DeviceInfo> deviceInfos)
  {
    this.deviceInfos = deviceInfos;
  }


}