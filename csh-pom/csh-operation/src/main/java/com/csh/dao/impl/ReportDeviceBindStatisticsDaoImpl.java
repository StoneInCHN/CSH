package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.ReportDeviceBindStatistics;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ReportDeviceBindStatisticsDao;
@Repository("reportDeviceBindStatisticsDaoImpl")
public class ReportDeviceBindStatisticsDaoImpl extends  BaseDaoImpl<ReportDeviceBindStatistics,Long> implements ReportDeviceBindStatisticsDao {

}