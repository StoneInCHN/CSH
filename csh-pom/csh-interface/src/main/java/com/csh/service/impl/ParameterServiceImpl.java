package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.Parameter;
import com.csh.dao.ParameterDao;
import com.csh.service.ParameterService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("parameterServiceImpl")
public class ParameterServiceImpl extends BaseServiceImpl<Parameter,Long> implements ParameterService {

      @Resource(name="parameterDaoImpl")
      public void setBaseDao(ParameterDao parameterDao) {
         super.setBaseDao(parameterDao);
  }
}