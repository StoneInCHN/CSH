package com.csh.entity.estore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.csh.entity.base.OrderEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity - 商品分类
 * 
 */
@Indexed(index="productCategory")
@Entity
@Table(name = "csh_product_category",indexes={@javax.persistence.Index(name="productCategory_name",columnList="name")})
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_product_category_sequence")
public class ProductCategory extends OrderEntity {

	private static final long serialVersionUID = 5095521437302782717L;

	/** 树路径分隔符 */
	public static final String TREE_PATH_SEPARATOR = ",";

	/** 访问路径前缀 */
	private static final String PATH_PREFIX = "/product/list";

	/** 访问路径后缀 */
	private static final String PATH_SUFFIX = ".jhtml";

	/** 名称 */
	private String name;
	
	/** 树路径 */
	private String treePath;

	/** 层级 */
	private Integer grade;

	/** 上级分类 */
	private ProductCategory parent;

	/** 下级分类 */
	private Set<ProductCategory> children = new HashSet<ProductCategory>();

	/** 商品 */
	private Set<Product> products = new HashSet<Product>();

	/** 筛选品牌 */
	private Set<Brand> brands = new HashSet<Brand>();

	/** 筛选属性 */
	private Set<Attribute> attributes = new HashSet<Attribute>();

	/** 参数组 */
  private Set<ParameterGroup> parameterGroups = new HashSet<ParameterGroup>();
 
	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@NotEmpty
	@Length(max = 200)
	@Column(nullable = false)
	@JsonProperty
	@Field(index = Index.YES,analyze=Analyze.NO,store=Store.NO)
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
	 * 获取树路径
	 * 
	 * @return 树路径
	 */
	@Column(nullable = false)
	public String getTreePath() {
		return treePath;
	}

	/**
	 * 设置树路径
	 * 
	 * @param treePath
	 *            树路径
	 */
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	/**
	 * 获取层级
	 * 
	 * @return 层级
	 */
	@Column(nullable = false)
	@JsonProperty
	public Integer getGrade() {
		return grade;
	}

	/**
	 * 设置层级
	 * 
	 * @param grade
	 *            层级
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * 获取上级分类
	 * 
	 * @return 上级分类
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	public ProductCategory getParent() {
		return parent;
	}

	/**
	 * 设置上级分类
	 * 
	 * @param parent
	 *            上级分类
	 */
	public void setParent(ProductCategory parent) {
		this.parent = parent;
	}

	/**
	 * 获取下级分类
	 * 
	 * @return 下级分类
	 */
	@JsonProperty
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	public Set<ProductCategory> getChildren() {
		return children;
	}

	/**
	 * 设置下级分类
	 * 
	 * @param children
	 *            下级分类
	 */
	public void setChildren(Set<ProductCategory> children) {
		this.children = children;
	}

	/**
	 * 获取商品
	 * 
	 * @return 商品
	 */
	@OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY)
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
	 * 获取筛选品牌
	 * 
	 * @return 筛选品牌
	 */
	@ManyToMany(fetch = FetchType.LAZY,cascade=CascadeType.PERSIST)
	@JoinTable(name = "csh_product_category_brand")
	@OrderBy("order asc")
	public Set<Brand> getBrands() {
		return brands;
	}

	/**
	 * 设置筛选品牌
	 * 
	 * @param brands
	 *            筛选品牌
	 */
	public void setBrands(Set<Brand> brands) {
		this.brands = brands;
	}


	/**
	 * 获取筛选属性
	 * 
	 * @return 筛选属性
	 */
	@OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OrderBy("order asc")
	public Set<Attribute> getAttributes() {
		return attributes;
	}

	/**
	 * 设置筛选属性
	 * 
	 * @param attributes
	 *            筛选属性
	 */
	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}


	/**
	 * 获取树路径
	 * 
	 * @return 树路径
	 */
	@Transient
	public List<Long> getTreePaths() {
		List<Long> treePaths = new ArrayList<Long>();
		String[] ids = StringUtils.split(getTreePath(), TREE_PATH_SEPARATOR);
		if (ids != null) {
			for (String id : ids) {
				treePaths.add(Long.valueOf(id));
			}
		}
		return treePaths;
	}

	/**
	 * 获取访问路径
	 * 
	 * @return 访问路径
	 */
	@Transient
	public String getPath() {
		if (getId() != null) {
			return PATH_PREFIX + "/" + getId() + PATH_SUFFIX;
		}
		return null;
	}
  /**
   * 获取参数组
   * 
   * @return 参数组
   */
  @OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @OrderBy("order asc")
  public Set<ParameterGroup> getParameterGroups() {
    return parameterGroups;
  }

  /**
   * 设置参数组
   * 
   * @param parameterGroups
   *            参数组
   */
  public void setParameterGroups(Set<ParameterGroup> parameterGroups) {
    this.parameterGroups = parameterGroups;
  }
	/**
	 * 删除前处理
	 */
//	@PreRemove
//	public void preRemove() {
//		Set<Promotion> promotions = getPromotions();
//		if (promotions != null) {
//			for (Promotion promotion : promotions) {
//				promotion.getProductCategories().remove(this);
//			}
//		}
//	}

}