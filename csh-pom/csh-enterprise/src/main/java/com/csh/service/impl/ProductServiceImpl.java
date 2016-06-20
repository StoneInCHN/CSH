package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.Product;
import com.csh.dao.ProductDao;
import com.csh.service.ProductService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("productServiceImpl")
public class ProductServiceImpl extends BaseServiceImpl<Product,Long> implements ProductService {

      @Resource(name="productDaoImpl")
      public void setBaseDao(ProductDao productDao) {
         super.setBaseDao(productDao);
  }
}