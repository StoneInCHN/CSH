package com.csh.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.csh.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 预约报表数据
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
  private Integer reservationBeautifyCount;
  
  /**
   * 完成数
   */
  private Integer paidBeautifyCount;
  /**
   * 预约维修数
   */
  private Integer reservationMaitainCount;
  
  /**
   * 完成数
   */
  private Integer paidMaintainCount;

  public ReportReservationStatistics ()
  {
    this.reservationMaitainCount = 0;
    this.reservationBeautifyCount = 0;
    this.reservationRepareCount = 0;
    this.paidMaintainCount = 0;
    this.paidBeautifyCount = 0;
    this.paidRepareCount = 0;
  }
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
  @JsonProperty
  public Integer getReservationBeautifyCount ()
  {
    return reservationBeautifyCount;
  }
  public void setReservationBeautifyCount (Integer reservationBeautifyCount)
  {
    this.reservationBeautifyCount = reservationBeautifyCount;
  }
  @JsonProperty
  public Integer getPaidBeautifyCount ()
  {
    return paidBeautifyCount;
  }
  public void setPaidBeautifyCount (Integer paidBeautifyCount)
  {
    this.paidBeautifyCount = paidBeautifyCount;
  }
  
  
}
