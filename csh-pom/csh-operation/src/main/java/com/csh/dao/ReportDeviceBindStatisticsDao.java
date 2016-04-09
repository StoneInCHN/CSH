package com.csh.dao; 
import java.util.Date;
import java.util.List;

import com.csh.entity.ReportDeviceBindStatistics;
import com.csh.entity.ReportUserRegStatistics;
import com.csh.framework.dao.BaseDao;

public interface ReportDeviceBindStatisticsDao extends  BaseDao<ReportDeviceBindStatistics,Long>{
  List<ReportDeviceBindStatistics> findList(Date startDate,Date endDate);
}