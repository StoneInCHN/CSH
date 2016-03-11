package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the xa_account database table.
 * 
 */
@Entity
@Table(name="xa_account")
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="XA_ACCOUNT_AID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="XA_ACCOUNT_AID_GENERATOR")
	private String aid;

	private double calling;

	private double cash;

	private double freeze;

	private int insurance;

	private String note;

	private String overdue;

	private String time;

	private double total;

	private BigInteger uid;

	public Account() {
	}

	public String getAid() {
		return this.aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public double getCalling() {
		return this.calling;
	}

	public void setCalling(double calling) {
		this.calling = calling;
	}

	public double getCash() {
		return this.cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getFreeze() {
		return this.freeze;
	}

	public void setFreeze(double freeze) {
		this.freeze = freeze;
	}

	public int getInsurance() {
		return this.insurance;
	}

	public void setInsurance(int insurance) {
		this.insurance = insurance;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOverdue() {
		return this.overdue;
	}

	public void setOverdue(String overdue) {
		this.overdue = overdue;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public BigInteger getUid() {
		return this.uid;
	}

	public void setUid(BigInteger uid) {
		this.uid = uid;
	}

}