package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.DistributorDeductClearingRecord;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.DistributorDeductClearingRecordDao;
@Repository("distributorDeductClearingRecordDaoImpl")
public class DistributorDeductClearingRecordDaoImpl extends  BaseDaoImpl<DistributorDeductClearingRecord,Long> implements DistributorDeductClearingRecordDao {

}