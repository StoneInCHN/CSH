package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.App;
import com.csh.service.AppService;
import com.csh.service.TenantAccountService;

/**
 * App配置
 * @author huyong
 *
 */
@Controller ("appController")
@RequestMapping ("console/app")
public class AppController extends BaseController
{

  @Resource (name = "appServiceImpl")
  private AppService appService;
  @Resource (name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  
  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/app", method = RequestMethod.GET)
  public String list(ModelMap model) {
    
    App app = appService.findEntityByTenantID ();
    model.put ("app", app);
    return "/app/app";
  }

  
//  @RequestMapping (value = "/add", method = RequestMethod.POST)
//  public @ResponseBody Message add (App app)
//  {
//    appService.save (app,true);
//    return SUCCESS_MESSAGE;
//  }
  
  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message update (App app)
  { 
    app.setTenantID(tenantAccountService.getCurrentTenantID());
    appService.update (app,"createDate");
    
    return SUCCESS_MESSAGE;
  }
  
}
