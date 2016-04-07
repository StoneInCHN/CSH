package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csh.controller.base.BaseController;
import com.csh.framework.paging.Pageable;
import com.csh.service.TenantClearingRecordService;

@RequestMapping("console/clearingRecord")
@Controller("tenantClearingRecordController")
public class TenantClearingRecordController extends BaseController{

  @Resource(name="tenantClearingRecordServiceImpl")
  private TenantClearingRecordService tenantClearingRecordService;
  
  /**
   * 详情
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(Long id, ModelMap model) {
    model.addAttribute("clearingRecord", tenantClearingRecordService.find(id));
    return "/clearingRecord/details";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", tenantClearingRecordService.findPage(pageable));
    return "/clearingRecord/list";
  }
}
