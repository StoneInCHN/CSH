package com.csh.controller;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.controller.base.BaseController;
import com.csh.entity.ReportDeviceBindStatistics;
import com.csh.service.ReportDeviceBindStatisticsService;

@Controller("reportDeviceBindStatisticsController")
@RequestMapping("console/reportDeviceBind")
public class ReportDeviceBindStatisticsController extends BaseController {

  @Resource(name = "reportDeviceBindStatisticsServiceImpl")
  private ReportDeviceBindStatisticsService reportDeviceBindStatisticsService;

  @RequestMapping(value = "/reportDeviceBind", method = RequestMethod.GET)
  public String reportUserReg() {
    return "/reportDeviceBind/reportDeviceBind";
  }

  @RequestMapping(value = "/getDeviceBindReport", method = RequestMethod.GET)
  public @ResponseBody List<ReportDeviceBindStatistics> getUserRegReport(Date beginDate,
      Date endDate) {
    if (beginDate == null || endDate == null) {
      Calendar current = Calendar.getInstance();
      current.set(Calendar.DATE, -3);
      Date date = new Date();
      date.setTime(current.getTimeInMillis());
      beginDate = date;
      endDate = new Date();
    }
    return reportDeviceBindStatisticsService.findList(beginDate, endDate);
  }

}
