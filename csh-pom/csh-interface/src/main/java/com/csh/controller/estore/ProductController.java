package com.csh.controller.estore;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csh.controller.base.MobileBaseController;
import com.csh.service.EndUserService;


/**
 * Controller - 电商商品
 * 
 * @author sujinxuan
 *
 */
@Controller("productController")
@RequestMapping("/estore/product")
public class ProductController extends MobileBaseController {

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;


  /**
   * 
   * 商品列表
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(ModelMap model) {

    model.addAttribute("a", 111);
    return "product/list";
  }


}
