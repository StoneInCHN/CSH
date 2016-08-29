package com.csh.dao; 
import java.util.List;

import com.csh.entity.ReportRepareStatistics;
import com.csh.framework.dao.BaseDao;

public interface ReportRepareStatisticsDao extends  BaseDao<ReportRepareStatistics,Long>{

  List<ReportRepareStatistics> monthlyReport(Integer maximum, Long tenantID);

}