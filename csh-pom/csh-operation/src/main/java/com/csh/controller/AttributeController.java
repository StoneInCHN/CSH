package com.csh.controller;

import java.util.Iterator;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.estore.Attribute;
import com.csh.entity.estore.Product;
import com.csh.framework.entity.BaseEntity.Save;
import com.csh.framework.paging.Pageable;
import com.csh.service.AttributeService;
import com.csh.service.ProductCategoryService;

@Controller("attributeController")
@RequestMapping("/console/attribute")
public class AttributeController extends BaseController{

  @Resource(name = "attributeServiceImpl")
  private AttributeService attributeService;
  @Resource(name = "productCategoryServiceImpl")
  private ProductCategoryService productCategoryService;

  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model) {
      model.addAttribute("productCategoryTree", productCategoryService.findTree());
      model.addAttribute("attributeValuePropertyCount", Product.ATTRIBUTE_VALUE_PROPERTY_COUNT);
      return "/attribute/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(Attribute attribute, Long productCategoryId, RedirectAttributes redirectAttributes) {
      for (Iterator<String> iterator = attribute.getOptions().iterator(); iterator.hasNext();) {
          String option = iterator.next();
          if (StringUtils.isEmpty(option)) {
              iterator.remove();
          }
      }
      attribute.setProductCategory(productCategoryService.find(productCategoryId));
      if (!isValid(attribute, Save.class)) {
          return ERROR_VIEW;
      }
      if (attribute.getProductCategory().getAttributes().size() >= Product.ATTRIBUTE_VALUE_PROPERTY_COUNT) {
      } else {
          attribute.setPropertyIndex(null);
          attributeService.save(attribute);
      }
      return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
      model.addAttribute("productCategoryTree", productCategoryService.findTree());
      model.addAttribute("attributeValuePropertyCount", Product.ATTRIBUTE_VALUE_PROPERTY_COUNT);
      model.addAttribute("attribute", attributeService.find(id));
      return "/attribute/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(Attribute attribute, RedirectAttributes redirectAttributes) {
      for (Iterator<String> iterator = attribute.getOptions().iterator(); iterator.hasNext();) {
          String option = iterator.next();
          if (StringUtils.isEmpty(option)) {
              iterator.remove();
          }
      }
      if (!isValid(attribute)) {
          return ERROR_VIEW;
      }
      attributeService.update(attribute, "propertyIndex", "productCategory");
      return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
      model.addAttribute("page", attributeService.findPage(pageable));
      return "/attribute/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody
  Message delete(Long[] ids) {
      attributeService.delete(ids);
      return SUCCESS_MESSAGE;
  }
  
}
