package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.beans.Setting.ImageType;
import com.csh.controller.base.BaseController;
import com.csh.entity.VehicleBrand;
import com.csh.entity.VehicleLine;
import com.csh.framework.paging.Pageable;
import com.csh.service.FileService;
import com.csh.service.VehicleBrandService;
import com.csh.service.VehicleLineService;

@RequestMapping("console/vehicleLine")
@Controller("vehicleLineController")
public class VehicleLineController extends BaseController{

  @Resource(name="vehicleLineServiceImpl")
  private VehicleLineService vehicleLineService;
  
  @Resource(name="vehicleBrandServiceImpl")
  private VehicleBrandService vehicleBrandService;
  
  @Resource(name = "fileServiceImpl")
  private FileService fileService;

  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model) {
    model.addAttribute("vehicleBrands", vehicleBrandService.findAll());
    model.addAttribute("vehicleLines", vehicleLineService.findAll());
    return "/vehicleLine/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(VehicleLine vehicleLine,Long parentId,Long vehicleBrandId) {
    if (!isValid(vehicleLine)) {
      return ERROR_VIEW;
    }
    if (parentId !=null) {
      VehicleLine parent = vehicleLineService.find(parentId);
      vehicleLine.setParent(parent);
      vehicleLine.setGrade(parent.getGrade() + 1);
    }else{
      vehicleLine.setGrade(1);
    }
    VehicleBrand vehicleBrand =null;
    if (vehicleBrandId !=null) {
      vehicleBrand = vehicleBrandService.find(vehicleBrandId);
      vehicleLine.setVehicleBrand(vehicleBrand);
    }
    String iconImage = "";
    if (vehicleLine.getIconFile() != null && vehicleLine.getIconFile().getSize() >0) {
      iconImage = fileService.saveImage(vehicleLine.getIconFile(), ImageType.VEHICLEICON);
    }else{
       if (vehicleBrand !=null) {
         iconImage = vehicleBrand.getIcon();
      }
    }
    vehicleLine.setIcon(iconImage);
    vehicleLineService.save(vehicleLine);;
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("vehicleLine", vehicleLineService.find(id));
    model.addAttribute("vehicleBrands", vehicleBrandService.findAll());
    model.addAttribute("vehicleLines", vehicleLineService.findAll());
    return "/vehicleLine/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(VehicleLine vehicleLine,Long parentId,Long vehicleBrandId) {
    if (!isValid(vehicleLine)) {
      return ERROR_VIEW;
    }
    VehicleLine temp = vehicleLineService.find(vehicleLine.getId());
    if (vehicleLine.getIconFile() != null && vehicleLine.getIconFile().getSize() > 0) {
      String iconImage = fileService.saveImage(vehicleLine.getIconFile(), ImageType.VEHICLEICON);
      temp.setIcon(iconImage);
    }
    if (parentId !=null) {
      VehicleLine parent = vehicleLineService.find(parentId);
      vehicleLine.setParent(parent);
      vehicleLine.setGrade(parent.getGrade() + 1);
    }else{
      vehicleLine.setGrade(1);
    }
    if (vehicleBrandId !=null) {
      vehicleLine.setVehicleBrand(vehicleBrandService.find(vehicleBrandId));
    }
    if (vehicleLine.getIconFile() != null && vehicleLine.getIconFile().getSize() >0) {
      String iconImage = fileService.saveImage(vehicleLine.getIconFile(), ImageType.VEHICLEICON);
      vehicleLine.setIcon(iconImage);
    }
    temp.setCode(vehicleLine.getCode());
    temp.setName(vehicleLine.getName());
    vehicleLineService.update(vehicleLine);
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", vehicleLineService.findPage(pageable));
    return "/vehicleLine/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      vehicleLineService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }
  
}
