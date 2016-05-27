package com.csh.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.ChargeStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.entity.commonenum.CommonEnum.SystemType;
import com.csh.lucene.DateBridgeImpl;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 购买记录配件实例表
 *
 */

@Entity
@Indexed(index = "carServiceRecordPartInst")
@Table(name = "csh_car_service_record_part_inst")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_car_service_record_part_inst_sequence")
public class CarServiceRecordPartInst extends BaseEntity {

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
  
  /**
   * 服务记录
   */
  private CarServiceRecord carServiceRecord;
  
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
  @ManyToOne
  public CarServiceRecord getCarServiceRecord ()
  {
    return carServiceRecord;
  }
  public void setCarServiceRecord (CarServiceRecord carServiceRecord)
  {
    this.carServiceRecord = carServiceRecord;
  }
  
  
}
