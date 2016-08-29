package com.csh.dao.impl; 

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository; 
import org.springframework.util.CollectionUtils;

import com.csh.entity.ReportWashStatistics;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.utils.DateTimeUtils;
import com.csh.dao.ReportWashStatisticsDao;
@Repository("reportWashStatisticsDaoImpl")
public class ReportWashStatisticsDaoImpl extends  BaseDaoImpl<ReportWashStatistics,Long> implements ReportWashStatisticsDao {

  @Override
  public List<ReportWashStatistics> monthlyReport(Integer maximum, Long tenantID) {
    List<ReportWashStatistics> reportList = new ArrayList<ReportWashStatistics>();
    //组装查询语句
    StringBuffer jpqlBuffer = new StringBuffer();
    jpqlBuffer.append("select date_format(statistics_date,'%Y-%m'),sum(total_income) from csh_report_wash_statistics");
    if (tenantID != null) {
      jpqlBuffer.append(" where tenantid = ");
      jpqlBuffer.append(tenantID);
    }    
    jpqlBuffer.append(" group by date_format(statistics_date,'%Y-%m') order by date_format(statistics_date,'%Y-%m') desc limit ");
    jpqlBuffer.append(maximum);

    //执行查询语句
    List list = entityManager.createNativeQuery(jpqlBuffer.toString())
        .setFlushMode(FlushModeType.COMMIT).getResultList();
    
    //解析封装返回对象
    if (!CollectionUtils.isEmpty(list)) {
      for (Object object : list) {
        Object[] entity = (Object[]) object;
        ReportWashStatistics report = new ReportWashStatistics(); 
        report.setTenantID(tenantID);
        report.setStatisticsDate(DateTimeUtils.getDateFromString(
            DateTimeUtils.yearMonthFormat, entity[0].toString()));
        report.setTotalIncome((Double)entity[1]);
        reportList.add(report);
      }
    }
    return reportList;
  }

}