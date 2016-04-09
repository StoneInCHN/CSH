package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.ReportServiceStatistics;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ReportServiceStatisticsDao;
@Repository("reportServiceStatisticsDaoImpl")
public class ReportServiceStatisticsDaoImpl extends  BaseDaoImpl<ReportServiceStatistics,Long> implements ReportServiceStatisticsDao {

}