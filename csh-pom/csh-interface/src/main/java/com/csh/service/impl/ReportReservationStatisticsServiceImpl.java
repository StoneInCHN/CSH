package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.ReportReservationStatistics;
import com.csh.dao.ReportReservationStatisticsDao;
import com.csh.service.ReportReservationStatisticsService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("reportReservationStatisticsServiceImpl")
public class ReportReservationStatisticsServiceImpl extends BaseServiceImpl<ReportReservationStatistics,Long> implements ReportReservationStatisticsService {

      @Resource(name="reportReservationStatisticsDaoImpl")
      public void setBaseDao(ReportReservationStatisticsDao reportReservationStatisticsDao) {
         super.setBaseDao(reportReservationStatisticsDao);
  }
}