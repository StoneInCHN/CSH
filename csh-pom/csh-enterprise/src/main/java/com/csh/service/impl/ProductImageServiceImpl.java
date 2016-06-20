package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.ProductImage;
import com.csh.dao.ProductImageDao;
import com.csh.service.ProductImageService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("productImageServiceImpl")
public class ProductImageServiceImpl extends BaseServiceImpl<ProductImage,Long> implements ProductImageService {

      @Resource(name="productImageDaoImpl")
      public void setBaseDao(ProductImageDao productImageDao) {
         super.setBaseDao(productImageDao);
  }
}