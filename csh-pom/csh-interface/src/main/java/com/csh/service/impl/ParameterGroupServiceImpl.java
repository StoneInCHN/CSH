package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.ParameterGroup;
import com.csh.dao.ParameterGroupDao;
import com.csh.service.ParameterGroupService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("parameterGroupServiceImpl")
public class ParameterGroupServiceImpl extends BaseServiceImpl<ParameterGroup,Long> implements ParameterGroupService {

      @Resource(name="parameterGroupDaoImpl")
      public void setBaseDao(ParameterGroupDao parameterGroupDao) {
         super.setBaseDao(parameterGroupDao);
  }
}