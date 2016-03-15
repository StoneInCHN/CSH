package com.csh.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.DeviceStatus;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 设备信息
 * 
 */
@Indexed(index="deviceInfo")
@Entity
@Table(name="csh_device_info")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_device_info_sequence")
public class DeviceInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;


/**
 * 绑定时间
 */
	private Date bindTime;
	
	/**
	 * 解绑时间
	 */
	private Date unBindTime;

	/**
	 * 设备编号
	 */
	private String deviceNo;

	/**
	 * 设备状态
	 */
	private DeviceStatus deviceStatus;

/**
 * sim 卡号
 */
	private String simNo;

	/**
	 * 设备类型
	 */
	private DeviceType type;

	/**
	 * 车辆
	 */
	private Vehicle vehicle;
	
	/**
	 * 租户ID
	 */
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
  public Date getUnBindTime ()
  {
    return unBindTime;
  }

  public void setUnBindTime (Date unBindTime)
  {
    this.unBindTime = unBindTime;
  }
  @JsonProperty
  @Field(store = Store.NO, index = Index.TOKENIZED,analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getDeviceNo ()
  {
    return deviceNo;
  }

  public void setDeviceNo (String deviceNo)
  {
    this.deviceNo = deviceNo;
  }
  @JsonProperty
  @Field(store = Store.NO, index = Index.UN_TOKENIZED)
  public DeviceStatus getDeviceStatus ()
  {
    return deviceStatus;
  }

  public void setDeviceStatus (DeviceStatus deviceStatus)
  {
    this.deviceStatus = deviceStatus;
  }
  @JsonProperty
  public String getSimNo ()
  {
    return simNo;
  }

  public void setSimNo (String simNo)
  {
    this.simNo = simNo;
  }
  @JsonProperty
  public DeviceType getType ()
  {
    return type;
  }

  public void setType (DeviceType type)
  {
    this.type = type;
  }

  @OneToOne(mappedBy="device")
  public Vehicle getVehicle ()
  {
    return vehicle;
  }

  public void setVehicle (Vehicle vehicle)
  {
    this.vehicle = vehicle;
  }

  @Field(store = Store.NO, index = Index.UN_TOKENIZED)
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }
	
}