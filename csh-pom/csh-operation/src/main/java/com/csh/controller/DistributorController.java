package com.csh.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csh.beans.Message;
import com.csh.beans.Setting.ImageType;
import com.csh.controller.base.BaseController;
import com.csh.entity.Admin;
import com.csh.entity.Distributor;
import com.csh.framework.paging.Pageable;
import com.csh.service.AdminService;
import com.csh.service.AreaService;
import com.csh.service.DistributorService;
import com.csh.service.FileService;

@Controller("distributorController")
@RequestMapping("/console/distributor")
public class DistributorController extends BaseController {

  @Resource(name = "distributorServiceImpl")
  private DistributorService distributorService;

  @Resource(name = "fileServiceImpl")
  private FileService fileService;

  @Resource(name = "adminServiceImpl")
  private AdminService adminService;
  @Resource(name = "areaServiceImpl")
  private AreaService areaService;


  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", distributorService.findPage(pageable));
    return "/vendor/list";
  }

  /**
   * 添加
   */
  @RequestMapping(value = "/simulator", method = RequestMethod.GET)
  public String simulator() {
    return "/vendor/simulator";
  }

  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add() {
    return "/vendor/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(Distributor vendor, MultipartFile file, Long areaId,
      RedirectAttributes redirectAttributes, ModelMap map) {
    Admin current = adminService.getCurrent();
    return "/vendor/info";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("vendor", distributorService.find(id));
    return "/vendor/edit";
  }

  /**
   * 审批
   */
  @RequestMapping(value = "/approval", method = RequestMethod.GET)
  public String approval(Long id, ModelMap model) {
    model.addAttribute("vendor", distributorService.find(id));
    return "/vendor/approval";
  }

  /**
   * 审批更新
   */
  @RequestMapping(value = "/statusUpdate", method = RequestMethod.POST)
  public String statusUpdate(Distributor vendor, ModelMap model) {
    Distributor vendorTemp = distributorService.find(vendor.getId());
    // vendorTemp.setVendorStatus(vendor.getVendorStatus());
    distributorService.update(vendorTemp);
    return "redirect:list.jhtml";
  }


  /**
   * 编辑
   */
  @RequestMapping(value = "/info", method = RequestMethod.GET)
  public String info(ModelMap model) {
    Admin current = adminService.getCurrent();
    // model.addAttribute("vendor", current.getVendor());
    return "/vendor/info";
  }


  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(Distributor vendor, MultipartFile file, Long areaId,
      RedirectAttributes redirectAttributes, ModelMap map) {
    if (!isValid(vendor)) {
      return ERROR_VIEW;
    }
    if (file.getSize() > 0) {
      String path = fileService.saveImage(file, ImageType.DISTRIBUTOR);
      // vendor.setVendorImagePath(path);
    }
    vendor.setModifyDate(new Date());
    // vendor.setVendorStatus(VendorStatus.AUDIT_WAITING);
    vendor.setArea(areaService.find(areaId));
    distributorService.update(vendor, "createDate", "chainStore", "admin", "autoServices");
    map.addAttribute("refresh", true);
    map.addAttribute("vendor", vendor);
    return "/vendor/info";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids.length >= distributorService.count()) {
      return Message.error("admin.common.deleteAllNotAllowed");
    }
    distributorService.delete(ids);
    return SUCCESS_MESSAGE;
  }

}
