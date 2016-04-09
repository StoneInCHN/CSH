package com.csh.service; 

import java.util.Date;
import java.util.List;

import com.csh.entity.ReportUserRegStatistics;
import com.csh.framework.service.BaseService;

public interface ReportUserRegStatisticsService extends BaseService<ReportUserRegStatistics,Long>{
  
  List<ReportUserRegStatistics> findList(Date startDate,Date endDate);
  
}