package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.TenantImage;
import com.csh.entity.TenantInfo;
import com.csh.service.TenantAccountService;
import com.csh.service.TenantImageService;
import com.csh.service.TenantInfoService;

/**
 * 租户图片
 * @author huyong
 *
 */
@Controller ("tenantInfoController")
@RequestMapping ("console/tenantInfo")
public class TenantInfoController extends BaseController
{

  @Resource (name = "tenantImageServiceImpl")
  private TenantImageService tenantImageService;
  @Resource (name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;
  @Resource (name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @RequestMapping (value = "/tenantInfo", method = RequestMethod.GET)
  public String tenantInfo (ModelMap model)
  {
    TenantInfo tenantInfo = tenantInfoService.find (tenantAccountService
        .getCurrentTenantInfo ().getId ());
    model.addAttribute ("tenantInfo", tenantInfo);
    model.addAttribute ("areaName", tenantInfo.getArea ().getFullName ());
    String businessTime = tenantInfo.getBusinessTime ();
    if (businessTime != null && businessTime.split ("-").length > 1)
    {
      model.addAttribute ("businessTimeStart", businessTime.split ("-")[0]);
      model.addAttribute ("businessTimeEnd", businessTime.split ("-")[1]);
    }
    model.addAttribute ("tenantImageList", tenantInfo.getTenantImages ());
    return "tenantInfo/tenantInfo";
  }
  @RequestMapping (value = "/edit", method = RequestMethod.POST)
  public @ResponseBody Message tenantInfo (ModelMap model,TenantInfo tenantInfo,String[] tenantImageList,String businessTimeStart,String businessTimeEnd)
  {
    tenantInfo.setBusinessTime (businessTimeStart+"-"+businessTimeEnd);
    tenantInfoService.saveTenantInfo(tenantInfo,tenantImageList);
    return SUCCESS_MESSAGE;
  }
  @RequestMapping (value = "/deleteImage", method = RequestMethod.POST)
  public @ResponseBody Message deleteImage (ModelMap model,Long id)
  {
    tenantImageService.delete (id);
    return SUCCESS_MESSAGE;
  }
}
