package com.csh.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.ReportUserRegStatisticsDao;
import com.csh.entity.ReportUserRegStatistics;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.ReportUserRegStatisticsService;
import com.csh.utils.DateUtils;

@Service("reportUserRegStatisticsServiceImpl")
public class ReportUserRegStatisticsServiceImpl extends
    BaseServiceImpl<ReportUserRegStatistics, Long> implements ReportUserRegStatisticsService {

  @Resource(name = "reportUserRegStatisticsDaoImpl")
  private ReportUserRegStatisticsDao reportUserRegStatisticsDao;
  
  @Resource(name = "reportUserRegStatisticsDaoImpl")
  public void setBaseDao(ReportUserRegStatisticsDao reportUserRegStatisticsDao) {
    super.setBaseDao(reportUserRegStatisticsDao);
  }

  @Override
  public List<ReportUserRegStatistics> findList(Date startDate, Date endDate) {
    
    return reportUserRegStatisticsDao.findList(startDate, endDate);
  }
}
