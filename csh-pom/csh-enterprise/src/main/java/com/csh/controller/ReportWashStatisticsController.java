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
import com.csh.dao.ReportWashStatisticsDao;
import com.csh.entity.ReportWashStatistics;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.ordering.Ordering;
import com.csh.framework.ordering.Ordering.Direction;
import com.csh.framework.paging.Pageable;
import com.csh.service.ReportWashStatisticsService;
import com.csh.service.TenantAccountService;
import com.csh.utils.ReportDataComparator;

/**
 * Controller - 洗车报表
 * 
 * @author yohu
 *
 */
@Controller("reportWashStatisticsController")
@RequestMapping("console/reportWashStatistics")
public class ReportWashStatisticsController extends BaseController {
  @Resource(name = "reportWashStatisticsServiceImpl")
  private ReportWashStatisticsService reportWashStatisticsService;
  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;  
  @Resource(name = "reportWashStatisticsDaoImpl")
  private ReportWashStatisticsDao reportWashStatisticsDao;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/reportWashStatistics", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/report/reportWashStatistics";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/dailyReport", method = RequestMethod.POST)
  public @ResponseBody List<ReportWashStatistics> dailyReport(Model model, Pageable pageable
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
    
    List<ReportWashStatistics>  reportWashStatisticList = reportWashStatisticsService.findList (30, filters, orderings, true,null);
    ReportDataComparator comparator =new ReportDataComparator ("statisticsDate");
    Collections.sort (reportWashStatisticList, comparator);
    return reportWashStatisticList;
  }
  /**
   * 按月统计
   * 
   * @param maximum 最近多少个月
   * @return
   */
  @RequestMapping(value = "/monthlyReport", method = RequestMethod.POST)
  public @ResponseBody List<ReportWashStatistics> monthlyReport(Integer maximum) {   
    if (maximum == null || maximum <= 0) {
      maximum = 12;//默认查询最近12个月数据
    }
    Long tenantID = tenantAccountService.getCurrentTenantID();
    List<ReportWashStatistics>  reportWashStatisticList = 
        reportWashStatisticsDao.monthlyReport(maximum, tenantID);
    ReportDataComparator comparator = new ReportDataComparator("statisticsDate");
    Collections.sort (reportWashStatisticList, comparator);
    return reportWashStatisticList;
  }
}
