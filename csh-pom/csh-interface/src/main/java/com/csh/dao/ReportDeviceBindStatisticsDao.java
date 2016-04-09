package com.csh.dao;

import java.util.Date;

import com.csh.entity.ReportDeviceBindStatistics;
import com.csh.framework.dao.BaseDao;

public interface ReportDeviceBindStatisticsDao extends BaseDao<ReportDeviceBindStatistics, Long> {


  ReportDeviceBindStatistics getReportByDate(Date date);
}
