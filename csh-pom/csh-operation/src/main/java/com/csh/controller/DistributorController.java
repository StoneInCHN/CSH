package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
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
    return "/distributor/list";
  }



  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add() {
    return "/distributor/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(Distributor distributor, Long areaId,
      RedirectAttributes redirectAttributes, ModelMap map) {
    distributor.setArea(areaService.find(areaId));
    distributorService.save(distributor);
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("distributor", distributorService.find(id));
    return "/distributor/edit";
  }


  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(Distributor distributor,Long areaId, ModelMap map) {
    distributor.setArea(areaService.find(areaId));
    distributorService.update(distributor, "createDate");
    return "redirect:list.jhtml";
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
