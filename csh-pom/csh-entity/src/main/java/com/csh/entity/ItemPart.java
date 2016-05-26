package com.csh.entity;


import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

import com.csh.entity.base.BaseEntity;


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
  
  private CarServiceItemPartsMap carServiceItemPartsMap;

  /**
   * 是否默认选中
   */
  private Boolean isDefault;
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

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

  public BigDecimal getPrice ()
  {
    return price;
  }

  public void setPrice (BigDecimal price)
  {
    this.price = price;
  }

  @OneToOne
  public CarServiceItemPartsMap getCarServiceItemPartsMap ()
  {
    return carServiceItemPartsMap;
  }

  public void setCarServiceItemPartsMap (
      CarServiceItemPartsMap carServiceItemPartsMap)
  {
    this.carServiceItemPartsMap = carServiceItemPartsMap;
  }

  public Boolean getIsDefault ()
  {
    return isDefault;
  }

  public void setIsDefault (Boolean isDefault)
  {
    this.isDefault = isDefault;
  }
  
}
