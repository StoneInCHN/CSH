package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the csh_pay_charge database table.
 * 
 */
@Entity
@Table(name="csh_pay_charge")
@NamedQuery(name="PayCharge.findAll", query="SELECT p FROM PayCharge p")
public class PayCharge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_PAY_CHARGE_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_PAY_CHARGE_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private int amount;

	private String body;

	private String channel;

	private String created;

	private String currency;

	private String description;

	@Column(name="failure_code")
	private String failureCode;

	@Column(name="failure_msg")
	private String failureMsg;

	private int money;

	@Column(name="order_no")
	private String orderNo;

	private int paid;

	private int red;

	private String subject;

	@Column(name="time_expire")
	private String timeExpire;

	@Column(name="time_paid")
	private String timePaid;

	@Column(name="transaction_no")
	private String transactionNo;

	public PayCharge() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCreated() {
		return this.created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFailureCode() {
		return this.failureCode;
	}

	public void setFailureCode(String failureCode) {
		this.failureCode = failureCode;
	}

	public String getFailureMsg() {
		return this.failureMsg;
	}

	public void setFailureMsg(String failureMsg) {
		this.failureMsg = failureMsg;
	}

	public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getPaid() {
		return this.paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}

	public int getRed() {
		return this.red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTimeExpire() {
		return this.timeExpire;
	}

	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}

	public String getTimePaid() {
		return this.timePaid;
	}

	public void setTimePaid(String timePaid) {
		this.timePaid = timePaid;
	}

	public String getTransactionNo() {
		return this.transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

}