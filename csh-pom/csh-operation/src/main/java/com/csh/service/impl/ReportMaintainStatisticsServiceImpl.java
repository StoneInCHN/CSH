package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.ReportMaintainStatistics;
import com.csh.dao.ReportMaintainStatisticsDao;
import com.csh.service.ReportMaintainStatisticsService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("reportMaintainStatisticsServiceImpl")
public class ReportMaintainStatisticsServiceImpl extends BaseServiceImpl<ReportMaintainStatistics,Long> implements ReportMaintainStatisticsService {

      @Resource(name="reportMaintainStatisticsDaoImpl")
      public void setBaseDao(ReportMaintainStatisticsDao reportMaintainStatisticsDao) {
         super.setBaseDao(reportMaintainStatisticsDao);
  }
}