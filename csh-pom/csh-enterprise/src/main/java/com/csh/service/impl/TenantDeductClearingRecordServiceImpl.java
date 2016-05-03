package com.csh.service.impl; 

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.CarServiceTenantDeductRecordDao;
import com.csh.dao.TenantDeductClearingRecordDao;
import com.csh.entity.CarServiceRecord;
import com.csh.entity.CarServiceTenantDeductRecord;
import com.csh.entity.CommissionRate;
import com.csh.entity.Sn.Type;
import com.csh.entity.TenantDeductClearingRecord;
import com.csh.entity.commonenum.CommonEnum.ClearingStatus;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.CommissionRateService;
import com.csh.service.SnService;
import com.csh.service.TenantDeductClearingRecordService;
import com.csh.service.TenantInfoService;

@Service("tenantDeductClearingRecordServiceImpl")
public class TenantDeductClearingRecordServiceImpl extends BaseServiceImpl<TenantDeductClearingRecord,Long> implements TenantDeductClearingRecordService {

      @Resource(name="tenantDeductClearingRecordDaoImpl")
      private TenantDeductClearingRecordDao tenantDeductClearingRecordDao;
      @Resource(name ="carServiceTenantDeductRecordDaoImpl")
      private CarServiceTenantDeductRecordDao carServiceTenantDeductRecordDao;
      @Resource (name = "snServiceImpl")
      private SnService snService;
      @Resource (name = "tenantInfoServiceImpl")
      private TenantInfoService tenantInfoService;
      
      @Resource(name = "commissionRateServiceImpl")
      private CommissionRateService commissionRateService;
      @Resource
      public void setBaseDao(TenantDeductClearingRecordDao tenantDeductClearingRecordDao) {
         super.setBaseDao(tenantDeductClearingRecordDao);
      }
      
      @Override
      @Transactional(propagation=Propagation.REQUIRES_NEW)
      public Boolean saveTenantDeductClearingRecord (
          TenantDeductClearingRecord tenantDeductClearingRecord, Long[] ids)
      {
        //validate before save
        List<CommissionRate> commissionRateList = commissionRateService.findAll ();
        if (commissionRateList == null || commissionRateList.size () == 0)
        {
          return false;
        }
        
        BigDecimal totalMoney = new BigDecimal (0);
        BigDecimal totalRealIncome = new BigDecimal (0);
        
        List<CarServiceTenantDeductRecord> carServiceTenantDeductRecordList = new ArrayList<CarServiceTenantDeductRecord>();
        for (Long id : ids)
        {
          
          CarServiceTenantDeductRecord record = carServiceTenantDeductRecordDao.find (id);
          
          totalMoney = totalMoney.add (record.getPrice ());
          
          carServiceTenantDeductRecordList.add (record);
        }
        //格式化数据
        DecimalFormat format=new DecimalFormat("0.00");
        String str = format.format(totalMoney.doubleValue ()* commissionRateList.get (0).getTenantRate ());    
        totalRealIncome = new BigDecimal(str);
        totalMoney = new BigDecimal (format.format (totalMoney));
        if (!totalRealIncome.equals (new BigDecimal (format.format (tenantDeductClearingRecord.getAmountOfCurrentPeriod ()))))
        {
          return false;
        }
        
        tenantDeductClearingRecord.setClearingStatus (ClearingStatus.WAITING_FOR_INVOICE);
        tenantDeductClearingRecord.setClearingSn (snService.generate (Type.deductClearing));
        tenantDeductClearingRecord.setTenantInfo (tenantInfoService.find (tenantAccountService.getCurrentTenantInfo ().getId ()));
        tenantDeductClearingRecord.setTenantID (tenantAccountService.getCurrentTenantID());
        tenantDeductClearingRecordDao.persist (tenantDeductClearingRecord);
        for (CarServiceTenantDeductRecord record : carServiceTenantDeductRecordList)
        {
//          CarServiceTenantDeductRecord record = carServiceTenantDeductRecordDao.find (id);
          record.setTenantDeductClearingRecord (tenantDeductClearingRecord);
          record.setClearingDate (tenantDeductClearingRecord.getCreateDate ());
          carServiceTenantDeductRecordDao.merge (record);
         }
        
        return true;
      }
}