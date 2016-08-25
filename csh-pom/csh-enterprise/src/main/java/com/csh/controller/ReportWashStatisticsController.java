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
import com.csh.entity.ReportMaintainStatistics;
import com.csh.entity.ReportRepareStatistics;
import com.csh.entity.ReportWashStatistics;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.ordering.Ordering;
import com.csh.framework.ordering.Ordering.Direction;
import com.csh.framework.paging.Pageable;
import com.csh.service.ReportMaintainStatisticsService;
import com.csh.service.ReportWashStatisticsService;
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
  @RequestMapping(value = "/report", method = RequestMethod.POST)
  public @ResponseBody List<ReportWashStatistics> list(Model model, Pageable pageable
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
}
