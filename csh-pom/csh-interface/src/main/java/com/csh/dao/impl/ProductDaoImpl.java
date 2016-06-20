package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.Product;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ProductDao;
@Repository("productDaoImpl")
public class ProductDaoImpl extends  BaseDaoImpl<Product,Long> implements ProductDao {

}