package com.csh.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.controller.base.BaseController;
import com.csh.dao.ReportRepareStatisticsDao;
import com.csh.entity.ReportRepareStatistics;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.ordering.Ordering;
import com.csh.framework.ordering.Ordering.Direction;
import com.csh.framework.paging.Pageable;
import com.csh.service.ReportRepareStatisticsService;
import com.csh.service.TenantAccountService;
import com.csh.utils.ReportDataComparator;

/**
 * Controller - 维修报表
 * 
 * @author yohu
 *
 */
@Controller("reportRepareStatisticsController")
@RequestMapping("console/reportRepareStatistics")
public class ReportRepareStatisticsController extends BaseController {
  @Resource(name = "reportRepareStatisticsServiceImpl")
  private ReportRepareStatisticsService repareStatisticsService;
  
  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;  
  @Resource(name = "reportRepareStatisticsDaoImpl")
  private ReportRepareStatisticsDao reportRepareStatisticsDao;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/reportRepareStatistics", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/report/reportRepareStatistics";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/dailyReport", method = RequestMethod.POST)
  public @ResponseBody List<ReportRepareStatistics> dailyReport(Model model, Pageable pageable
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
    
    List<ReportRepareStatistics>  reportRepareStatisticsList = repareStatisticsService.findList (30, filters, orderings, true,null);
    ReportDataComparator comparator =new ReportDataComparator ("statisticsDate");
    Collections.sort (reportRepareStatisticsList, comparator);
    return reportRepareStatisticsList;
  }
  /**
   * 按月统计
   * 
   * @param maximum 最近多少个月
   * @return
   */
  @RequestMapping(value = "/monthlyReport", method = RequestMethod.POST)
  public @ResponseBody List<ReportRepareStatistics> monthlyReport(Integer maximum) {   
    if (maximum == null || maximum <= 0) {
      maximum = 12;//默认查询最近12个月数据
    }
    Long tenantID = tenantAccountService.getCurrentTenantID();
    List<ReportRepareStatistics>  reportRepareStatisticList = 
        reportRepareStatisticsDao.monthlyReport(maximum, tenantID);
    ReportDataComparator comparator = new ReportDataComparator("statisticsDate");
    Collections.sort (reportRepareStatisticList, comparator);
    return reportRepareStatisticList;
  }
}
