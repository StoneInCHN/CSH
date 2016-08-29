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
import com.csh.dao.ReportMaintainStatisticsDao;
import com.csh.entity.ReportMaintainStatistics;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.ordering.Ordering;
import com.csh.framework.ordering.Ordering.Direction;
import com.csh.framework.paging.Pageable;
import com.csh.service.ReportMaintainStatisticsService;
import com.csh.service.TenantAccountService;
import com.csh.utils.ReportDataComparator;

/**
 * Controller - 保养报表
 * 
 * @author yohu
 *
 */
@Controller("reportMaintainStatisticsController")
@RequestMapping("console/reportMaintainStatistics")
public class ReportMaintainStatisticsController extends BaseController {
  @Resource(name = "reportMaintainStatisticsServiceImpl")
  private ReportMaintainStatisticsService reportMaintainStatisticsService;
  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;  
  @Resource(name = "reportMaintainStatisticsDaoImpl")
  private ReportMaintainStatisticsDao reportMaintainStatisticsDao;
  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/reportMaintainStatistics", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/report/reportMaintainStatistics";
  }

  /**
   * 按天统计
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/dailyReport", method = RequestMethod.POST)
  public @ResponseBody List<ReportMaintainStatistics> dailyReport(Model model, Pageable pageable
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
      startDateFilter.setOperator (Operator.ge);
      startDateFilter.setProperty ("statisticsDate");
      startDateFilter.setValue (beginDate);
      filters.add (startDateFilter);
    }
    
    if (endDate != null)
    {
      Filter endDateFilter = new Filter();
      endDateFilter.setProperty ("statisticsDate");
      endDateFilter.setValue (endDate);
      endDateFilter.setOperator (Operator.le);
      filters.add (endDateFilter);
    }
    
    List<ReportMaintainStatistics>  reportMaintainStatisticList = reportMaintainStatisticsService.findList (30, filters, orderings, true,null);
    ReportDataComparator comparator =new ReportDataComparator ("statisticsDate");
    Collections.sort (reportMaintainStatisticList, comparator);
    return reportMaintainStatisticList;
  }
  /**
   * 按月统计
   * 
   * @param maximum 最近多少个月
   * @return
   */
  @RequestMapping(value = "/monthlyReport", method = RequestMethod.POST)
  public @ResponseBody List<ReportMaintainStatistics> monthlyReport(Integer maximum) {   
    if (maximum == null || maximum <= 0) {
      maximum = 12;//默认查询最近12个月数据
    }
    Long tenantID = tenantAccountService.getCurrentTenantID();
    List<ReportMaintainStatistics>  reportMaintainStatisticList = 
        reportMaintainStatisticsDao.monthlyReport(maximum, tenantID);
    ReportDataComparator comparator = new ReportDataComparator("statisticsDate");
    Collections.sort (reportMaintainStatisticList, comparator);
    return reportMaintainStatisticList;
  }
}
