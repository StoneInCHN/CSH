package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the cshm_order_log database table.
 * 
 */
@Entity
@Table(name="cshm_order_log")
@NamedQuery(name="OrderLog.findAll", query="SELECT o FROM OrderLog o")
public class OrderLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSHM_ORDER_LOG_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSHM_ORDER_LOG_ID_GENERATOR")
	private String id;

	@Column(name="changed_status")
	private String changedStatus;

	@Column(name="log_time")
	private Timestamp logTime;

	private String operator;

	@Column(name="order_id")
	private BigInteger orderId;

	@Column(name="order_status")
	private String orderStatus;

	private String remark;

	public OrderLog() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChangedStatus() {
		return this.changedStatus;
	}

	public void setChangedStatus(String changedStatus) {
		this.changedStatus = changedStatus;
	}

	public Timestamp getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Timestamp logTime) {
		this.logTime = logTime;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public BigInteger getOrderId() {
		return this.orderId;
	}

	public void setOrderId(BigInteger orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}