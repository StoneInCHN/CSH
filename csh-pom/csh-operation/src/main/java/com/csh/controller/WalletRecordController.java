package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csh.controller.base.BaseController;
import com.csh.framework.paging.Pageable;
import com.csh.service.WalletRecordService;

@RequestMapping("console/walletRecord")
@Controller("walletRecordController")
public class WalletRecordController extends BaseController{

  @Resource(name="walletRecordServiceImpl")
  private WalletRecordService walletRecordService;
  
  
  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", walletRecordService.findPage(pageable));
    return "/walletRecord/list";
  }
  
    
}
