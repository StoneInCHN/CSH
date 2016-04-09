package com.csh.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.ReportUserRegStatisticsDao;
import com.csh.entity.ReportUserRegStatistics;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.ReportUserRegStatisticsService;

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
  public ReportUserRegStatistics getReportByDate(Date date) {
    return reportUserRegStatisticsDao.getReportByDate(date);
  }
}
