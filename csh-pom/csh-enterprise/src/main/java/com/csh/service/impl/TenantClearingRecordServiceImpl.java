package com.csh.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.beans.Setting;
import com.csh.dao.CarServiceRecordDao;
import com.csh.dao.TenantClearingRecordDao;
import com.csh.entity.CarServiceRecord;
import com.csh.entity.Sn.Type;
import com.csh.entity.TenantClearingRecord;
import com.csh.entity.commonenum.CommonEnum.ClearingStatus;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.SnService;
import com.csh.service.TenantClearingRecordService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.SettingUtils;

/**
 * 结算记录
 * 
 * @author shijun
 *
 */
@Service("tenantClearingRecordServiceImpl")
public class TenantClearingRecordServiceImpl extends BaseServiceImpl<TenantClearingRecord, Long> implements
TenantClearingRecordService {


  @Resource(name = "tenantClearingRecordDaoImpl")
  private TenantClearingRecordDao tenantClearingRecordDao;

  @Resource (name = "carServiceRecordDaoImpl")
  private CarServiceRecordDao carServiceRecordDao;
  @Resource (name = "snServiceImpl")
  private SnService snService;
  private Setting setting = SettingUtils.get();
  @Resource()
  public void setBaseDao(TenantClearingRecordDao tenantClearingRecordDao) {
    super.setBaseDao(tenantClearingRecordDao);
  }

  @Override
  public Map<String, Date> findPeriodBeginEndDate ()
  {
    
    Map<String, Date> peroidDate = new HashMap<String, Date>();
    Date peroidBeginDate =  tenantClearingRecordDao.findLastPeriodEndDate ();
    Date now = new Date ();
    Integer cycle = setting.getClearingRecordCycle ();
    int days = (int) ((now.getTime() - peroidBeginDate.getTime())
        / (24 * 60 * 60 * 1000)/cycle*cycle);
    if (days - cycle < 0)
    {
      return peroidDate;
    }
    Calendar rightNow = Calendar.getInstance();
    rightNow.setTime(peroidBeginDate);
    rightNow.add(Calendar.DATE,days);
    
    Timestamp peroidEndDate = new Timestamp (rightNow.getTimeInMillis ());
    
    peroidDate.put ("peroidBeginDate", peroidBeginDate);
    peroidDate.put ("peroidEndDate", peroidEndDate);
    
    return peroidDate;
  }

  @Override
  @Transactional(propagation=Propagation.REQUIRES_NEW)
  public void saveTenantClearingRecord (
      TenantClearingRecord tenantClearingRecord, Long[] ids)
  {
    tenantClearingRecord.setClearingStatus (ClearingStatus.WAITING_FOR_INVOICE);
    tenantClearingRecord.setClearingSn (snService.generate (Type.clearing));
    FieldFilterUtils.addFieldValue("tenantID", tenantAccountService.getCurrentTenantID(), tenantClearingRecord);
    tenantClearingRecordDao.persist (tenantClearingRecord);
    for (Long id : ids)
    {
      CarServiceRecord record = carServiceRecordDao.find (id);
      record.setTenantClearingRecord (tenantClearingRecord);
      record.setClearingDate (tenantClearingRecord.getCreateDate ());
      carServiceRecordDao.merge (record);
     }
    
    
  }

}
