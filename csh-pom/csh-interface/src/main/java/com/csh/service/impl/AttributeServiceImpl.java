package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.Attribute;
import com.csh.dao.AttributeDao;
import com.csh.service.AttributeService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("attributeServiceImpl")
public class AttributeServiceImpl extends BaseServiceImpl<Attribute,Long> implements AttributeService {

      @Resource(name="attributeDaoImpl")
      public void setBaseDao(AttributeDao attributeDao) {
         super.setBaseDao(attributeDao);
  }
}