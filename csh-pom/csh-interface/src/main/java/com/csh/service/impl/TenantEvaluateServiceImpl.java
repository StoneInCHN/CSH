package com.csh.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.CarServiceRecordDao;
import com.csh.dao.TenantEvaluateDao;
import com.csh.dao.TenantInfoDao;
import com.csh.entity.CarServiceRecord;
import com.csh.entity.EndUser;
import com.csh.entity.TenantEvaluate;
import com.csh.entity.TenantInfo;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.TenantEvaluateService;

@Service("tenantEvaluateServiceImpl")
public class TenantEvaluateServiceImpl extends BaseServiceImpl<TenantEvaluate, Long> implements
    TenantEvaluateService {

  @Resource(name = "tenantEvaluateDaoImpl")
  private TenantEvaluateDao tenantEvaluateDao;

  @Resource(name = "tenantInfoDaoImpl")
  private TenantInfoDao tenantInfoDao;

  @Resource(name = "carServiceRecordDaoImpl")
  private CarServiceRecordDao carServiceRecordDao;

  @Resource(name = "tenantEvaluateDaoImpl")
  public void setBaseDao(TenantEvaluateDao tenantEvaluateDao) {
    super.setBaseDao(tenantEvaluateDao);
  }


  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void doRateForTenant(EndUser endUser, Long tenantId, Integer score, Long recordId) {
    TenantEvaluate tenantEvaluate = new TenantEvaluate();
    CarServiceRecord carServiceRecord = carServiceRecordDao.find(recordId);
    tenantEvaluate.setCarServiceRecord(carServiceRecord);
    tenantEvaluate.setScore(score);
    tenantEvaluate.setEndUser(endUser);
    tenantEvaluate.setTenantId(tenantId);
    tenantEvaluateDao.persist(tenantEvaluate);

    List<Filter> filters = new ArrayList<Filter>();
    Filter tenant = new Filter("tenantId", Operator.eq, tenantId);
    filters.add(tenant);
    List<TenantEvaluate> tenantEvaluates = findList(null, filters, null);
    Double sum = 0d;
    for (TenantEvaluate evaluate : tenantEvaluates) {
      sum += evaluate.getScore();
    }
    Integer avgScore = (int) Math.ceil(sum / tenantEvaluates.size());
    TenantInfo tenantInfo = tenantInfoDao.find(tenantId);
    tenantInfo.setPraiseRate(avgScore);
    tenantInfo.setRateCounts(tenantInfo.getRateCounts() + 1);
    tenantInfoDao.merge(tenantInfo);
  }
}
