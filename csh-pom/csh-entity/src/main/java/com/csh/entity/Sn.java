package com.csh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;

/**
 * Entity - 序列号
 * 
 * @author
 * @version
 */
@Entity
@Table(name = "csh_sn")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_sn_sequence")
public class Sn extends BaseEntity {

  private static final long serialVersionUID = -2330598144835706164L;

  /**
   * 类型
   */
  public enum Type {

    /** 商品 */
    product,
    /**
     * 结算单
     */
    clearing,
    /**
     * 提成结算单
     */
    deductClearing,

    /** 退款单 */
    refunds,

    /** 发货单 */
    shipping,

    /** 退货单 */
    returns,

    /** 订单 */
    order
  }

  /** 类型 */
  private Type type;

  /** 末值 */
  private Long lastValue;

  /**
   * 获取类型
   * 
   * @return 类型
   */
  @Column(nullable = false, updatable = false, unique = true)
  public Type getType() {
    return type;
  }

  /**
   * 设置类型
   * 
   * @param type 类型
   */
  public void setType(Type type) {
    this.type = type;
  }

  /**
   * 获取末值
   * 
   * @return 末值
   */
  @Column(nullable = false)
  public Long getLastValue() {
    return lastValue;
  }

  /**
   * 设置末值
   * 
   * @param lastValue 末值
   */
  public void setLastValue(Long lastValue) {
    this.lastValue = lastValue;
  }

}
