package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.VehicleBrandDetail;
import com.csh.entity.VehicleLine;
import com.csh.framework.paging.Pageable;
import com.csh.service.VehicleBrandDetailService;
import com.csh.service.VehicleLineService;

@Controller("vehicleBrandDetailController")
@RequestMapping("console/vehicleBrandDetail")
public class VehicleBrandDetailController extends BaseController {

  @Resource(name = "vehicleBrandDetailServiceImpl")
  private VehicleBrandDetailService vehicleBrandDetailService;
  
  @Resource(name = "vehicleLineServiceImpl")
  private VehicleLineService vehicleLineService;

  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model) {
    model.addAttribute("vehicleLines", vehicleLineService.findAll());
    return "/vehicleBrandDetail/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(VehicleBrandDetail vehicleBrandDetail ,Long vehicleLineId) {
    if (!isValid(vehicleBrandDetail)) {
      return ERROR_VIEW;
    }
    VehicleLine vehicleLine= vehicleLineService.find(vehicleLineId);
    vehicleBrandDetail.setVehicleLine(vehicleLine);
    vehicleBrandDetailService.save(vehicleBrandDetail);;
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("vehicleBrandDetail", vehicleBrandDetailService.find(id));
    model.addAttribute("vehicleLines", vehicleLineService.findAll());
    return "/vehicleBrandDetail/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(VehicleBrandDetail vehicleBrandDetail ,Long vehicleLineId) {
    if (!isValid(vehicleBrandDetail)) {
      return ERROR_VIEW;
    }
    VehicleLine vehicleLine= vehicleLineService.find(vehicleLineId);
    vehicleBrandDetail.setVehicleLine(vehicleLine);
    vehicleBrandDetailService.update(vehicleBrandDetail);
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", vehicleBrandDetailService.findPage(pageable));
    return "/vehicleBrandDetail/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      vehicleBrandDetailService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }


}
