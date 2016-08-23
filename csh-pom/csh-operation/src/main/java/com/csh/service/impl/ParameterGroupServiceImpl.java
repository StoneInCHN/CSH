package com.csh.service.impl; 

import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.ParameterGroupDao;
import com.csh.entity.estore.Parameter;
import com.csh.entity.estore.ParameterGroup;
import com.csh.entity.estore.ProductCategory;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.ParameterGroupService;
import com.csh.service.ParameterService;
import com.csh.service.ProductCategoryService;

@Service("parameterGroupServiceImpl")
public class ParameterGroupServiceImpl extends BaseServiceImpl<ParameterGroup,Long> implements ParameterGroupService {

      @Resource(name="parameterServiceImpl")
      private ParameterService parameterService;
      @Resource(name="productCategoryServiceImpl")
      private ProductCategoryService productCategoryService;
      @Resource(name="parameterGroupDaoImpl")
      public void setBaseDao(ParameterGroupDao parameterGroupDao) {
         super.setBaseDao(parameterGroupDao);
  }

      @Override
      public void saveParameterGrpoup (Long productCategoryId,ParameterGroup parameterGroup)
      {
        for (Iterator<Parameter> iterator = parameterGroup.getParameters().iterator(); iterator.hasNext();) {
          Parameter parameter = iterator.next();
          if (parameter == null || parameter.getName() == null) {
            iterator.remove();
          } else {
            parameter.setParameterGroup(parameterGroup);
          }
        }
        ProductCategory productCategory = productCategoryService.find (productCategoryId);
        parameterGroup.setProductCategory (productCategory);
        this.save (parameterGroup);
      }

      @Override
      public void updateParameterGroup (ParameterGroup parameterGroup)
      {
        for (Iterator<Parameter> iterator = parameterGroup.getParameters().iterator(); iterator.hasNext();) {
          Parameter parameter = iterator.next();
          if (parameter == null || parameter.getName() == null) {
            iterator.remove();
          } else {
            parameter.setParameterGroup(parameterGroup);
          }
        }
        this.update (parameterGroup,"productCategory","createDate");
      }
}