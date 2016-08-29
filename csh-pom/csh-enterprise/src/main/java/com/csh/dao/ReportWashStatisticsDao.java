package com.csh.dao; 
import java.util.List;

import com.csh.entity.ReportWashStatistics;
import com.csh.framework.dao.BaseDao;

public interface ReportWashStatisticsDao extends  BaseDao<ReportWashStatistics,Long>{

  List<ReportWashStatistics> monthlyReport(Integer maximum, Long tenantID);

}