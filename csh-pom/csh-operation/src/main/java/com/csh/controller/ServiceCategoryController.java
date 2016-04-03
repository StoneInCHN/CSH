package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.ServiceCategory;
import com.csh.framework.paging.Pageable;
import com.csh.service.ServiceCategoryService;

@RequestMapping("console/serviceCategory")
@Controller("serviceCategoryController")
public class ServiceCategoryController extends BaseController {

  @Resource(name = "serviceCategoryServiceImpl")
  private ServiceCategoryService serviceCategoryService;


  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model) {
    return "/serviceCategory/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(ServiceCategory serviceCategory) {
    serviceCategory.setIsSystem(false);
    serviceCategoryService.save(serviceCategory);
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("serviceCategory", serviceCategoryService.find(id));
    return "/serviceCategory/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(ServiceCategory serviceCategory) {
    serviceCategoryService.update(serviceCategory, "isSystem", "createDate");
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", serviceCategoryService.findPage(pageable));
    return "/serviceCategory/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      for (Long id : ids) {
        ServiceCategory serviceCategory = serviceCategoryService.find(id);
        if (!serviceCategory.getIsSystem()) {
          serviceCategoryService.delete(id);
        }
      }

    }
    return SUCCESS_MESSAGE;
  }


}
