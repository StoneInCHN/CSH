package com.csh.service;

import java.util.Date;

import com.csh.entity.ReportUserRegStatistics;
import com.csh.framework.service.BaseService;

public interface ReportUserRegStatisticsService extends BaseService<ReportUserRegStatistics, Long> {


  ReportUserRegStatistics getReportByDate(Date date);

}
