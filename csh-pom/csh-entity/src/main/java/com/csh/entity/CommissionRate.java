package com.csh.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;

/**
 * 提成比例
 * 
 * @author tanbiao
 *
 */
@Entity
@Table(name = "csh_commission_rate")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_commission_rate_sequence")
public class CommissionRate extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -7066804383753668284L;
  /**
   * 分销商提成
   */
  private Double distributorRate;
  /**
   * 平台提成
   */
  private Double platformRate;

  /**
   * 租户提成
   */
  private Double tenantRate;

  /**
   * 分销商提成从另一分销商下的某租户提成比例
   */
  private Double rateFormOtherTenant;

  public Double getDistributorRate() {
    return distributorRate;
  }

  public void setDistributorRate(Double distributorRate) {
    this.distributorRate = distributorRate;
  }

  public Double getPlatformRate() {
    return platformRate;
  }

  public void setPlatformRate(Double platformRate) {
    this.platformRate = platformRate;
  }

  public Double getTenantRate() {
    return tenantRate;
  }

  public void setTenantRate(Double tenantRate) {
    this.tenantRate = tenantRate;
  }

  public Double getRateFormOtherTenant() {
    return rateFormOtherTenant;
  }

  public void setRateFormOtherTenant(Double rateFormOtherTenant) {
    this.rateFormOtherTenant = rateFormOtherTenant;
  }

}
