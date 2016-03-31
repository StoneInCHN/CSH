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
import com.csh.framework.paging.Pageable;
import com.csh.service.FileService;
import com.csh.service.VehicleBrandService;

@Controller("vehicleBrandController")
@RequestMapping("console/vehicleBrand")
public class VehicleBrandController extends BaseController {

  @Resource(name = "vehicleBrandServiceImpl")
  private VehicleBrandService vehicleBrandService;

  @Resource(name = "fileServiceImpl")
  private FileService fileService;

  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add() {
    return "/vehicleBrand/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(VehicleBrand vehicleBrand) {
    if (!isValid(vehicleBrand)) {
      return ERROR_VIEW;
    }
    String iconImage = "";
    if (vehicleBrand.getIconFile() != null) {
      iconImage = fileService.saveImage(vehicleBrand.getIconFile(), ImageType.VEHICLEICON);
    }
    vehicleBrand.setIcon(iconImage);
    vehicleBrandService.save(vehicleBrand);;
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("vehicleBrand", vehicleBrandService.find(id));
    return "/vehicleBrand/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(VehicleBrand vehicleBrand) {
    if (!isValid(vehicleBrand)) {
      return ERROR_VIEW;
    }
    VehicleBrand temp = vehicleBrandService.find(vehicleBrand.getId());
    if (vehicleBrand.getIconFile() != null && vehicleBrand.getIconFile().getSize() > 0) {
      String iconImage = fileService.saveImage(vehicleBrand.getIconFile(), ImageType.VEHICLEICON);
      temp.setIcon(iconImage);
    }
    temp.setCode(vehicleBrand.getCode());
    temp.setName(vehicleBrand.getName());
    vehicleBrandService.update(temp);
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", vehicleBrandService.findPage(pageable));
    return "/vehicleBrand/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      vehicleBrandService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

}
