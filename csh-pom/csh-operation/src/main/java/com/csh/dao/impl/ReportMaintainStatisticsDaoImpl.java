package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.ReportMaintainStatistics;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ReportMaintainStatisticsDao;
@Repository("reportMaintainStatisticsDaoImpl")
public class ReportMaintainStatisticsDaoImpl extends  BaseDaoImpl<ReportMaintainStatistics,Long> implements ReportMaintainStatisticsDao {

}