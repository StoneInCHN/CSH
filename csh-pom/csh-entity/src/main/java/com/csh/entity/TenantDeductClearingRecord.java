package com.csh.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.ClearingStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "csh_tenant_deduct_clearing_record")
@Indexed(index = "tenantDeductClearingRecord")
@SequenceGenerator(name = "sequenceGenerator",
    sequenceName = "csh_tenant_deduct_clearing_record_sequence")
public class TenantDeductClearingRecord extends BaseEntity {
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
   * 本次结算周期金额
   */
  private BigDecimal amountOfCurrentPeriod = new BigDecimal(0);

  /**
   * 关联的CarServiceTenantDeductRecord
   */
  private List<CarServiceTenantDeductRecord> carServiceTenantDeductRecords =
      new ArrayList<CarServiceTenantDeductRecord>();

  /**
   * 租户
   */
  private TenantInfo tenantInfo;
  
  private Long tenantID;

  @JsonProperty
  @Field(store = Store.NO, index = Index.YES,analyze = Analyze.NO)
  @Pattern(regexp = "^[0-9a-zA-Z_-]+$")
  @Length(max = 100)
  @Column(nullable = false, unique = true, length = 100)
  public String getClearingSn() {
    return clearingSn;
  }

  public void setClearingSn(String clearingSn) {
    this.clearingSn = clearingSn;
  }

  public ClearingStatus getClearingStatus() {
    return clearingStatus;
  }

  public void setClearingStatus(ClearingStatus clearingStatus) {
    this.clearingStatus = clearingStatus;
  }


  @JsonProperty
  public BigDecimal getAmountOfCurrentPeriod() {
    return amountOfCurrentPeriod;
  }

  public void setAmountOfCurrentPeriod(BigDecimal amountOfCurrentPeriod)

  {
    this.amountOfCurrentPeriod = amountOfCurrentPeriod;
  }


  @OneToMany(mappedBy = "tenantDeductClearingRecord", fetch = FetchType.EAGER, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST})
  public List<CarServiceTenantDeductRecord> getCarServiceTenantDeductRecords() {
    return carServiceTenantDeductRecords;
  }

  public void setCarServiceTenantDeductRecords(
      List<CarServiceTenantDeductRecord> carServiceTenantDeductRecords) {
    this.carServiceTenantDeductRecords = carServiceTenantDeductRecords;
  }

  @ManyToOne
  public TenantInfo getTenantInfo() {
    return tenantInfo;
  }

  public void setTenantInfo(TenantInfo tenantInfo) {
    this.tenantInfo = tenantInfo;
  }

  @org.hibernate.annotations.Index(name="tenantDeductClearingRecord_tenantid")
  @Field(store = Store.NO, index = Index.YES,analyze = Analyze.NO)
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

}
