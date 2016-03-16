package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.ServiceCategory;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ServiceCategoryDao;
@Repository("serviceCategoryDaoImpl")
public class ServiceCategoryDaoImpl extends  BaseDaoImpl<ServiceCategory,Long> implements ServiceCategoryDao {

}