package com.csh.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.csh.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 设备信息
 * 
 */
@Entity
@Table(name = "csh_report_reservation_statistics")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_report_reservation_statistics_sequence")
public class ReportReservationStatistics extends BaseEntity {
  private static final long serialVersionUID = 1L;


 

  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 预约维修数
   */
  private Integer reservationRepareCount;
  
  /**
   * 完成数
   */
  private Integer paidRepareCount;
  /**
   * 预约维修数
   */
  private Integer reservationMaitainCount;
  
  /**
   * 完成数
   */
  private Integer paidMaintainCount;

  @Index(name="reportReservation_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  @JsonProperty
  public Integer getReservationRepareCount ()
  {
    return reservationRepareCount;
  }

  public void setReservationRepareCount (Integer reservationRepareCount)
  {
    this.reservationRepareCount = reservationRepareCount;
  }

  @JsonProperty
  public Integer getPaidRepareCount ()
  {
    return paidRepareCount;
  }

  public void setPaidRepareCount (Integer paidRepareCount)
  {
    this.paidRepareCount = paidRepareCount;
  }

  @JsonProperty
  public Integer getReservationMaitainCount ()
  {
    return reservationMaitainCount;
  }

  public void setReservationMaitainCount (Integer reservationMaitainCount)
  {
    this.reservationMaitainCount = reservationMaitainCount;
  }

  @JsonProperty
  public Integer getPaidMaintainCount ()
  {
    return paidMaintainCount;
  }

  public void setPaidMaintainCount (Integer paidMaintainCount)
  {
    this.paidMaintainCount = paidMaintainCount;
  }
  
  
}
