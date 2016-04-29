package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csh.controller.base.BaseController;
import com.csh.entity.CommissionRate;
import com.csh.framework.paging.Pageable;
import com.csh.service.CommissionRateService;

@RequestMapping("console/commissionRate")
@Controller("commissionRateController")
public class CommissionRateController extends BaseController {

  @Resource(name = "commissionRateServiceImpl")
  private CommissionRateService commissionRateService;

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("commissionRate", commissionRateService.find(id));
    return "/commissionRate/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(CommissionRate commissionRate) {
    if (!isValid(commissionRate)) {
      return ERROR_VIEW;
    }
    commissionRateService.update(commissionRate);
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", commissionRateService.findPage(pageable));
    return "/commissionRate/list";
  }

}
