package com.csh.dao.impl;

import java.util.Date;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.csh.dao.ReportUserRegStatisticsDao;
import com.csh.entity.ReportUserRegStatistics;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("reportUserRegStatisticsDaoImpl")
public class ReportUserRegStatisticsDaoImpl extends BaseDaoImpl<ReportUserRegStatistics, Long>
    implements ReportUserRegStatisticsDao {

  @Override
  public ReportUserRegStatistics getReportByDate(Date date) {
    if (date == null) {
      return null;
    }
    try {
      String jpql =
          "select report from ReportUserRegStatistics report where report.statisticsDate = :statisticsDate";
      return entityManager.createQuery(jpql, ReportUserRegStatistics.class)
          .setFlushMode(FlushModeType.COMMIT).setParameter("statisticsDate", date)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

}
