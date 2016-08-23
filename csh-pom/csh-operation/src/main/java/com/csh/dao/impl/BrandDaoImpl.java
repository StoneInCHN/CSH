package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.Brand;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.BrandDao;
@Repository("brandDaoImpl")
public class BrandDaoImpl extends  BaseDaoImpl<Brand,Long> implements BrandDao {

}