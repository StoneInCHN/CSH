package com.csh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csh.controller.base.BaseController;
import com.csh.entity.Distributor;
import com.csh.entity.TenantInfo;
import com.csh.entity.Vehicle;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.TenantInfoService;
import com.csh.service.VehicleService;

@RequestMapping("console/vehicle")
@Controller("vehicleController")
public class VehicleController extends BaseController {

  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;
  
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
	  Page<Vehicle> page = vehicleService.findPage(pageable);
	  List<Vehicle> vehicles = page.getContent();
	  List<Vehicle> lists = new ArrayList<Vehicle>();
	  for(Vehicle vehicle:vehicles){
		  TenantInfo info = tenantInfoService.find(vehicle.getId());
		  if (info !=null) {
			  vehicle.setTenantName(info.getTenantName());	
			  Distributor distributor = info.getDistributor();
			  if(distributor!=null){
				  vehicle.setDistributorName(distributor.getDistributorName());
			  }
		  }
		  lists.add(vehicle);
	  }
    model.addAttribute("page",new Page<Vehicle>(lists, page.getTotal(), page.getPageable()));
    return "/vehicle/list";
  }
}
