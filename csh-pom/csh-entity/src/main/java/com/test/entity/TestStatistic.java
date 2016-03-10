package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the test_statistic database table.
 * 
 */
@Entity
@Table(name="test_statistic")
@NamedQuery(name="TestStatistic.findAll", query="SELECT t FROM TestStatistic t")
public class TestStatistic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TEST_STATISTIC_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TEST_STATISTIC_ID_GENERATOR")
	private int id;

	private String day;

	@Column(name="day_money")
	private int dayMoney;

	@Column(name="lyear_money")
	private int lyearMoney;

	private String year;

	@Column(name="year_money")
	private int yearMoney;

	public TestStatistic() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDay() {
		return this.day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getDayMoney() {
		return this.dayMoney;
	}

	public void setDayMoney(int dayMoney) {
		this.dayMoney = dayMoney;
	}

	public int getLyearMoney() {
		return this.lyearMoney;
	}

	public void setLyearMoney(int lyearMoney) {
		this.lyearMoney = lyearMoney;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getYearMoney() {
		return this.yearMoney;
	}

	public void setYearMoney(int yearMoney) {
		this.yearMoney = yearMoney;
	}

}