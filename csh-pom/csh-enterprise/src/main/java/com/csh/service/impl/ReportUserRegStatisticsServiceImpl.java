package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.ReportUserRegStatistics;
import com.csh.dao.ReportUserRegStatisticsDao;
import com.csh.service.ReportUserRegStatisticsService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("reportUserRegStatisticsServiceImpl")
public class ReportUserRegStatisticsServiceImpl extends BaseServiceImpl<ReportUserRegStatistics,Long> implements ReportUserRegStatisticsService {

      @Resource(name="reportUserRegStatisticsDaoImpl")
      public void setBaseDao(ReportUserRegStatisticsDao reportUserRegStatisticsDao) {
         super.setBaseDao(reportUserRegStatisticsDao);
  }
}