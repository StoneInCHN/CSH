package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.ReportWashStatistics;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ReportWashStatisticsDao;
@Repository("reportWashStatisticsDaoImpl")
public class ReportWashStatisticsDaoImpl extends  BaseDaoImpl<ReportWashStatistics,Long> implements ReportWashStatisticsDao {

}