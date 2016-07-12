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

import com.csh.beans.Setting;
import com.csh.controller.base.BaseController;
import com.csh.entity.ReportMaintainStatistics;
import com.csh.entity.ReportRepareStatistics;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.ordering.Ordering;
import com.csh.framework.ordering.Ordering.Direction;
import com.csh.framework.paging.Pageable;
import com.csh.service.ReportMaintainStatisticsService;
import com.csh.utils.ApiUtils;
import com.csh.utils.ReportDataComparator;
import com.csh.utils.SettingUtils;

/**
 * Controller - 保养报表
 * 
 * @author yohu
 *
 */
@Controller("ReportVehicleMileageController")
@RequestMapping("console/vehicleMileageReport")
public class ReportVehicleMileageController extends BaseController {
  private Setting setting = SettingUtils.get();
  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/vehicleMileageReport", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/report/vehicleMileageReport";
  }
  /**
   * @param params 参数：deviceId=8801001667&fromDate=2016-4-1&toDate=2016-4-30
   */
  @RequestMapping(value = "/monthlyVehicleStatus", method = RequestMethod.POST)
  public @ResponseBody String monthlyVehicleStatus(String deviceId, String fromDate, String toDate) {
//    String mileageJson = "{'msg':[{'dailyMileage': 10,'averageFuelConsumption': 17,'fuelConsumption': 16,'cost': null,'averageSpeed': 19,'emergencybrakecount': 2,'suddenturncount': 0,'rapidlyspeedupcount': 4,'createdate': 1459872000000,'day': 6},{'dailyMileage': 23,'averageFuelConsumption': 7,'fuelConsumption': 9,'cost': null,'averageSpeed': 8,'emergencybrakecount': 7,'suddenturncount': 10,'rapidlyspeedupcount': 13,'createdate': 1459958400000,'day': 7}]}";
//    return mileageJson.replaceAll("'", "\"");
    String mileageJson = null;
    if (deviceId != null && fromDate != null && toDate != null) {
      StringBuffer params = new StringBuffer();
      params.append("deviceId=");
      params.append(deviceId);
      params.append("&fromDate=");
      params.append(fromDate);
      params.append("&toDate=");
      params.append(toDate);
      mileageJson = ApiUtils.post(setting.getObdServiceUrl ()+"tenantVehicleData/monthlyVehicleStatus.jhtml",params.toString());
      return mileageJson;
    }else {
      return "";
    }
  }
}
