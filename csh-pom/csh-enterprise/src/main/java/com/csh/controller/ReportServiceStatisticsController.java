package com.csh.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.ReportServiceStatistics;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.ordering.Ordering;
import com.csh.framework.ordering.Ordering.Direction;
import com.csh.framework.paging.Pageable;
import com.csh.service.ReportProcedureService;
import com.csh.service.ReportServiceStatisticsService;
import com.csh.utils.DateTimeUtils;
import com.csh.utils.ReportDataComparator;

/**
 * Controller - 服务统计报表
 * 
 * @author yohu
 *
 */
@Controller("reportServiceStatisticsController")
@RequestMapping("console/reportServiceStatistics")
public class ReportServiceStatisticsController extends BaseController {
  @Resource(name = "reportServiceStatisticsServiceImpl")
  private ReportServiceStatisticsService reportServiceStatisticsService;
  @Resource(name="reportProcedureServiceImpl")
  private ReportProcedureService reportProcedureService;

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/report", method = RequestMethod.POST)
  public @ResponseBody List<ReportServiceStatistics> list(Model model, Pageable pageable
      ,Date beginDate, Date endDate) {
    
    //时间倒序
    List<Ordering> orderings = new ArrayList<Ordering> ();
    Ordering dateCycleOrdering = new Ordering ("statisticsDate",
        Direction.desc);
    orderings.add (dateCycleOrdering);
    
    List<Filter> filters = new ArrayList<Filter> ();
    if (beginDate != null)
    {
      Filter startDateFilter = new Filter();
      startDateFilter.setOperator (Operator.gt);
      startDateFilter.setProperty ("statisticsDate");
      startDateFilter.setValue (beginDate);
      filters.add (startDateFilter);
    }
    
    if (endDate != null)
    {
      Filter endDateFilter = new Filter();
      endDateFilter.setProperty ("statisticsDate");
      endDateFilter.setValue (endDate);
      endDateFilter.setOperator (Operator.lt);
      filters.add (endDateFilter);
    }
    if (beginDate == null && endDate == null)
    {
        Date now = new Date ();
        Date start = DateTimeUtils.getRecentDate (now, "MONTH", -1);
        
        Filter startDateFilter = new Filter();
        startDateFilter.setProperty ("statisticsDate");
        startDateFilter.setValue (start);
        startDateFilter.setOperator (Operator.gt);
        filters.add (startDateFilter);
        
        Filter endDateFilter = new Filter();
        endDateFilter.setProperty ("statisticsDate");
        endDateFilter.setValue (now);
        endDateFilter.setOperator (Operator.lt);
        filters.add (endDateFilter);
    }
    
    List<ReportServiceStatistics>  reportServiceStatistics = reportServiceStatisticsService.findList (null, filters, orderings, true,null);
    ReportDataComparator comparator =new ReportDataComparator ("statisticsDate");
    Collections.sort (reportServiceStatistics, comparator);
    return reportServiceStatistics;
  }
  
  @RequestMapping(value = "/refresh", method = RequestMethod.POST)
  public @ResponseBody Message refresh(Model model, Pageable pageable
      ,Date beginDate, Date endDate) {
    
    if (beginDate != null && endDate != null)
    {
      Calendar current = Calendar.getInstance ();
      current.setTime (beginDate);
      while (DateUtils.truncate (current, Calendar.DATE).getTime ().before (DateUtils.truncate (endDate, Calendar.DATE)))
      {
        reportProcedureService.callProcedure ("csh_car_service_record_pr",DateTimeUtils.convertDateToString (current.getTime (), "YYYY-MM-dd"));
        current.add (Calendar.DATE, 1);
      }
    }
    else {
      reportProcedureService.callProcedure ("csh_car_service_record_pr",DateTimeUtils.convertDateToString (new Date (), "YYYY-MM-dd"));
    }
    return SUCCESS_MESSAGE;
  }
}
