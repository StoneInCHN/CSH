package com.csh.service; 

import com.csh.entity.estore.ParameterGroup;
import com.csh.framework.service.BaseService;

public interface ParameterGroupService extends BaseService<ParameterGroup,Long>{

  void saveParameterGrpoup (Long productCategoryId, ParameterGroup parameterGroup);

  void updateParameterGroup (ParameterGroup parameterGroup);
 
}