package com.csh.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.controller.base.BaseController;
import com.csh.service.ServiceCategoryService;

/**
 * 服务配置
 * @author huyong
 *
 */
@Controller ("serviceCategoryController")
@RequestMapping ("console/serviceCategory")
public class ServiceCategoryController extends BaseController
{

  @Resource (name = "serviceCategoryServiceImpl")
  private ServiceCategoryService serviceCategoryService;
  
  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/findAllServiceCategory", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAllServiceCategory(ModelMap model) {
	return serviceCategoryService.findAllServiceCategory();
  }
  
}
