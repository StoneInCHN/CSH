package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.TenantInfo;
import com.csh.framework.paging.Pageable;
import com.csh.service.TenantInfoService;

@Controller("tenantInfoController")
@RequestMapping("console/tenantInfo")
public class TenantInfoController extends BaseController {

  @Resource(name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;

  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add() {
    return "/tenantInfo/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(TenantInfo tenantInfo, RedirectAttributes redirectAttributes) {
    if (!isValid(tenantInfo)) {
      return ERROR_VIEW;
    }
    tenantInfoService.save(tenantInfo);
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("tenant", tenantInfoService.find(id));
    return "/tenantInfo/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(TenantInfo tenantInfo) {
    if (!isValid(tenantInfo)) {
      return ERROR_VIEW;
    }
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", tenantInfoService.findPage(pageable));
    return "/tenantInfo/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      tenantInfoService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

}
