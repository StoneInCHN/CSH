package com.csh.service; 

import com.csh.beans.Message;
import com.csh.entity.Distributor;
import com.csh.entity.DistributorDeductClearingRecord;
import com.csh.framework.service.BaseService;

public interface DistributorDeductClearingRecordService extends BaseService<DistributorDeductClearingRecord,Long>{

  /**
   * 生成结算单
   * @param id 分销商Id
   */
  Message createClearingRecord(Distributor distributor);
  
}