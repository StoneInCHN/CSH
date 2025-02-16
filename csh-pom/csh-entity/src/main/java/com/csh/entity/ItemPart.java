package com.csh.entity;


import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.csh.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 服务项配件
 *
 */
@Indexed(index = "itemPart")
@Entity
@Table(name = "csh_item_part" ,indexes={@Index(name="itemPart_tenantid",columnList="tenantID")})
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_item_part_sequence")
public class ItemPart extends BaseEntity {

  /**
     *
     */
  private static final long serialVersionUID = 4496524488163375888L;

  /**
   * 租户ID
   */
  private Long tenantID;
  /**
   * 服务项名称
   */
  private String serviceItemPartName;
  /**
   * 服务项简介
   */
  private String serviceItemPartDesc;
  /**
   * 价格
   */
  private BigDecimal price;
  
  private CarServiceItem carServiceItem;
  /**
   * 是否默认选中
   */
  private Boolean isDefault;
  
  /**
   * 关联车品牌
   */
  private Set<VehicleBrand> vehicleBrands;
  /**
   * 关联车系
   */
  private Set<VehicleLine> vehicleLines;
  /**
   * 关联车型
   */
  private Set<VehicleBrandDetail> vehicleBrandDetails;
  
  @Field(store=Store.NO,index = org.hibernate.search.annotations.Index.YES,analyze=Analyze.NO)
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  @JsonProperty
  @Field(store=Store.NO,index = org.hibernate.search.annotations.Index.YES,analyze=Analyze.NO)
  public String getServiceItemPartName ()
  {
    return serviceItemPartName;
  }

  public void setServiceItemPartName (String serviceItemPartName)
  {
    this.serviceItemPartName = serviceItemPartName;
  }

  public String getServiceItemPartDesc ()
  {
    return serviceItemPartDesc;
  }

  public void setServiceItemPartDesc (String serviceItemPartDesc)
  {
    this.serviceItemPartDesc = serviceItemPartDesc;
  }

  @JsonProperty
  public BigDecimal getPrice ()
  {
    return price;
  }

  public void setPrice (BigDecimal price)
  {
    this.price = price;
  }

  @JsonProperty
  public Boolean getIsDefault ()
  {
    return isDefault;
  }

  public void setIsDefault (Boolean isDefault)
  {
    this.isDefault = isDefault;
  }

  @ManyToMany
  public Set<VehicleBrand> getVehicleBrands ()
  {
    return vehicleBrands;
  }

  public void setVehicleBrands (Set<VehicleBrand> vehicleBrands)
  {
    this.vehicleBrands = vehicleBrands;
  }

  @ManyToMany
  public Set<VehicleLine> getVehicleLines ()
  {
    return vehicleLines;
  }

  public void setVehicleLines (Set<VehicleLine> vehicleLines)
  {
    this.vehicleLines = vehicleLines;
  }

  @ManyToMany
  public Set<VehicleBrandDetail> getVehicleBrandDetails ()
  {
    return vehicleBrandDetails;
  }

  public void setVehicleBrandDetails (Set<VehicleBrandDetail> vehicleBrandDetails)
  {
    this.vehicleBrandDetails = vehicleBrandDetails;
  }

  @ManyToOne
  @IndexedEmbedded
  public CarServiceItem getCarServiceItem ()
  {
    return carServiceItem;
  }

  public void setCarServiceItem (CarServiceItem carServiceItem)
  {
    this.carServiceItem = carServiceItem;
  }

  
}
