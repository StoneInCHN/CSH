package com.csh.entity;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

import com.csh.entity.base.BaseEntity;


/**
 * 汽车服务项与配件中间表
 *
 */
@Indexed(index = "carServiceItemPartsMap")
@Entity
@Table(name = "csh_car_service_item_parts_map")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_car_service_item_parts_map_sequence")
public class CarServiceItemPartsMap extends BaseEntity {

    /**
     *
     */
  private static final long serialVersionUID = 4496524488163375888L;

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
  
  /**
   * 配件
   */
  private ItemPart itemParts;
  
  /**
   * 服务项
   */
  private CarServiceItem carServiceItem;

  @OneToMany
  public Set<VehicleBrand> getVehicleBrands ()
  {
    return vehicleBrands;
  }

  public void setVehicleBrands (Set<VehicleBrand> vehicleBrands)
  {
    this.vehicleBrands = vehicleBrands;
  }

  @OneToMany
  public Set<VehicleLine> getVehicleLines ()
  {
    return vehicleLines;
  }

  public void setVehicleLines (Set<VehicleLine> vehicleLines)
  {
    this.vehicleLines = vehicleLines;
  }
  @OneToMany
  public Set<VehicleBrandDetail> getVehicleBrandDetails ()
  {
    return vehicleBrandDetails;
  }

  public void setVehicleBrandDetails (Set<VehicleBrandDetail> vehicleBrandDetails)
  {
    this.vehicleBrandDetails = vehicleBrandDetails;
  }

  @OneToOne(mappedBy="carServiceItemPartsMap")
  public ItemPart getItemParts ()
  {
    return itemParts;
  }

  public void setItemParts (ItemPart itemParts)
  {
    this.itemParts = itemParts;
  }

  @ManyToOne
  public CarServiceItem getCarServiceItem ()
  {
    return carServiceItem;
  }

  public void setCarServiceItem (CarServiceItem carServiceItem)
  {
    this.carServiceItem = carServiceItem;
  }
  
}
