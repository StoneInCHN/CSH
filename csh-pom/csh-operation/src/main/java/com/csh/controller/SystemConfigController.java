package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csh.controller.base.BaseController;
import com.csh.entity.Coupon;
import com.csh.entity.SystemConfig;
import com.csh.entity.commonenum.CommonEnum.CouponSendType;
import com.csh.framework.paging.Pageable;
import com.csh.service.SystemConfigService;

@RequestMapping("console/systemConfig")
@Controller("systemConfigController")
public class SystemConfigController extends BaseController {

  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list( ModelMap model) {
    model.addAttribute("systemConfigs", systemConfigService.findAll());
    return "/systemConfig/list";
  }
  
  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("systemConfig",systemConfigService.find(id));
    return "/systemConfig/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(SystemConfig systemConfig) {
    SystemConfig temp = systemConfigService.find(systemConfig.getId());
    temp.setConfigValue(systemConfig.getConfigValue());
    systemConfigService.update(temp);
    return "redirect:list.jhtml";
  }

}
