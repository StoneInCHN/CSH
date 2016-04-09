package com.csh.dao.impl;

import java.util.Date;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.csh.dao.ReportDeviceBindStatisticsDao;
import com.csh.entity.ReportDeviceBindStatistics;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("reportDeviceBindStatisticsDaoImpl")
public class ReportDeviceBindStatisticsDaoImpl extends
    BaseDaoImpl<ReportDeviceBindStatistics, Long> implements ReportDeviceBindStatisticsDao {

  @Override
  public ReportDeviceBindStatistics getReportByDate(Date date) {
    if (date == null) {
      return null;
    }
    try {
      String jpql =
          "select report from ReportDeviceBindStatistics report where report.statisticsDate = :statisticsDate";
      return entityManager.createQuery(jpql, ReportDeviceBindStatistics.class)
          .setFlushMode(FlushModeType.COMMIT).setParameter("statisticsDate", date)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }


}
