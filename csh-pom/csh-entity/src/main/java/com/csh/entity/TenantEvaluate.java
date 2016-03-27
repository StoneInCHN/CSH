package com.csh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.csh.entity.base.BaseEntity;


/**
 * Entity - 租户评价
 * 
 * @author Andrea
 *
 */
@Entity
@Table(name = "csh_tenant_evaluate")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_tenant_evaluate_sequence")
public class TenantEvaluate extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 4509383295262124028L;



  /** 评价用户 */
  private EndUser endUser;


  /** 得分 */
  private Integer score;

  /**
   * 备注
   */
  private String remark;

  /**
   * 租户ID
   */
  private Long tenantId;

  @ManyToOne
  public EndUser getEndUser() {
    return endUser;
  }

  public void setEndUser(EndUser endUser) {
    this.endUser = endUser;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  @Index(name = "index_evalute_tenantid")
  public Long getTenantId() {
    return tenantId;
  }

  public void setTenantId(Long tenantId) {
    this.tenantId = tenantId;
  }


  @Column(length = 100)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }



}
