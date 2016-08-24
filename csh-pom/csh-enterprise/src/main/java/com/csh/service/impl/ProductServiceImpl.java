package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.entity.TenantImage;
import com.csh.entity.estore.Product;
import com.csh.entity.estore.ProductImage;
import com.csh.dao.ProductDao;
import com.csh.service.ProductImageService;
import com.csh.service.ProductService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service ("productServiceImpl")
public class ProductServiceImpl extends BaseServiceImpl<Product, Long>
    implements ProductService
{

  @Resource (name = "productImageServiceImpl")
  private ProductImageService productImageService;

  @Resource (name = "productDaoImpl")
  public void setBaseDao (ProductDao productDao)
  {
    super.setBaseDao (productDao);
  }

  @Override
  @Transactional(propagation=Propagation.REQUIRED)
  public void updateProduct (Product product,String[] productImageList,
      Long[] deleteImageIdList)
  {
    if (productImageList != null)
    {
      for (String image : productImageList)
      {
        ProductImage productImage = new ProductImage ();
        productImage.setSource (image);
        productImage.setProduct (product);
        productImageService.save (productImage);
      }
    }
    if (deleteImageIdList != null)
    {
      productImageService.delete (deleteImageIdList);
    }
    
    
    this.update (product, "image", "tenantID", "createDate", "treePath",
        "isMarketable", "hits", "grade", "children", "products",
        "parameterGroups", "attributes", "allocatedStock", "isGift", "isList","productStatus");
  }
}