package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.ReportRepareStatistics;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ReportRepareStatisticsDao;
@Repository("reportRepareStatisticsDaoImpl")
public class ReportRepareStatisticsDaoImpl extends  BaseDaoImpl<ReportRepareStatistics,Long> implements ReportRepareStatisticsDao {

}