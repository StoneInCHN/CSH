package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.ServiceCategory;
import com.csh.dao.ServiceCategoryDao;
import com.csh.service.ServiceCategoryService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("serviceCategoryServiceImpl")
public class ServiceCategoryServiceImpl extends BaseServiceImpl<ServiceCategory,Long> implements ServiceCategoryService {

      @Resource(name="serviceCategoryDaoImpl")
      public void setBaseDao(ServiceCategoryDao serviceCategoryDao) {
         super.setBaseDao(serviceCategoryDao);
  }
}