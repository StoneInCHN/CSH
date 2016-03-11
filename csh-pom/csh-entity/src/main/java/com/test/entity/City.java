package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the csh_city database table.
 * 
 */
@Entity
@Table(name="csh_city")
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_CITY_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_CITY_ID_GENERATOR")
	private int id;

	private int grade;

	private String name;

	private String num;

	private int parent;

	private String type;

	public City() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGrade() {
		return this.grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public int getParent() {
		return this.parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}