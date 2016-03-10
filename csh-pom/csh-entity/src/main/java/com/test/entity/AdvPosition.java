package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the csh_adv_position database table.
 * 
 */
@Entity
@Table(name="csh_adv_position")
@NamedQuery(name="AdvPosition.findAll", query="SELECT a FROM AdvPosition a")
public class AdvPosition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_ADV_POSITION_APID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_ADV_POSITION_APID_GENERATOR")
	@Column(name="ap_id")
	private int apId;

	@Column(name="add_time")
	private Timestamp addTime;

	private String admin;

	@Column(name="adv_num")
	private int advNum;

	@Column(name="ap_class")
	private int apClass;

	@Column(name="ap_display")
	private int apDisplay;

	@Column(name="ap_height")
	private int apHeight;

	@Column(name="ap_intro")
	private String apIntro;

	@Column(name="ap_name")
	private String apName;

	@Column(name="ap_price")
	private int apPrice;

	@Column(name="ap_width")
	private int apWidth;

	@Column(name="click_num")
	private int clickNum;

	@Column(name="is_use")
	private int isUse;

	//bi-directional many-to-one association to Adv
	@OneToMany(mappedBy="cshAdvPosition")
	private Set<Adv> cshAdvs;

	//bi-directional many-to-one association to AdvClick
	@OneToMany(mappedBy="cshAdvPosition")
	private Set<AdvClick> cshAdvClicks;

	public AdvPosition() {
	}

	public int getApId() {
		return this.apId;
	}

	public void setApId(int apId) {
		this.apId = apId;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public String getAdmin() {
		return this.admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public int getAdvNum() {
		return this.advNum;
	}

	public void setAdvNum(int advNum) {
		this.advNum = advNum;
	}

	public int getApClass() {
		return this.apClass;
	}

	public void setApClass(int apClass) {
		this.apClass = apClass;
	}

	public int getApDisplay() {
		return this.apDisplay;
	}

	public void setApDisplay(int apDisplay) {
		this.apDisplay = apDisplay;
	}

	public int getApHeight() {
		return this.apHeight;
	}

	public void setApHeight(int apHeight) {
		this.apHeight = apHeight;
	}

	public String getApIntro() {
		return this.apIntro;
	}

	public void setApIntro(String apIntro) {
		this.apIntro = apIntro;
	}

	public String getApName() {
		return this.apName;
	}

	public void setApName(String apName) {
		this.apName = apName;
	}

	public int getApPrice() {
		return this.apPrice;
	}

	public void setApPrice(int apPrice) {
		this.apPrice = apPrice;
	}

	public int getApWidth() {
		return this.apWidth;
	}

	public void setApWidth(int apWidth) {
		this.apWidth = apWidth;
	}

	public int getClickNum() {
		return this.clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}

	public int getIsUse() {
		return this.isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}

	public Set<Adv> getCshAdvs() {
		return this.cshAdvs;
	}

	public void setCshAdvs(Set<Adv> cshAdvs) {
		this.cshAdvs = cshAdvs;
	}

	public Adv addCshAdv(Adv cshAdv) {
		getCshAdvs().add(cshAdv);
		cshAdv.setCshAdvPosition(this);

		return cshAdv;
	}

	public Adv removeCshAdv(Adv cshAdv) {
		getCshAdvs().remove(cshAdv);
		cshAdv.setCshAdvPosition(null);

		return cshAdv;
	}

	public Set<AdvClick> getCshAdvClicks() {
		return this.cshAdvClicks;
	}

	public void setCshAdvClicks(Set<AdvClick> cshAdvClicks) {
		this.cshAdvClicks = cshAdvClicks;
	}

	public AdvClick addCshAdvClick(AdvClick cshAdvClick) {
		getCshAdvClicks().add(cshAdvClick);
		cshAdvClick.setCshAdvPosition(this);

		return cshAdvClick;
	}

	public AdvClick removeCshAdvClick(AdvClick cshAdvClick) {
		getCshAdvClicks().remove(cshAdvClick);
		cshAdvClick.setCshAdvPosition(null);

		return cshAdvClick;
	}

}