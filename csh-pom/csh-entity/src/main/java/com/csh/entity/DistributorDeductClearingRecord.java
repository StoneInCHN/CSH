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

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.ClearingStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "csh_distributor_deduct_clearing_record")
@Indexed(index = "distributorDeductClearingRecord")
@SequenceGenerator(name = "sequenceGenerator",
    sequenceName = "csh_distributor_deduct_clearing_record_sequence")
public class DistributorDeductClearingRecord extends BaseEntity {
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
   * 关联的CarServiceDistributorDeductRecord
   */
  private List<CarServiceDistributorDeductRecord> carServiceDistributorDeductRecords =
      new ArrayList<CarServiceDistributorDeductRecord>();


  /**
   * 分销商
   */
  private Distributor distributor;



  @JsonProperty
  @Field(store = Store.NO, index = Index.UN_TOKENIZED)
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

  public BigDecimal getAmountOfCurrentPeriod() {
    return amountOfCurrentPeriod;
  }

  public void setAmountOfCurrentPeriod(BigDecimal amountOfCurrentPeriod)

  {
    this.amountOfCurrentPeriod = amountOfCurrentPeriod;
  }

  @ManyToOne
  public Distributor getDistributor() {
    return distributor;
  }

  public void setDistributor(Distributor distributor) {
    this.distributor = distributor;
  }

  @OneToMany(mappedBy = "distributorDeductClearingRecord", fetch = FetchType.EAGER, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST})
  public List<CarServiceDistributorDeductRecord> getCarServiceDistributorDeductRecords() {
    return carServiceDistributorDeductRecords;
  }

  public void setCarServiceDistributorDeductRecords(
      List<CarServiceDistributorDeductRecord> carServiceDistributorDeductRecords) {
    this.carServiceDistributorDeductRecords = carServiceDistributorDeductRecords;
  }

}
