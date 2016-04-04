package com.csh.dao; 
import java.util.Date;
import java.util.Map;

import com.csh.entity.TenantClearingRecord;
import com.csh.framework.dao.BaseDao;

public interface TenantClearingRecordDao extends  BaseDao<TenantClearingRecord,Long>{

  Date findLastPeriodEndDate ();

}