package com.csh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.CommonAttributes;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.DeviceInfo;
import com.csh.entity.Distributor;
import com.csh.entity.TenantInfo;
import com.csh.entity.Vehicle;
import com.csh.framework.filter.Filter;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.BaseResponse;
import com.csh.json.request.VehicleStatus;
import com.csh.service.DeviceInfoService;
import com.csh.service.TenantInfoService;
import com.csh.service.VehicleService;

@RequestMapping("console/vehicle")
@Controller("vehicleController")
public class VehicleController extends BaseController {

  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;

  @Resource(name = "deviceInfoServiceImpl")
  private DeviceInfoService deviceInfoService;

  @Resource(name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;

  /**
   * 详情
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(Long id, ModelMap model) {
    model.addAttribute("vehicle", vehicleService.find(id));
    return "/vehicle/details";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    List<Filter> filters = new ArrayList<Filter>();
    filters.add(Filter.ne("plate", "0000000"));
    pageable.setFilters(filters);
    Page<Vehicle> page = vehicleService.findPage(pageable);
    List<Vehicle> vehicles = page.getContent();
    List<Vehicle> lists = new ArrayList<Vehicle>();
    for (Vehicle vehicle : vehicles) {
      // 获取商家及分销商信息
      TenantInfo info = tenantInfoService.find(vehicle.getTenantID());
      if (info != null) {
        vehicle.setTenantName(info.getTenantName());
        Distributor distributor = info.getDistributor();
        if (distributor != null) {
          vehicle.setDistributorName(distributor.getDistributorName());
        }
      }
      lists.add(vehicle);
    }
    model.addAttribute("page", new Page<Vehicle>(lists, page.getTotal(), page.getPageable()));
    return "/vehicle/list";
  }


  /**
   * 推送车辆状态
   * 
   * @param id
   * @param model
   * @return
   */
  @RequestMapping(value = "/pushVehicleStatus", method = RequestMethod.POST)
  public @ResponseBody BaseResponse pushVehicleStatus(
      @RequestBody List<VehicleStatus> vehicleStatusList) {
    BaseResponse response = new BaseResponse();
    List<Vehicle> vehicles = new ArrayList<>();
    for (VehicleStatus vehicleStatus : vehicleStatusList) {
      if (LogUtil.isDebugEnabled(VehicleController.class)) {
        LogUtil
            .debug(
                VehicleController.class,
                "pushVehicleStatus",
                "get push vehicle status from obd server. deviceNo: %s,wainingInfo: %s,faultCode: %s,isOnline: %s",
                vehicleStatus.getDeviceNo(), vehicleStatus.getWainingInfo(),
                vehicleStatus.getFaultCode(), vehicleStatus.getIsOnline());
      }

      DeviceInfo deviceInfo = deviceInfoService.findByDeviceNo(vehicleStatus.getDeviceNo());
      if (deviceInfo != null && deviceInfo.getVehicle() != null) {
        Vehicle vehicle = deviceInfo.getVehicle();
        vehicle.setWarning(vehicleStatus.getWainingInfo());
        vehicle.setFaultCode(vehicleStatus.getFaultCode());
        if ("1".equals(vehicleStatus.getIsOnline())) {
          vehicle.setIsOnline(true);
        } else {
          vehicle.setIsOnline(false);
        }
        vehicles.add(vehicle);
      }
    }
    vehicleService.update(vehicles);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }
}
