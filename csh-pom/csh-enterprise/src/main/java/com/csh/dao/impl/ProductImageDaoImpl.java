package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.ProductImage;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ProductImageDao;
@Repository("productImageDaoImpl")
public class ProductImageDaoImpl extends  BaseDaoImpl<ProductImage,Long> implements ProductImageDao {

}