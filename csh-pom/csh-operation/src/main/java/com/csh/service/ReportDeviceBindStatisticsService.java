package com.csh.service; 

import java.util.Date;
import java.util.List;

import com.csh.entity.ReportDeviceBindStatistics;
import com.csh.framework.service.BaseService;

public interface ReportDeviceBindStatisticsService extends BaseService<ReportDeviceBindStatistics,Long>{
  List<ReportDeviceBindStatistics> findList(Date startDate,Date endDate);
}