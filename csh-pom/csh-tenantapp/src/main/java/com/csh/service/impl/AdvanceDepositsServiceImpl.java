package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.AdvanceDeposits;
import com.csh.dao.AdvanceDepositsDao;
import com.csh.service.AdvanceDepositsService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("advanceDepositsServiceImpl")
public class AdvanceDepositsServiceImpl extends BaseServiceImpl<AdvanceDeposits,Long> implements AdvanceDepositsService {

      @Resource(name="advanceDepositsDaoImpl")
      public void setBaseDao(AdvanceDepositsDao advanceDepositsDao) {
         super.setBaseDao(advanceDepositsDao);
  }
}