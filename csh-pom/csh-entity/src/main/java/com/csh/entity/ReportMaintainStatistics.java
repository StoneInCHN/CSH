package com.csh.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.csh.entity.base.BaseEntity;


/**
 * 设备信息
 * 
 */
@Entity
@Table(name = "csh_report_repare_statistics")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_report_repare_statistics_sequence")
public class ReportMaintainStatistics extends BaseEntity {
  private static final long serialVersionUID = 1L;


 

  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 总收入
   */
  private Double totalIncome;
  
  /**
   * 统计周期
   */
  private Date statisticsDate;
  
 @Index(name="repare_statistics_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  public Double getTotalIncome ()
  {
    return totalIncome;
  }

  public void setTotalIncome (Double totalIncome)
  {
    this.totalIncome = totalIncome;
  }

  public Date getStatisticsDate ()
  {
    return statisticsDate;
  }

  public void setStatisticsDate (Date statisticsDate)
  {
    this.statisticsDate = statisticsDate;
  }

}
