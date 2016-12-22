package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.ReportServiceStatistics;
import com.csh.dao.ReportServiceStatisticsDao;
import com.csh.service.ReportServiceStatisticsService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("reportServiceStatisticsServiceImpl")
public class ReportServiceStatisticsServiceImpl extends BaseServiceImpl<ReportServiceStatistics,Long> implements ReportServiceStatisticsService {

      @Resource(name="reportServiceStatisticsDaoImpl")
      public void setBaseDao(ReportServiceStatisticsDao reportServiceStatisticsDao) {
         super.setBaseDao(reportServiceStatisticsDao);
  }
}