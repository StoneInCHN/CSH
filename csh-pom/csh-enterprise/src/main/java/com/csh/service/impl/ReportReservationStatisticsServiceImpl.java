package com.csh.service.impl; 

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.ReportReservationStatisticsDao;
import com.csh.entity.ReportReservationStatistics;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.ReportReservationStatisticsService;

@Service("reportReservationStatisticsServiceImpl")
public class ReportReservationStatisticsServiceImpl extends BaseServiceImpl<ReportReservationStatistics,Long> implements ReportReservationStatisticsService {

      @Resource(name="reportReservationStatisticsDaoImpl")
      ReportReservationStatisticsDao reportReservationStatisticsDao;
      @Resource
      public void setBaseDao(ReportReservationStatisticsDao reportReservationStatisticsDao) {
         super.setBaseDao(reportReservationStatisticsDao);
  }

}