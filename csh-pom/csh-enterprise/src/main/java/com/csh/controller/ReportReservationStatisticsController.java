package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.controller.base.BaseController;
import com.csh.entity.ReportReservationStatistics;
import com.csh.service.ReportReservationStatisticsService;

/**
 * 维修预约
 * @author huyong
 *
 */
@Controller ("reportReservationStatisticsController")
@RequestMapping ("console/reportReservationStatistics")
public class ReportReservationStatisticsController extends BaseController
{

  @Resource (name = "reportReservationStatisticsServiceImpl")
  private ReportReservationStatisticsService reportReservationStatisticsService;
  
  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/report", method = RequestMethod.GET)
  public @ResponseBody ReportReservationStatistics report(ModelMap model) {
    return reportReservationStatisticsService.findEntityByTenantID ();
  }
}
