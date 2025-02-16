package com.csh.dao.impl; 

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.csh.dao.ProductCategoryDao;
import com.csh.entity.estore.ProductCategory;
import com.csh.framework.dao.impl.BaseDaoImpl;
@Repository("productCategoryDaoImpl")
public class ProductCategoryDaoImpl extends  BaseDaoImpl<ProductCategory,Long> implements ProductCategoryDao {

  public List<ProductCategory> findRoots(Integer count,Long tenantId) {
    String jpql = "select productCategory from ProductCategory productCategory where productCategory.parent is null "
        +" and tenantID = "+  tenantId
        +" order by productCategory.order asc";
    TypedQuery<ProductCategory> query = entityManager.createQuery(jpql, ProductCategory.class).setFlushMode(FlushModeType.COMMIT);
    if (count != null) {
      query.setMaxResults(count);
    }
    return query.getResultList();
  }
  public List<ProductCategory> findRoots(Integer count) {
    String jpql = "select productCategory from ProductCategory productCategory where productCategory.parent is null order by productCategory.order asc";
    TypedQuery<ProductCategory> query = entityManager.createQuery(jpql, ProductCategory.class).setFlushMode(FlushModeType.COMMIT);
    if (count != null) {
      query.setMaxResults(count);
    }
    return query.getResultList();
  }
  public List<ProductCategory> findParents(ProductCategory productCategory, Integer count) {
    if (productCategory == null || productCategory.getParent() == null) {
      return Collections.<ProductCategory> emptyList();
    }
    String jpql = "select productCategory from ProductCategory productCategory where productCategory.id in (:ids) order by productCategory.grade asc";
    TypedQuery<ProductCategory> query = entityManager.createQuery(jpql, ProductCategory.class).setFlushMode(FlushModeType.COMMIT).setParameter("ids", productCategory.getTreePaths());
    if (count != null) {
      query.setMaxResults(count);
    }
    return query.getResultList();
  }

  public List<ProductCategory> findChildren(ProductCategory productCategory, Integer count) {
    TypedQuery<ProductCategory> query;
    if (productCategory != null) {
      String jpql = "select productCategory from ProductCategory productCategory where productCategory.treePath like :treePath order by productCategory.order asc";
      query = entityManager.createQuery(jpql, ProductCategory.class).setFlushMode(FlushModeType.COMMIT).setParameter("treePath", "%" + ProductCategory.TREE_PATH_SEPARATOR + productCategory.getId() + ProductCategory.TREE_PATH_SEPARATOR + "%");
    } else {
      String jpql = "select productCategory from ProductCategory productCategory order by productCategory.order asc";
      query = entityManager.createQuery(jpql, ProductCategory.class).setFlushMode(FlushModeType.COMMIT);
    }
    if (count != null) {
      query.setMaxResults(count);
    }
    return sort(query.getResultList(), productCategory);
  }



  /**
   * 排序商品分类
   * 
   * @param productCategories
   *            商品分类
   * @param parent
   *            上级商品分类
   * @return 商品分类
   */
  private List<ProductCategory> sort(List<ProductCategory> productCategories, ProductCategory parent) {
    List<ProductCategory> result = new ArrayList<ProductCategory>();
    if (productCategories != null) {
      for (ProductCategory productCategory : productCategories) {
        if ((productCategory.getParent() != null && productCategory.getParent().equals(parent)) || (productCategory.getParent() == null && parent == null)) {
          result.add(productCategory);
          result.addAll(sort(productCategories, productCategory));
        }
      }
    }
    return result;
  }

  
  @Override
  public void persist (ProductCategory entity)
  {
    setValue (entity);
    super.persist (entity);
  }

  @Override
  public ProductCategory merge (ProductCategory entity)
  {
    setValue(entity);
    for (ProductCategory category : findChildren(entity, null)) {
      setValue(category);
    }
    return super.merge (entity);
  }
  /**
   * 设置值
   * 
   * @param productCategory
   *            商品分类
   */
  private void setValue(ProductCategory productCategory) {
    if (productCategory == null) {
      return;
    }
    ProductCategory parent = productCategory.getParent();
    if (parent != null) {
      productCategory.setTreePath(parent.getTreePath() + parent.getId() + ProductCategory.TREE_PATH_SEPARATOR);
    } else {
      productCategory.setTreePath(ProductCategory.TREE_PATH_SEPARATOR);
    }
    productCategory.setGrade(productCategory.getTreePaths().size());
  }
  
}