package com.csh.dao; 
import java.util.Date;
import java.util.List;

import com.csh.entity.ReportUserRegStatistics;
import com.csh.framework.dao.BaseDao;

public interface ReportUserRegStatisticsDao extends  BaseDao<ReportUserRegStatistics,Long>{
  List<ReportUserRegStatistics> findList(Date startDate,Date endDate);
}