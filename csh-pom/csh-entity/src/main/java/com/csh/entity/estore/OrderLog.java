package com.csh.entity.estore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.OrderLogType;

/**
 * Entity - 订单日志
 * 
 */
@Entity
@Table(name = "csh_order_log",indexes={@Index(name="orderLog_tenantid",columnList="tenantID")})
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_order_log_sequence")
public class OrderLog extends BaseEntity {

	private static final long serialVersionUID = -2704154761295319939L;



	/** 类型 */
	private OrderLogType type;

	/** 操作员 */
	private String operator;

	/** 内容 */
	private String content;

	/** 订单 */
	private Order order;
	/**
   * 租户ID
   */
  private Long tenantID;
  
  @Field(index=org.hibernate.search.annotations.Index.YES,analyze=Analyze.NO,store=Store.NO)
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }
	public OrderLog() {
	}

	public OrderLog(OrderLogType type, String operator) {
		this.type = type;
		this.operator = operator;
	}

	public OrderLog(OrderLogType type, String operator, String content) {
		this.type = type;
		this.operator = operator;
		this.content = content;
	}

	/**
	 * 获取类型
	 * 
	 * @return 类型
	 */
	@Column(nullable = false, updatable = false)
	public OrderLogType getType() {
		return type;
	}

	/**
	 * 设置类型
	 * 
	 * @param type
	 *            类型
	 */
	public void setType(OrderLogType type) {
		this.type = type;
	}

	/**
	 * 获取操作员
	 * 
	 * @return 操作员
	 */
	@Column(updatable = false)
	public String getOperator() {
		return operator;
	}

	/**
	 * 设置操作员
	 * 
	 * @param operator
	 *            操作员
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */
	@Column(updatable = false)
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 * 
	 * @param content
	 *            内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取订单
	 * 
	 * @return 订单
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orders", nullable = false, updatable = false)
	public Order getOrder() {
		return order;
	}

	/**
	 * 设置订单
	 * 
	 * @param order
	 *            订单
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

}