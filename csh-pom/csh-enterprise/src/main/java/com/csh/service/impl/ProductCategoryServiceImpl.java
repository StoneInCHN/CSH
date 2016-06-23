package com.csh.service.impl; 

import java.util.List;

import javax.annotation.Resource; 

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional;

import com.csh.entity.estore.ProductCategory;
import com.csh.dao.ProductCategoryDao;
import com.csh.service.ProductCategoryService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("productCategoryServiceImpl")
public class ProductCategoryServiceImpl extends BaseServiceImpl<ProductCategory,Long> implements ProductCategoryService {

  @Resource(name = "productCategoryDaoImpl")
  private ProductCategoryDao productCategoryDao;

  @Resource(name = "productCategoryDaoImpl")
  public void setBaseDao(ProductCategoryDao productCategoryDao) {
    super.setBaseDao(productCategoryDao);
  }

  @Transactional(readOnly = true)
  public List<ProductCategory> findRoots() {
    return productCategoryDao.findRoots(null);
  }
  @Transactional(readOnly = true)
  public List<ProductCategory> findRoots(Boolean isTenant) {
    if (isTenant)
    {
      return productCategoryDao.findRoots(null,tenantAccountService.getCurrentTenantID ());
    }else {
      return productCategoryDao.findRoots(null);
    }
    
  }
  @Transactional(readOnly = true)
  public List<ProductCategory> findRoots(Integer count) {
    return productCategoryDao.findRoots(count);
  }

  @Transactional(readOnly = true)
  @Cacheable("productCategory")
  public List<ProductCategory> findRoots(Integer count, String cacheRegion) {
    return productCategoryDao.findRoots(count);
  }

  @Transactional(readOnly = true)
  public List<ProductCategory> findParents(ProductCategory productCategory) {
    return productCategoryDao.findParents(productCategory, null);
  }

  @Transactional(readOnly = true)
  public List<ProductCategory> findParents(ProductCategory productCategory, Integer count) {
    return productCategoryDao.findParents(productCategory, count);
  }

  @Transactional(readOnly = true)
  @Cacheable("productCategory")
  public List<ProductCategory> findParents(ProductCategory productCategory, Integer count, String cacheRegion) {
    return productCategoryDao.findParents(productCategory, count);
  }

  @Transactional(readOnly = true)
  public List<ProductCategory> findTree() {
    return productCategoryDao.findChildren(null, null);
  }

  @Transactional(readOnly = true)
  public List<ProductCategory> findChildren(ProductCategory productCategory) {
    return productCategoryDao.findChildren(productCategory, null);
  }

  @Transactional(readOnly = true)
  public List<ProductCategory> findChildren(ProductCategory productCategory, Integer count) {
    return productCategoryDao.findChildren(productCategory, count);
  }

  @Transactional(readOnly = true)
  @Cacheable("productCategory")
  public List<ProductCategory> findChildren(ProductCategory productCategory, Integer count, String cacheRegion) {
    return productCategoryDao.findChildren(productCategory, count);
  }
}