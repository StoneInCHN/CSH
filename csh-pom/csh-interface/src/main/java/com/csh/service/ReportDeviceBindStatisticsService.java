package com.csh.service;

import java.util.Date;

import com.csh.entity.ReportDeviceBindStatistics;
import com.csh.framework.service.BaseService;

public interface ReportDeviceBindStatisticsService extends
    BaseService<ReportDeviceBindStatistics, Long> {

  ReportDeviceBindStatistics getReportByDate(Date date);

}
