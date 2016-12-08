package com.csh.entity.estore;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;

/**
 * Order Relation
 * 
 * @author Andrea
 *
 */
@Entity
@Table(name = "csh_order_relation")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_order_relation_sequence")
public class OrderRelation extends BaseEntity {

  private static final long serialVersionUID = 7282926448628904157L;


  /**
   * 主订单ID
   */
  private Long mainOrderId;

  /**
   * 关联订单ID
   */
  private Long orderId;

  public Long getMainOrderId() {
    return mainOrderId;
  }

  public void setMainOrderId(Long mainOrderId) {
    this.mainOrderId = mainOrderId;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }



}
