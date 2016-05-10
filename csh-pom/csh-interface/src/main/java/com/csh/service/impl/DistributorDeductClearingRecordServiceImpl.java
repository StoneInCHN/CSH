package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.DistributorDeductClearingRecord;
import com.csh.dao.DistributorDeductClearingRecordDao;
import com.csh.service.DistributorDeductClearingRecordService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("distributorDeductClearingRecordServiceImpl")
public class DistributorDeductClearingRecordServiceImpl extends BaseServiceImpl<DistributorDeductClearingRecord,Long> implements DistributorDeductClearingRecordService {

      @Resource(name="distributorDeductClearingRecordDaoImpl")
      public void setBaseDao(DistributorDeductClearingRecordDao distributorDeductClearingRecordDao) {
         super.setBaseDao(distributorDeductClearingRecordDao);
  }
}