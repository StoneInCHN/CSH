package com.csh.dao;

import java.util.Date;

import com.csh.entity.ReportUserRegStatistics;
import com.csh.framework.dao.BaseDao;

public interface ReportUserRegStatisticsDao extends BaseDao<ReportUserRegStatistics, Long> {

  ReportUserRegStatistics getReportByDate(Date date);

}
