package com.csh.dao; 
import java.util.List;

import com.csh.entity.estore.ProductCategory;
import com.csh.framework.dao.BaseDao;

public interface ProductCategoryDao extends  BaseDao<ProductCategory,Long>{
  /**
   * 查找顶级商品分类
   * 
   * @param count
   *            数量
   * @return 顶级商品分类
   */
  List<ProductCategory> findRoots(Integer count);
  List<ProductCategory> findRoots(Integer count,Long tenantId); 
  /**
   * 查找上级商品分类
   * 
   * @param productCategory
   *            商品分类
   * @param count
   *            数量
   * @return 上级商品分类
   */
  List<ProductCategory> findParents(ProductCategory productCategory, Integer count);

  /**
   * 查找下级商品分类
   * 
   * @param productCategory
   *            商品分类
   * @param count
   *            数量
   * @return 下级商品分类
   */
  List<ProductCategory> findChildren(ProductCategory productCategory, Integer count);
}