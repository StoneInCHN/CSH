package com.csh.entity.estore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import com.csh.entity.base.BaseEntity;

/**
 * Entity - 商品图片
 * 
 */
@Entity
@Table(name = "csh_productImage")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_productImage")
public class ProductImage extends BaseEntity {

	private static final long serialVersionUID = -673883300094536107L;

	/** 标题 */
	private String title;

	/** 原图片 */
	private String source;

	/** 大图片 */
	private String large;

	/** 中图片 */
	private String medium;

	/** 缩略图 */
	private String thumbnail;
	
	/** 手机商品列表图片 */
	private String mobilelist;
	
	/** 手机商品详情图片 */
	private String mobileicon;
	
	/** 手机商品展示图片 */
	private String mobiledisplay;
	
	/** 手机已购买商品展示图片 */
	private String mobilehistory;

	/** 排序 */
	private Integer order;

	/** 文件 */
	private MultipartFile file;

	private Product product;
	
	@ManyToOne
	public Product getProduct ()
  {
    return product;
  }

  public void setProduct (Product product)
  {
    this.product = product;
  }

  /**
	 * 获取标题
	 * 
	 * @return 标题
	 */
	@Length(max = 200)
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 * 
	 * @param title
	 *            标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取原图片
	 * 
	 * @return 原图片
	 */
	public String getSource() {
		return source;
	}

	/**
	 * 设置原图片
	 * 
	 * @param source
	 *            原图片
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * 获取大图片
	 * 
	 * @return 大图片
	 */
	public String getLarge() {
		return large;
	}

	/**
	 * 设置大图片
	 * 
	 * @param large
	 *            大图片
	 */
	public void setLarge(String large) {
		this.large = large;
	}

	/**
	 * 获取中图片
	 * 
	 * @return 中图片
	 */
	public String getMedium() {
		return medium;
	}

	/**
	 * 设置中图片
	 * 
	 * @param medium
	 *            中图片
	 */
	public void setMedium(String medium) {
		this.medium = medium;
	}

	/**
	 * 获取缩略图
	 * 
	 * @return 缩略图
	 */
	public String getThumbnail() {
		return thumbnail;
	}

	/**
	 * 设置缩略图
	 * 
	 * @param thumbnail
	 *            缩略图
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getMobilelist() {
		return mobilelist;
	}

	public void setMobilelist(String mobilelist) {
		this.mobilelist = mobilelist;
	}

	public String getMobileicon() {
		return mobileicon;
	}

	public void setMobileicon(String mobileicon) {
		this.mobileicon = mobileicon;
	}

	public String getMobiledisplay() {
		return mobiledisplay;
	}

	public void setMobiledisplay(String mobiledisplay) {
		this.mobiledisplay = mobiledisplay;
	}

	
	public String getMobilehistory() {
		return mobilehistory;
	}

	public void setMobilehistory(String mobilehistory) {
		this.mobilehistory = mobilehistory;
	}

	/**
	 * 获取排序
	 * 
	 * @return 排序
	 */
	@Min(0)
	@Column(name = "orders")
	public Integer getOrder() {
		return order;
	}

	/**
	 * 设置排序
	 * 
	 * @param order
	 *            排序
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * 获取文件
	 * 
	 * @return 文件
	 */
	@Transient
	public MultipartFile getFile() {
		return file;
	}

	/**
	 * 设置文件
	 * 
	 * @param file
	 *            文件
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}

	/**
	 * 判断是否为空
	 * 
	 * @return 是否为空
	 */
	@Transient
	public boolean isEmpty() {
		return (getFile() == null || getFile().isEmpty()) && (StringUtils.isEmpty(getSource()) || StringUtils.isEmpty(getLarge()) || StringUtils.isEmpty(getMedium()) || StringUtils.isEmpty(getThumbnail()));
	}

	/**
	 * 实现compareTo方法
	 * 
	 * @param productImage
	 *            商品图片
	 * @return 比较结果
	 */
	public int compareTo(ProductImage productImage) {
		return new CompareToBuilder().append(getOrder(), productImage.getOrder()).toComparison();
	}

}