package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csh.controller.base.BaseController;
import com.csh.service.DriverLicenseService;

@RequestMapping("console/driverLicense")
@Controller("driverLicenseController")
public class DriverLicenseController extends BaseController {

  @Resource(name = "driverLicenseServiceImpl")
  private DriverLicenseService driverLicenseService;

  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(Long id, ModelMap model) {
    model.addAttribute("driverLicense", driverLicenseService.find(id));
    return "/driverLicense/details";
  }

}
