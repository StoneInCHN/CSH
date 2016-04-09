package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.ReportUserRegStatistics;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ReportUserRegStatisticsDao;
@Repository("reportUserRegStatisticsDaoImpl")
public class ReportUserRegStatisticsDaoImpl extends  BaseDaoImpl<ReportUserRegStatistics,Long> implements ReportUserRegStatisticsDao {

}