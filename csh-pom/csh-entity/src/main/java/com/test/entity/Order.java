package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the cshm_order database table.
 * 
 */
@Entity
@Table(name="cshm_order")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSHM_ORDER_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSHM_ORDER_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	@Column(name="amount_paid")
	private double amountPaid;

	private byte anonymous;

	@Column(name="buyer_email")
	private String buyerEmail;

	@Column(name="buyer_id")
	private BigInteger buyerId;

	@Column(name="buyer_name")
	private String buyerName;

	@Column(name="cancel_reasons")
	private String cancelReasons;

	private double discount;

	@Column(name="evaluation_status")
	private byte evaluationStatus;

	@Column(name="evaluation_time")
	private Timestamp evaluationTime;

	private String extension;

	@Column(name="finished_time")
	private Timestamp finishedTime;

	@Column(name="flow_type")
	private int flowType;

	@Column(name="goods_amount")
	private double goodsAmount;

	@Column(name="invoice_no")
	private String invoiceNo;

	@Column(name="order_amount")
	private double orderAmount;

	@Column(name="order_sn")
	private String orderSn;

	@Column(name="out_trade_sn")
	private String outTradeSn;

	@Column(name="pay_alter")
	private byte payAlter;

	@Column(name="pay_message")
	private String payMessage;

	@Column(name="pay_time")
	private Timestamp payTime;

	@Column(name="payment_code")
	private String paymentCode;

	@Column(name="payment_id")
	private int paymentId;

	@Column(name="payment_name")
	private String paymentName;

	private String postscript;

	@Column(name="seller_id")
	private int sellerId;

	@Column(name="seller_name")
	private String sellerName;

	@Column(name="service_time")
	private Timestamp serviceTime;

	@Column(name="ship_time")
	private Timestamp shipTime;

	private String status;

	private String type;

	@Column(name="vehicle_describe")
	private String vehicleDescribe;

	@Column(name="vehicle_id")
	private BigInteger vehicleId;

	public Order() {
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

	public double getAmountPaid() {
		return this.amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public byte getAnonymous() {
		return this.anonymous;
	}

	public void setAnonymous(byte anonymous) {
		this.anonymous = anonymous;
	}

	public String getBuyerEmail() {
		return this.buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public BigInteger getBuyerId() {
		return this.buyerId;
	}

	public void setBuyerId(BigInteger buyerId) {
		this.buyerId = buyerId;
	}

	public String getBuyerName() {
		return this.buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getCancelReasons() {
		return this.cancelReasons;
	}

	public void setCancelReasons(String cancelReasons) {
		this.cancelReasons = cancelReasons;
	}

	public double getDiscount() {
		return this.discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public byte getEvaluationStatus() {
		return this.evaluationStatus;
	}

	public void setEvaluationStatus(byte evaluationStatus) {
		this.evaluationStatus = evaluationStatus;
	}

	public Timestamp getEvaluationTime() {
		return this.evaluationTime;
	}

	public void setEvaluationTime(Timestamp evaluationTime) {
		this.evaluationTime = evaluationTime;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Timestamp getFinishedTime() {
		return this.finishedTime;
	}

	public void setFinishedTime(Timestamp finishedTime) {
		this.finishedTime = finishedTime;
	}

	public int getFlowType() {
		return this.flowType;
	}

	public void setFlowType(int flowType) {
		this.flowType = flowType;
	}

	public double getGoodsAmount() {
		return this.goodsAmount;
	}

	public void setGoodsAmount(double goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public double getOrderAmount() {
		return this.orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderSn() {
		return this.orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getOutTradeSn() {
		return this.outTradeSn;
	}

	public void setOutTradeSn(String outTradeSn) {
		this.outTradeSn = outTradeSn;
	}

	public byte getPayAlter() {
		return this.payAlter;
	}

	public void setPayAlter(byte payAlter) {
		this.payAlter = payAlter;
	}

	public String getPayMessage() {
		return this.payMessage;
	}

	public void setPayMessage(String payMessage) {
		this.payMessage = payMessage;
	}

	public Timestamp getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}

	public String getPaymentCode() {
		return this.paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public int getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentName() {
		return this.paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public String getPostscript() {
		return this.postscript;
	}

	public void setPostscript(String postscript) {
		this.postscript = postscript;
	}

	public int getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerName() {
		return this.sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Timestamp getServiceTime() {
		return this.serviceTime;
	}

	public void setServiceTime(Timestamp serviceTime) {
		this.serviceTime = serviceTime;
	}

	public Timestamp getShipTime() {
		return this.shipTime;
	}

	public void setShipTime(Timestamp shipTime) {
		this.shipTime = shipTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVehicleDescribe() {
		return this.vehicleDescribe;
	}

	public void setVehicleDescribe(String vehicleDescribe) {
		this.vehicleDescribe = vehicleDescribe;
	}

	public BigInteger getVehicleId() {
		return this.vehicleId;
	}

	public void setVehicleId(BigInteger vehicleId) {
		this.vehicleId = vehicleId;
	}

}