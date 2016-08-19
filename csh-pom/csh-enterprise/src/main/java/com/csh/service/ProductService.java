package com.csh.service; 

import com.csh.entity.estore.Product;
import com.csh.framework.service.BaseService;

public interface ProductService extends BaseService<Product,Long>{

  void updateProduct (Product product,String[] productImageList,Long[] deleteImageIdList);

}