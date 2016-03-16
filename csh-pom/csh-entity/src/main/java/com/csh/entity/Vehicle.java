package com.csh.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
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
   * 车辆型号
   */
  private VehicleBrandDetail vehicleBrandDetail;

  /**
   * 车架号
   */
  private String vehicleNo; 
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


  private String vin;
  
  /**
   * 仪表盘里程
   */
  private Float dashboardMileage;
  
  /**
   * 仪表盘电压
   */
  private Float dashboardBV;

  /**
   * 仪表盘油量
   */
  private Float dashboradOil;
  
  /**
   * 生产日期
   */
  private Date produceDate;
  
  /**
   * 上牌日期
   */
  private Date plateDate;
  private EndUser endUser;

  private Long tenantID;

  private Set<VehicleMaintain> vehicleMaintain = new HashSet<VehicleMaintain> ();
  
  /**
   * 设备编号，冗余字段，方便查询
   */
  private String deviceNo;
  @JsonProperty
  @ManyToOne(fetch=FetchType.EAGER)
  @IndexedEmbedded
  public VehicleBrandDetail getVehicleBrandDetail ()
  {
    return vehicleBrandDetail;
  }

  public void setVehicleBrandDetail (VehicleBrandDetail vehicleBrandDetail)
  {
    this.vehicleBrandDetail = vehicleBrandDetail;
  }

  public String getColor ()
  {
    return color;
  }

  public void setColor (String color)
  {
    this.color = color;
  }

  @OneToOne(mappedBy="vehicle",cascade=CascadeType.MERGE)
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

  @OneToMany(mappedBy="vehicle")
  public Set<VehicleMaintain> getVehicleMaintain ()
  {
    return vehicleMaintain;
  }

  public void setVehicleMaintain (Set<VehicleMaintain> vehicleMaintain)
  {
    this.vehicleMaintain = vehicleMaintain;
  }

  @JsonProperty
  @Transient
  public String getDeviceNo ()
  {
    return deviceNo;
  }

  public void setDeviceNo (String deviceNo)
  {
    this.deviceNo = deviceNo;
  }

  @JsonProperty
	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	@JsonProperty
	public Float getDashboardMileage() {
		return dashboardMileage;
	}

	public void setDashboardMileage(Float dashboardMileage) {
		this.dashboardMileage = dashboardMileage;
	}
	@JsonProperty
	public Float getDashboardBV() {
		return dashboardBV;
	}

	public void setDashboardBV(Float dashboardBV) {
		this.dashboardBV = dashboardBV;
	}
	@JsonProperty
	public Float getDashboradOil() {
		return dashboradOil;
	}

	public void setDashboradOil(Float dashboradOil) {
		this.dashboradOil = dashboradOil;
	}
	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	public Date getPlateDate() {
		return plateDate;
	}

	public void setPlateDate(Date plateDate) {
		this.plateDate = plateDate;
	}

  
  
}