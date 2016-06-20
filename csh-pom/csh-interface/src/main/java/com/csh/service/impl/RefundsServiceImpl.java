package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.Refunds;
import com.csh.dao.RefundsDao;
import com.csh.service.RefundsService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("refundsServiceImpl")
public class RefundsServiceImpl extends BaseServiceImpl<Refunds,Long> implements RefundsService {

      @Resource(name="refundsDaoImpl")
      public void setBaseDao(RefundsDao refundsDao) {
         super.setBaseDao(refundsDao);
  }
}