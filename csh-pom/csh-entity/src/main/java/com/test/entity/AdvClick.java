package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the csh_adv_click database table.
 * 
 */
@Entity
@Table(name="csh_adv_click")
@NamedQuery(name="AdvClick.findAll", query="SELECT a FROM AdvClick a")
public class AdvClick implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="adv_name")
	private String advName;

	@Column(name="ap_name")
	private String apName;

	@Column(name="click_month")
	private int clickMonth;

	@Column(name="click_num")
	private int clickNum;

	@Column(name="click_year")
	private int clickYear;

	//bi-directional many-to-one association to Adv
	@ManyToOne
	@JoinColumn(name="adv_id")
	private Adv cshAdv;

	//bi-directional many-to-one association to AdvPosition
	@ManyToOne
	@JoinColumn(name="ap_id")
	private AdvPosition cshAdvPosition;

	public AdvClick() {
	}

	public String getAdvName() {
		return this.advName;
	}

	public void setAdvName(String advName) {
		this.advName = advName;
	}

	public String getApName() {
		return this.apName;
	}

	public void setApName(String apName) {
		this.apName = apName;
	}

	public int getClickMonth() {
		return this.clickMonth;
	}

	public void setClickMonth(int clickMonth) {
		this.clickMonth = clickMonth;
	}

	public int getClickNum() {
		return this.clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}

	public int getClickYear() {
		return this.clickYear;
	}

	public void setClickYear(int clickYear) {
		this.clickYear = clickYear;
	}

	public Adv getCshAdv() {
		return this.cshAdv;
	}

	public void setCshAdv(Adv cshAdv) {
		this.cshAdv = cshAdv;
	}

	public AdvPosition getCshAdvPosition() {
		return this.cshAdvPosition;
	}

	public void setCshAdvPosition(AdvPosition cshAdvPosition) {
		this.cshAdvPosition = cshAdvPosition;
	}

}