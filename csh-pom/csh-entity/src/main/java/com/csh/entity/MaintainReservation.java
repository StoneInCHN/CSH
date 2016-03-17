package com.csh.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;
import com.csh.entity.commonenum.CommonEnum.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 预约中心
 * 
 */
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

  private Long tenantID;

  public EndUser getEndUser ()
  {
    return endUser;
  }

  public void setEndUser (EndUser endUser)
  {
    this.endUser = endUser;
  }

  public Date getReservationDate ()
  {
    return reservationDate;
  }

  public void setReservationDate (Date reservationDate)
  {
    this.reservationDate = reservationDate;
  }

  public String getPlate ()
  {
    return plate;
  }

  public void setPlate (String plate)
  {
    this.plate = plate;
  }

  public String getVehicleBrand ()
  {
    return vehicleBrand;
  }

  public void setVehicleBrand (String vehicleBrand)
  {
    this.vehicleBrand = vehicleBrand;
  }

  @Index (name = "maintainReservation_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

}
