package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.ProductCategory;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ProductCategoryDao;
@Repository("productCategoryDaoImpl")
public class ProductCategoryDaoImpl extends  BaseDaoImpl<ProductCategory,Long> implements ProductCategoryDao {

}