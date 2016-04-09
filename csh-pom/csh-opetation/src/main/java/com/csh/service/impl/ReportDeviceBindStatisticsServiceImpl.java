package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.ReportDeviceBindStatistics;
import com.csh.dao.ReportDeviceBindStatisticsDao;
import com.csh.service.ReportDeviceBindStatisticsService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("reportDeviceBindStatisticsServiceImpl")
public class ReportDeviceBindStatisticsServiceImpl extends BaseServiceImpl<ReportDeviceBindStatistics,Long> implements ReportDeviceBindStatisticsService {

      @Resource(name="reportDeviceBindStatisticsDaoImpl")
      public void setBaseDao(ReportDeviceBindStatisticsDao reportDeviceBindStatisticsDao) {
         super.setBaseDao(reportDeviceBindStatisticsDao);
  }
}