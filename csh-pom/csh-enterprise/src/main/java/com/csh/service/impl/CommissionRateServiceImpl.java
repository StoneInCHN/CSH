package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.CommissionRate;
import com.csh.dao.CommissionRateDao;
import com.csh.service.CommissionRateService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("commissionRateServiceImpl")
public class CommissionRateServiceImpl extends BaseServiceImpl<CommissionRate,Long> implements CommissionRateService {

      @Resource(name="commissionRateDaoImpl")
      public void setBaseDao(CommissionRateDao commissionRateDao) {
         super.setBaseDao(commissionRateDao);
  }
}