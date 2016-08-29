package com.csh.dao; 
import java.util.List;

import com.csh.entity.ReportMaintainStatistics;
import com.csh.framework.dao.BaseDao;

public interface ReportMaintainStatisticsDao extends  BaseDao<ReportMaintainStatistics,Long>{

  List<ReportMaintainStatistics> monthlyReport(int maximum, Long tenantID);

}