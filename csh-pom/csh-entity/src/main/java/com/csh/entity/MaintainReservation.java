package com.csh.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.ReservationInfoFrom;
import com.csh.lucene.DateBridgeImpl;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 预约中心
 * 
 */
@Indexed(index="maintainReservation")
@Entity
@Table (name = "csh_maintain_reservation")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "csh_maintain_reservation_sequence")
public class MaintainReservation extends BaseEntity
{

  private static final long serialVersionUID = 1L;

  /**
   * 预约人
   */
  private EndUser endUser;

  /**
   * 预约时间
   */
  private Date reservationDate;

  /**
   * 预约车牌号
   */
  private String plate;

  /**
   * 预约车型
   */
  private String vehicleBrand;

  /**
   * 预约信息来源
   */
  private ReservationInfoFrom reservationInfoFrom;
  
  /**
   * 服务记录
   */
  private CarServiceRecord carServiceRecord;
  
  
  private Long tenantID;

  @JsonProperty
  @IndexedEmbedded
  @ManyToOne
  public EndUser getEndUser ()
  {
    return endUser;
  }

  public void setEndUser (EndUser endUser)
  {
    this.endUser = endUser;
  }

  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @FieldBridge(impl = DateBridgeImpl.class)
  public Date getReservationDate ()
  {
    return reservationDate;
  }

  public void setReservationDate (Date reservationDate)
  {
    this.reservationDate = reservationDate;
  }
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.TOKENIZED, store = Store.NO,analyzer=@Analyzer(impl=IKAnalyzer.class))
  public String getPlate ()
  {
    return plate;
  }

  public void setPlate (String plate)
  {
    this.plate = plate;
  }

  @JsonProperty
  public String getVehicleBrand ()
  {
    return vehicleBrand;
  }

  public void setVehicleBrand (String vehicleBrand)
  {
    this.vehicleBrand = vehicleBrand;
  }

  @Index (name = "maintainReservation_tenantid")
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  public ReservationInfoFrom getReservationInfoFrom ()
  {
    return reservationInfoFrom;
  }

  public void setReservationInfoFrom (ReservationInfoFrom reservationInfoFrom)
  {
    this.reservationInfoFrom = reservationInfoFrom;
  }

  @OneToOne(cascade=CascadeType.PERSIST)
  public CarServiceRecord getCarServiceRecord ()
  {
    return carServiceRecord;
  }

  public void setCarServiceRecord (CarServiceRecord carServiceRecord)
  {
    this.carServiceRecord = carServiceRecord;
  }

}
