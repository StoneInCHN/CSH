package com.csh.dao.impl; 

import org.springframework.stereotype.Repository;

import com.csh.dao.ReportReservationStatisticsDao;
import com.csh.entity.ReportReservationStatistics;
import com.csh.framework.dao.impl.BaseDaoImpl;
@Repository("reportReservationStatisticsDaoImpl")
public class ReportReservationStatisticsDaoImpl extends  BaseDaoImpl<ReportReservationStatistics,Long> implements ReportReservationStatisticsDao {

}