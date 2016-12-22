package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.Identifier;
import com.csh.dao.IdentifierDao;
import com.csh.service.IdentifierService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("identifierServiceImpl")
public class IdentifierServiceImpl extends BaseServiceImpl<Identifier,Long> implements IdentifierService {

      @Resource(name="identifierDaoImpl")
      public void setBaseDao(IdentifierDao identifierDao) {
         super.setBaseDao(identifierDao);
  }
}