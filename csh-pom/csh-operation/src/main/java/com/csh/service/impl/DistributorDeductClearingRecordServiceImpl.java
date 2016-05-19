package com.csh.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.dao.CarServiceDistributorDeductRecordDao;
import com.csh.dao.DistributorDeductClearingRecordDao;
import com.csh.dao.SnDao;
import com.csh.entity.CarServiceDistributorDeductRecord;
import com.csh.entity.Distributor;
import com.csh.entity.DistributorDeductClearingRecord;
import com.csh.entity.Sn;
import com.csh.entity.commonenum.CommonEnum.ClearingStatus;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.DistributorDeductClearingRecordService;

@Service("distributorDeductClearingRecordServiceImpl")
public class DistributorDeductClearingRecordServiceImpl extends
    BaseServiceImpl<DistributorDeductClearingRecord, Long> implements
    DistributorDeductClearingRecordService {

  @Resource(name = "distributorDeductClearingRecordDaoImpl")
  private DistributorDeductClearingRecordDao distributorDeductClearingRecordDao;

  @Resource(name = "carServiceDistributorDeductRecordDaoImpl")
  private CarServiceDistributorDeductRecordDao carServiceDistributorDeductRecorDao;

  @Resource(name = "snDaoImpl")
  private SnDao snDao;

  
  @Resource(name = "distributorDeductClearingRecordDaoImpl")
  public void setBaseDao(DistributorDeductClearingRecordDao distributorDeductClearingRecordDao) {
    super.setBaseDao(distributorDeductClearingRecordDao);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public Message createClearingRecord(Distributor distributor) {
    try {
      List<Filter> filters = new ArrayList<Filter>();
      filters.add(Filter.eq("distributorId",distributor.getId()));
      filters.add(Filter.isNull("clearingDate"));
      List<CarServiceDistributorDeductRecord> lists =
          carServiceDistributorDeductRecorDao.findList(null, null, filters, null);
      if (lists.size() > 0) {
        DistributorDeductClearingRecord clearRecord = new DistributorDeductClearingRecord();
        BigDecimal amount = new BigDecimal(0);
        Date currentDate = new Date();
        for (CarServiceDistributorDeductRecord carServiceDistributorDeductRecord : lists) {
          amount = amount.add(carServiceDistributorDeductRecord.getDeductMoney());
        }
        clearRecord.setAmountOfCurrentPeriod(amount);
        clearRecord.setClearingStatus(ClearingStatus.UNPAID);
        clearRecord.setDistributor(distributor);
        clearRecord.setCarServiceDistributorDeductRecords(lists);
        clearRecord.setClearingSn(snDao.generate(Sn.Type.deductClearing));
        save(clearRecord);
        for (CarServiceDistributorDeductRecord carServiceDistributorDeductRecord : lists) {
          carServiceDistributorDeductRecord.setClearingDate(currentDate);
          carServiceDistributorDeductRecord.setDistributorDeductClearingRecord(clearRecord);
          carServiceDistributorDeductRecorDao.merge(carServiceDistributorDeductRecord);
        }
        return Message.success("结算单生成成功");
      } else {
        return Message.warn("你没有可以结算的清单");
      }
    } catch (Exception e) {
      LogUtil.debug(DistributorDeductClearingRecordServiceImpl.class, "createClearingRecord",
          "结算单生成失败,失败id为 ：%s ,失败原因", distributor.getId(), e.getMessage());
      return Message.error("结算单生成失败");
    }

  }
}
