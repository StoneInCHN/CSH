package com.csh.service; 

import java.util.List;
import java.util.Map;

import com.csh.entity.ServiceCategory;
import com.csh.framework.service.BaseService;

public interface ServiceCategoryService extends BaseService<ServiceCategory,Long>{

	List<Map<String, Object>> findAllServiceCategory();

}