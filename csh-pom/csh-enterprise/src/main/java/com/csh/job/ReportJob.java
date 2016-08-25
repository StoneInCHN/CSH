package com.csh.job;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.csh.common.log.LogUtil;
import com.csh.service.ReportProcedureService;
import com.csh.utils.DateTimeUtils;

@Component
@Lazy(false)
public class ReportJob
{
  
  
  @Resource(name = "reportProcedureServiceImpl")
  private ReportProcedureService reportProcedureService;
  
  
//  @Scheduled(cron="0/10 * *  * * ? ")   //每10秒执行一次
  @Scheduled(cron="59 59 23 * * ?")   //每天23点59分59秒执行
  public void reprotDonateStatistics()
  {
    
    Date currentDate = new Date ();
    
    LogUtil.debug (ReportJob.class, "reprotServiceStatistics", "reprot data generate start !");
    reportProcedureService.callProcedure ("csh_car_service_record_pr",DateTimeUtils.convertDateToString (currentDate, "YYYY-MM-dd"));
    LogUtil.debug (ReportJob.class, "reproServiceStatistics", "reprot data generate end!");
  }
}
