package com.csh.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
 * 汽车服务项
 *
 */
@Indexed(index = "carServiceItem")
@Entity
@Table(name = "csh_car_service_item",indexes={@Index(name="carServiceItem_tenantid",columnList="tenantID")})
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_car_service_item_sequence")
public class CarServiceItem extends BaseEntity {

  /**
     *
     */
  private static final long serialVersionUID = 4496524488163375888L;

  private Long tenantID;
  /**
   * 服务项名称
   */
  private String serviceItemName;
  /**
   * 服务项简介
   */
  private String serviceDesc;
  /**
   * 汽车服务
   */
  private CarService carService;
  
  private Set<ItemPart> itemParts = new HashSet<ItemPart> ();

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
  public String getServiceItemName ()
  {
    return serviceItemName;
  }

  public void setServiceItemName (String serviceItemName)
  {
    this.serviceItemName = serviceItemName;
  }

  public String getServiceDesc ()
  {
    return serviceDesc;
  }

  public void setServiceDesc (String serviceDesc)
  {
    this.serviceDesc = serviceDesc;
  }

  @ManyToOne
  @IndexedEmbedded
  public CarService getCarService ()
  {
    return carService;
  }

  public void setCarService (CarService carService)
  {
    this.carService = carService;
  }

  @OneToMany(mappedBy = "carServiceItem")
  public Set<ItemPart> getItemParts ()
  {
    return itemParts;
  }

  public void setItemParts (Set<ItemPart> itemParts)
  {
    this.itemParts = itemParts;
  }

  
}
