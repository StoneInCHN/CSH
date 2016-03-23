package com.csh.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

import com.csh.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 设备信息
 * 
 */
@Entity
@Table(name = "csh_report_service_statistics")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_report_service_statistics_sequence")
public class ReportServiceStatistics extends BaseEntity {
  private static final long serialVersionUID = 1L;


 

  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 业务类型名称
   */
  private ServiceCategory serviceCategory;
  /**
   * 总量
   */
  private Double totalCount;
  
  /**
   * 统计周期
   */
  private Date statisticsDate;
  
 @Index(name="service_statistics_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }


  @JsonProperty
  @Temporal (TemporalType.DATE)
  public Date getStatisticsDate ()
  {
    return statisticsDate;
  }

  public void setStatisticsDate (Date statisticsDate)
  {
    this.statisticsDate = statisticsDate;
  }

  @JsonProperty
  public Double getTotalCount ()
  {
    return totalCount;
  }

  public void setTotalCount (Double totalCount)
  {
    this.totalCount = totalCount;
  }

  @JsonProperty
  @ManyToOne(fetch = FetchType.EAGER)
  public ServiceCategory getServiceCategory ()
  {
    return serviceCategory;
  }

  public void setServiceCategory (ServiceCategory serviceCategory)
  {
    this.serviceCategory = serviceCategory;
  }

}
