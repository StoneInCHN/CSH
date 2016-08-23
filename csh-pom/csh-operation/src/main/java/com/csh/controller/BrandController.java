package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.estore.Brand;
import com.csh.framework.paging.Pageable;
import com.csh.service.BrandService;

@Controller("brandController")
@RequestMapping("console/brand")
public class BrandController extends BaseController {

  @Resource(name = "brandServiceImpl")
  private BrandService brandService;

  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add() {
    return "/deviceType/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(Brand brand) {
    if (!isValid(brand)) {
      return ERROR_VIEW;
    }
    brandService.save(brand);
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("brand", brandService.find(id));
    return "/brand/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(Brand brand) {
    if (!isValid(brand)) {
      return ERROR_VIEW;
    }
    Brand temp = brandService.find(brand.getId());
    temp.setName(brand.getName());
    brandService.update(temp);
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", brandService.findPage(pageable));
    return "/brand/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      brandService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }


}
