package com.csh.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.ClearingStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table (name = "csh_tenant_deduct_clearing_record")
@Indexed(index="tenantDeductClearingRecord")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "csh_tenant_deduct_clearing_record_sequence")
public class TenantDeductClearingRecord extends BaseEntity
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 结算单号
   */
  private String clearingSn;

  /**
   * 结算状态
   */
  private ClearingStatus clearingStatus;

  /**
   * 本次结算周期开始时间
   */
  private Date periodBeginDate;

  /**
   * 本次结算周期结束时间
   */
  private Date periodEndDate;
  

  /**
   *本次结算周期金额 
   */
  private BigDecimal amountOfCurrentPeriod = new BigDecimal (0);

  /**
   * 扣除金额 比如个人所得税
   */
  private BigDecimal reduce=new BigDecimal(0);
  
  /**
   * 备注，记录扣除原因
   */
  private String comments;
  
  /**
   * 本次结算周期以外的金额
   * 出现本次结算周期以外的结算金额的情况有，   * 每次处理当前结算时候发生了意外的不可预期
   * 的错误导致当前周期结算单位成功生成的情况
   */
  private BigDecimal amountOutOfCurrentPeriod = new BigDecimal (0);

  /**
   *关联的CarServiceRecord 
   */
  private List<CarServiceRecord> carServiceRecords = new ArrayList<CarServiceRecord> ();

  private Long tenantID;
  /**
   *非关联本次结算的OrderRebateRecord
   */
//  private List<OrderRebateRecord> orderRebateRecordsOutOfCurrentPeriod = new ArrayList<OrderRebateRecord> ();


  @JsonProperty
  @Field(store = Store.NO, index = Index.UN_TOKENIZED)
  @Pattern(regexp = "^[0-9a-zA-Z_-]+$")
  @Length(max = 100)
  @Column(nullable = false, unique = true, length = 100)
  public String getClearingSn ()
  {
    return clearingSn;
  }

  public void setClearingSn (String clearingSn)
  {
    this.clearingSn = clearingSn;
  }

  public ClearingStatus getClearingStatus ()
  {
    return clearingStatus;
  }

  public void setClearingStatus (ClearingStatus clearingStatus)
  {
    this.clearingStatus = clearingStatus;
  }
  
  @JsonProperty
  public Date getPeriodBeginDate ()
  {
    return periodBeginDate;
  }

  public void setPeriodBeginDate (Date periodBeginDate)
  {
    this.periodBeginDate = periodBeginDate;
  }
  
  @JsonProperty
  public Date getPeriodEndDate ()
  {
    return periodEndDate;
  }

  public void setPeriodEndDate (Date periodEndDate)
  {
    this.periodEndDate = periodEndDate;
  }

  @JsonProperty
  public BigDecimal getAmountOfCurrentPeriod ()
  {
    return amountOfCurrentPeriod;
  }

  public void setAmountOfCurrentPeriod (BigDecimal amountOfCurrentPeriod)
  
  {
    this.amountOfCurrentPeriod = amountOfCurrentPeriod;
  }
  
  public BigDecimal getReduce() {
		return reduce;
	}

	public void setReduce(BigDecimal reduce) {
		this.reduce = reduce;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
  
  
  public BigDecimal getAmountOutOfCurrentPeriod ()
  {
    return amountOutOfCurrentPeriod;
  }

  public void setAmountOutOfCurrentPeriod (BigDecimal amountOutOfCurrentPeriod)
  {
    this.amountOutOfCurrentPeriod = amountOutOfCurrentPeriod;
  }
   

  @OneToMany(mappedBy = "tenantClearingRecord",fetch=FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
  public List<CarServiceRecord> getCarServiceRecords ()
  {
    return carServiceRecords;
  }

  public void setCarServiceRecords (List<CarServiceRecord> carServiceRecords)
  {
    this.carServiceRecords = carServiceRecords;
  }

  @org.hibernate.annotations.Index(name="tenantDeductClearingRecord_tenantid")
  @Field(store = Store.NO, index = Index.UN_TOKENIZED)
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  
}
