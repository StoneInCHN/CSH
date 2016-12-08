package com.csh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Setting;
import com.csh.controller.base.BaseController;
import com.csh.entity.DeviceInfo;
import com.csh.entity.Vehicle;
import com.csh.entity.VehicleOil;
import com.csh.entity.commonenum.CommonEnum.OilType;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.service.DeviceInfoService;
import com.csh.service.VehicleOilService;
import com.csh.utils.ApiUtils;
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

  @Resource(name = "vehicleOilServiceImpl")
  private VehicleOilService vehicleOilService;

  @Resource(name = "deviceInfoServiceImpl")
  private DeviceInfoService deviceInfoService;


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
   * 获取车辆所在地的当日油价，默认93号汽油
   * 
   * @param deviceId
   * @param fromDate
   * @param toDate
   * @return
   */
  @RequestMapping(value = "/getOilPrice", method = RequestMethod.POST)
  public @ResponseBody String oilPrice(String deviceId) {
    List<Filter> filters = new ArrayList<Filter>();
    Filter deviceNoFilter = new Filter("deviceNo", Operator.eq, deviceId);
    filters.add(deviceNoFilter);
    List<DeviceInfo> deviceInfos = deviceInfoService.findList(null, filters, null);
    if (deviceInfos != null && deviceInfos.size() == 1) {
      Vehicle vehicle = deviceInfos.get(0).getVehicle();
      String plate = vehicle.getPlate();
      if (plate == null) {
        return "0";
      }
      plate = plate.substring(0, 1);
      OilType oilType = vehicle.getVehicleBrandDetail().getOilType();
      if (oilType == null) {
        // 默认93号汽油
        oilType = OilType.P93;
      }
      List<Filter> oilFilters = new ArrayList<Filter>();
      Filter plateFilter = new Filter("shortPlate", Operator.eq, plate);
      Filter oilTypeFilter = new Filter("oilType", Operator.eq, oilType);
      oilFilters.add(plateFilter);
      oilFilters.add(oilTypeFilter);
      List<VehicleOil> oils = vehicleOilService.findList(null, oilFilters, null);
      if (oils == null || oils.size() != 1) {
        return "0";
      }
      VehicleOil oil = oils.get(0);
      return oil.getPrice().toString();
    }
    return "0";
  }

  /**
   * @param params 参数：deviceId=8801001667&fromDate=2016-4-1&toDate=2016-4-30
   */
  @RequestMapping(value = "/monthlyVehicleStatus", method = RequestMethod.POST)
  public @ResponseBody String monthlyVehicleStatus(String deviceId, String fromDate, String toDate) {
    // String mileageJson =
    // "{'msg':[{'dailyMileage': 10,'averageFuelConsumption': 17,'fuelConsumption': 16,'cost': null,'averageSpeed': 19,'emergencybrakecount': 2,'suddenturncount': 0,'rapidlyspeedupcount': 4,'createdate': 1459872000000,'day': 6},{'dailyMileage': 23,'averageFuelConsumption': 7,'fuelConsumption': 9,'cost': null,'averageSpeed': 8,'emergencybrakecount': 7,'suddenturncount': 10,'rapidlyspeedupcount': 13,'createdate': 1459958400000,'day': 7}]}";
    // return mileageJson.replaceAll("'", "\"");
    String mileageJson = null;
    if (deviceId != null && fromDate != null && toDate != null) {
      StringBuffer params = new StringBuffer();
      params.append("deviceId=");
      params.append(deviceId);
      params.append("&fromDate=");
      params.append(fromDate);
      params.append("&toDate=");
      params.append(toDate);
      mileageJson =
          ApiUtils.post(
              setting.getObdServiceUrl() + "tenantVehicleData/monthlyVehicleStatus.jhtml",
              params.toString());
      return mileageJson;
    } else {
      return "";
    }
  }
}
