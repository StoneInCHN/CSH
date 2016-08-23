package com.csh.entity.estore;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.base.OrderEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity - 品牌
 * @author huyong
 */
@Indexed(index="brand")
@Entity
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_brand_sequence")
@Table(name = "csh_brand")
public class Brand extends OrderEntity {

	private static final long serialVersionUID = -6109590619136943215L;

	/** 名称 */
	private String name;


	/** logo */
	private String logo;

	/** 网址 */
	private String url;

	/** 介绍 */
	private String introduction;

	/** 商品 */
	private Set<Product> products = new HashSet<Product>();

	/** 商品分类 */
	private Set<ProductCategory> productCategories = new HashSet<ProductCategory>();

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@NotEmpty
	@Length(max = 200)
	@Column(nullable = false)
	@JsonProperty
	@Field(index=org.hibernate.search.annotations.Index.YES,store=Store.NO,analyze=Analyze.NO)
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * 获取logo
	 * 
	 * @return logo
	 */
	@Length(max = 200)
	public String getLogo() {
		return logo;
	}

	/**
	 * 设置logo
	 * 
	 * @param logo
	 *            logo
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * 获取网址
	 * 
	 * @return 网址
	 */
	@Length(max = 200)
	@JsonProperty
	public String getUrl() {
		return url;
	}

	/**
	 * 设置网址
	 * 
	 * @param url
	 *            网址
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取介绍
	 * 
	 * @return 介绍
	 */
	@Lob
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * 设置介绍
	 * 
	 * @param introduction
	 *            介绍
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	/**
	 * 获取商品
	 * 
	 * @return 商品
	 */
	@OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
	public Set<Product> getProducts() {
		return products;
	}

	/**
	 * 设置商品
	 * 
	 * @param products
	 *            商品
	 */
	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	/**
	 * 获取商品分类
	 * 
	 * @return 商品分类
	 */
	@ManyToMany(mappedBy = "brands", fetch = FetchType.LAZY)
	@OrderBy("order asc")
	public Set<ProductCategory> getProductCategories() {
		return productCategories;
	}

	/**
	 * 设置商品分类
	 * 
	 * @param productCategories
	 *            商品分类
	 */
	public void setProductCategories(Set<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}

  /**
	 * 删除前处理
	 */
	@PreRemove
	public void preRemove() {
		Set<Product> products = getProducts();
		if (products != null) {
			for (Product product : products) {
				product.setBrand(null);
			}
		}
		Set<ProductCategory> productCategories = getProductCategories();
		if (productCategories != null) {
			for (ProductCategory productCategory : productCategories) {
				productCategory.getBrands().remove(this);
			}
		}
//		Set<Promotion> promotions = getPromotions();
//		if (promotions != null) {
//			for (Promotion promotion : promotions) {
//				promotion.getBrands().remove(this);
//			}
//		}
	}

}