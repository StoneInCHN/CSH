package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.Returns;
import com.csh.dao.ReturnsDao;
import com.csh.service.ReturnsService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("returnsServiceImpl")
public class ReturnsServiceImpl extends BaseServiceImpl<Returns,Long> implements ReturnsService {

      @Resource(name="returnsDaoImpl")
      public void setBaseDao(ReturnsDao returnsDao) {
         super.setBaseDao(returnsDao);
  }
}