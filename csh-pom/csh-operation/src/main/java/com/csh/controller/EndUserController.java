package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csh.controller.base.BaseController;
import com.csh.framework.paging.Pageable;
import com.csh.service.EndUserService;

@RequestMapping("console/endUser")
@Controller("endUserController")
public class EndUserController extends BaseController{

  @Resource(name="endUserServiceImpl")
  private EndUserService endUserService;
  
  /**
   * 详情
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(Long id, ModelMap model) {
    model.addAttribute("endUser", endUserService.find(id));
    return "/endUser/details";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", endUserService.findPage(pageable));
    return "/endUser/list";
  }
  
}
