package com.csh.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.ReportDeviceBindStatisticsDao;
import com.csh.entity.ReportDeviceBindStatistics;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.ReportDeviceBindStatisticsService;

@Service("reportDeviceBindStatisticsServiceImpl")
public class ReportDeviceBindStatisticsServiceImpl extends
    BaseServiceImpl<ReportDeviceBindStatistics, Long> implements ReportDeviceBindStatisticsService {

  @Resource(name = "reportDeviceBindStatisticsDaoImpl")
  private ReportDeviceBindStatisticsDao reportDeviceBindStatisticsDao;

  @Resource(name = "reportDeviceBindStatisticsDaoImpl")
  public void setBaseDao(ReportDeviceBindStatisticsDao reportDeviceBindStatisticsDao) {
    super.setBaseDao(reportDeviceBindStatisticsDao);
  }

  @Override
  public List<ReportDeviceBindStatistics> findList(Date startDate, Date endDate) {
    return reportDeviceBindStatisticsDao.findList(startDate, endDate);
  }
}
