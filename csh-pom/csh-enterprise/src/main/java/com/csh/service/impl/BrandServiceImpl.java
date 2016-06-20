package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.Brand;
import com.csh.dao.BrandDao;
import com.csh.service.BrandService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("brandServiceImpl")
public class BrandServiceImpl extends BaseServiceImpl<Brand,Long> implements BrandService {

      @Resource(name="brandDaoImpl")
      public void setBaseDao(BrandDao brandDao) {
         super.setBaseDao(brandDao);
  }
}