package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.ReportRepareStatistics;
import com.csh.dao.ReportRepareStatisticsDao;
import com.csh.service.ReportRepareStatisticsService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("reportRepareStatisticsServiceImpl")
public class ReportRepareStatisticsServiceImpl extends BaseServiceImpl<ReportRepareStatistics,Long> implements ReportRepareStatisticsService {

      @Resource(name="reportRepareStatisticsDaoImpl")
      public void setBaseDao(ReportRepareStatisticsDao reportRepareStatisticsDao) {
         super.setBaseDao(reportRepareStatisticsDao);
  }
}