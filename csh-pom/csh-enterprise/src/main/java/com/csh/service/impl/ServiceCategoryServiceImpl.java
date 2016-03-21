package com.csh.service.impl; 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.ServiceCategoryDao;
import com.csh.entity.ServiceCategory;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.ServiceCategoryService;
import com.csh.utils.FieldFilterUtils;

@Service("serviceCategoryServiceImpl")
public class ServiceCategoryServiceImpl extends BaseServiceImpl<ServiceCategory,Long> implements ServiceCategoryService {

      @Resource(name="serviceCategoryDaoImpl")
      private ServiceCategoryDao serviceCategoryDao;
      @Resource
      public void setBaseDao(ServiceCategoryDao serviceCategoryDao) {
         super.setBaseDao(serviceCategoryDao);
  }

	@Override
	public List<Map<String, Object>> findAllServiceCategory() {
		
		List<ServiceCategory> serviceCategorieList = serviceCategoryDao.findList(null, null, null, null);
		
		String[] propertys = {"id", "categoryName"};
	    return FieldFilterUtils.filterCollectionMap(propertys, serviceCategorieList);
	}
}
    