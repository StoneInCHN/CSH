package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.ReportWashStatistics;
import com.csh.dao.ReportWashStatisticsDao;
import com.csh.service.ReportWashStatisticsService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("reportWashStatisticsServiceImpl")
public class ReportWashStatisticsServiceImpl extends BaseServiceImpl<ReportWashStatistics,Long> implements ReportWashStatisticsService {

      @Resource(name="reportWashStatisticsDaoImpl")
      public void setBaseDao(ReportWashStatisticsDao reportWashStatisticsDao) {
         super.setBaseDao(reportWashStatisticsDao);
  }
}