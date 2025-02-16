package com.csh.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyze;
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
 * 维修预约
 * 
 */
@Indexed(index="repareReservation")
@Entity
@Table (name = "csh_reservation_reservation")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "csh_reservation_reservation_sequence")
public class RepareReservation extends BaseEntity
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

  private String remark;
  @ManyToOne
  @JsonProperty
  @IndexedEmbedded
  public EndUser getEndUser ()
  {
    return endUser;
  }

  public void setEndUser (EndUser endUser)
  {
    this.endUser = endUser;
  }

  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO,analyze = Analyze.NO)
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
  @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO,analyze = Analyze.NO)
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

  @Index (name = "repareReservation_tenantid")
  @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO,analyze = Analyze.NO)
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }
  
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO,analyze = Analyze.NO)
  public ReservationInfoFrom getReservationInfoFrom ()
  {
    return reservationInfoFrom;
  }

  public void setReservationInfoFrom (ReservationInfoFrom reservationInfoFrom)
  {
    this.reservationInfoFrom = reservationInfoFrom;
  }

  @OneToOne(cascade = CascadeType.PERSIST)
  @JsonProperty
  public CarServiceRecord getCarServiceRecord ()
  {
    return carServiceRecord;
  }

  public void setCarServiceRecord (CarServiceRecord carServiceRecord)
  {
    this.carServiceRecord = carServiceRecord;
  }

  public String getRemark ()
  {
    return remark;
  }

  public void setRemark (String remark)
  {
    this.remark = remark;
  }

}
