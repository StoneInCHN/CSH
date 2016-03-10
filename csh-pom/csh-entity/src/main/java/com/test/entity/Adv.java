package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the csh_adv database table.
 * 
 */
@Entity
@Table(name="csh_adv")
@NamedQuery(name="Adv.findAll", query="SELECT a FROM Adv a")
public class Adv implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_ADV_ADVID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_ADV_ADVID_GENERATOR")
	@Column(name="adv_id")
	private int advId;

	@Column(name="add_time")
	private Timestamp addTime;

	private String admin;

	@Column(name="adv_content")
	private String advContent;

	@Column(name="adv_end_date")
	private Timestamp advEndDate;

	@Column(name="adv_image")
	private String advImage;

	@Column(name="adv_start_date")
	private Timestamp advStartDate;

	@Column(name="adv_title")
	private String advTitle;

	@Column(name="adv_type")
	private int advType;

	@Column(name="click_num")
	private int clickNum;

	private int goldpay;

	@Column(name="is_allow")
	private int isAllow;

	@Column(name="slide_sort")
	private int slideSort;

	//bi-directional many-to-one association to AdvPosition
	@ManyToOne
	@JoinColumn(name="ap_id")
	private AdvPosition cshAdvPosition;

	//bi-directional many-to-one association to AdvClick
	@OneToMany(mappedBy="cshAdv")
	private Set<AdvClick> cshAdvClicks;

	public Adv() {
	}

	public int getAdvId() {
		return this.advId;
	}

	public void setAdvId(int advId) {
		this.advId = advId;
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

	public String getAdvContent() {
		return this.advContent;
	}

	public void setAdvContent(String advContent) {
		this.advContent = advContent;
	}

	public Timestamp getAdvEndDate() {
		return this.advEndDate;
	}

	public void setAdvEndDate(Timestamp advEndDate) {
		this.advEndDate = advEndDate;
	}

	public String getAdvImage() {
		return this.advImage;
	}

	public void setAdvImage(String advImage) {
		this.advImage = advImage;
	}

	public Timestamp getAdvStartDate() {
		return this.advStartDate;
	}

	public void setAdvStartDate(Timestamp advStartDate) {
		this.advStartDate = advStartDate;
	}

	public String getAdvTitle() {
		return this.advTitle;
	}

	public void setAdvTitle(String advTitle) {
		this.advTitle = advTitle;
	}

	public int getAdvType() {
		return this.advType;
	}

	public void setAdvType(int advType) {
		this.advType = advType;
	}

	public int getClickNum() {
		return this.clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}

	public int getGoldpay() {
		return this.goldpay;
	}

	public void setGoldpay(int goldpay) {
		this.goldpay = goldpay;
	}

	public int getIsAllow() {
		return this.isAllow;
	}

	public void setIsAllow(int isAllow) {
		this.isAllow = isAllow;
	}

	public int getSlideSort() {
		return this.slideSort;
	}

	public void setSlideSort(int slideSort) {
		this.slideSort = slideSort;
	}

	public AdvPosition getCshAdvPosition() {
		return this.cshAdvPosition;
	}

	public void setCshAdvPosition(AdvPosition cshAdvPosition) {
		this.cshAdvPosition = cshAdvPosition;
	}

	public Set<AdvClick> getCshAdvClicks() {
		return this.cshAdvClicks;
	}

	public void setCshAdvClicks(Set<AdvClick> cshAdvClicks) {
		this.cshAdvClicks = cshAdvClicks;
	}

	public AdvClick addCshAdvClick(AdvClick cshAdvClick) {
		getCshAdvClicks().add(cshAdvClick);
		cshAdvClick.setCshAdv(this);

		return cshAdvClick;
	}

	public AdvClick removeCshAdvClick(AdvClick cshAdvClick) {
		getCshAdvClicks().remove(cshAdvClick);
		cshAdvClick.setCshAdv(null);

		return cshAdvClick;
	}

}