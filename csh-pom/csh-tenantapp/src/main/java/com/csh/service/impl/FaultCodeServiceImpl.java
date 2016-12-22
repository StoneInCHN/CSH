package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.FaultCode;
import com.csh.dao.FaultCodeDao;
import com.csh.service.FaultCodeService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("faultCodeServiceImpl")
public class FaultCodeServiceImpl extends BaseServiceImpl<FaultCode,Long> implements FaultCodeService {

      @Resource(name="faultCodeDaoImpl")
      public void setBaseDao(FaultCodeDao faultCodeDao) {
         super.setBaseDao(faultCodeDao);
  }
}