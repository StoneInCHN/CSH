package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.ProductCategory;
import com.csh.dao.ProductCategoryDao;
import com.csh.service.ProductCategoryService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("productCategoryServiceImpl")
public class ProductCategoryServiceImpl extends BaseServiceImpl<ProductCategory,Long> implements ProductCategoryService {

      @Resource(name="productCategoryDaoImpl")
      public void setBaseDao(ProductCategoryDao productCategoryDao) {
         super.setBaseDao(productCategoryDao);
  }
}