package com.csh.estore.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.Shipping;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.OrderService;
import com.csh.service.ShippingService;

/**
 * 发货单
 *
 */
@Controller
@RequestMapping ("console/shipping")
public class ShippingController extends BaseController {

  
  @Resource (name = "shippingServiceImpl")
  private ShippingService shippingService;
  @Resource (name = "orderServiceImpl")
  private OrderService orderService;
  
  
  /**
   * 发货单页面
   */
  @RequestMapping (value = "/shipping", method = RequestMethod.GET)
  public String config(ModelMap model) {
    return "estore/shipping/shipping";
  }
  
  
  /**
   * 发货单列表
   */
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Shipping> shippingList (Pageable pageable, ModelMap model) {
      return shippingService.findPage(pageable);
  }
  /**
   * 发货单列表
   */
  @RequestMapping (value = "/listUnshippedOrder", method = RequestMethod.POST)
  public @ResponseBody Page<Order> listUnshippedOrder (Pageable pageable, ModelMap model) {
      return orderService.findPage(pageable);
  }  
  
  /**
   * 添加发货单
   */
  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message addShipping(Shipping shipping) {
    shippingService.save(shipping,true);
    return SUCCESS_MESSAGE;
  } 
  
  /**
   * 编辑/查看 发货单
   */
  @RequestMapping (value = "/details", method = RequestMethod.GET)
  public String shippingDetails(ModelMap model, Long id, String path) {
    Shipping shipping = shippingService.find (id);
    model.put ("shipping", shipping);
    return "estore/shipping/" + path;
  }
  /**
   * 更新发货单
   */
  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message updateShipping(Shipping shipping) {   
    shippingService.update(shipping,"shippingMethods");
    return SUCCESS_MESSAGE;
  } 
  /**
   * 删除发货单
   */
  @RequestMapping (value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message deleteShipping (Long[] ids) {
    if (ids != null) {
      shippingService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

}
