package com.csh.service.impl; 

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.CarServiceTenantDeductRecordDao;
import com.csh.dao.TenantDeductClearingRecordDao;
import com.csh.entity.CarServiceTenantDeductRecord;
import com.csh.entity.Sn.Type;
import com.csh.entity.TenantDeductClearingRecord;
import com.csh.entity.commonenum.CommonEnum.ClearingStatus;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.SnService;
import com.csh.service.TenantDeductClearingRecordService;
import com.csh.utils.FieldFilterUtils;

@Service("tenantDeductClearingRecordServiceImpl")
public class TenantDeductClearingRecordServiceImpl extends BaseServiceImpl<TenantDeductClearingRecord,Long> implements TenantDeductClearingRecordService {

      @Resource(name="tenantDeductClearingRecordDaoImpl")
      private TenantDeductClearingRecordDao tenantDeductClearingRecordDao;
      @Resource(name ="carServiceTenantDeductRecordDaoImpl")
      private CarServiceTenantDeductRecordDao carServiceTenantDeductRecordDao;
      @Resource (name = "snServiceImpl")
      private SnService snService;
      @Resource
      public void setBaseDao(TenantDeductClearingRecordDao tenantDeductClearingRecordDao) {
         super.setBaseDao(tenantDeductClearingRecordDao);
      }
      
      @Override
      @Transactional(propagation=Propagation.REQUIRES_NEW)
      public void saveTenantDeductClearingRecord (
          TenantDeductClearingRecord tenantDeductClearingRecord, Long[] ids)
      {
        tenantDeductClearingRecord.setClearingStatus (ClearingStatus.WAITING_FOR_INVOICE);
        tenantDeductClearingRecord.setClearingSn (snService.generate (Type.deductClearing));
        FieldFilterUtils.addFieldValue("tenantID", tenantAccountService.getCurrentTenantID(), tenantDeductClearingRecord);
        tenantDeductClearingRecordDao.persist (tenantDeductClearingRecord);
        for (Long id : ids)
        {
          CarServiceTenantDeductRecord record = carServiceTenantDeductRecordDao.find (id);
          record.setTenantDeductClearingRecord (tenantDeductClearingRecord);
          record.setClearingDate (tenantDeductClearingRecord.getCreateDate ());
          carServiceTenantDeductRecordDao.merge (record);
         }
        
        
      }
}