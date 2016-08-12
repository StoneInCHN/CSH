package com.csh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.TenantAccount;
import com.csh.entity.commonenum.CommonEnum.OrderStatus;
import com.csh.entity.estore.Order;
import com.csh.framework.filter.Filter;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.OrderService;
import com.csh.service.TenantAccountService;

@RequestMapping("console/estore/order")
@Controller("orderController")
public class OrderController extends BaseController{

	@Resource(name="orderServiceImpl")
	private OrderService orderService;

	@Resource (name = "tenantAccountServiceImpl")
	 private TenantAccountService tenantAccountService;
	
	  /**
	   * 界面展示
	   * 
	   * @param model
	   * @return
	   */
	  @RequestMapping(value = "/order", method = RequestMethod.GET)
	  public String list(ModelMap model) {
	    return "/estore/order/order";
	  }

	  @RequestMapping(value = "/edit", method = RequestMethod.GET)
	  public String edit(ModelMap model, Long id) {
	    return "/estore/order/edit";
	  }

	/*  @RequestMapping(value = "/details", method = RequestMethod.GET)
	  public @ResponseBody ModelMap details(ModelMap model, Long id) {
		Order parentOrder = orderService.find(id);
		model.addAttribute("parentOrder", parentOrder);
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(Filter.eq("parent", id));
		List<Order> subOrders = orderService.findList(null, filters, null);
		if (subOrders.size() >0) {
			model.addAttribute("hasSubOrders", true);
		}else{
			subOrders.add(parentOrder);
			model.addAttribute("hasSubOrders", false);
		}
		
		
		model.addAttribute("subOrders", subOrders);
	    return model;
	  }*/
  
	  @RequestMapping(value = "/details", method = RequestMethod.GET)
	  public String details(ModelMap model, Long id) {
		Order order = orderService.find(id);
		model.addAttribute("order", order);
	/*	List<Filter> filters = new ArrayList<Filter>();
		filters.add(Filter.eq("parent", id));
		List<Order> subOrders = orderService.findList(null, filters, null);
		if (subOrders.size() >0) {
			model.addAttribute("hasSubOrders", true);
		}else{
			subOrders.add(parentOrder);
			model.addAttribute("hasSubOrders", false);
		}
		model.addAttribute("subOrders", subOrders);
		*/
		
		
	    return "/estore/order/details";
	  }
	  
	  /**
	   * 列表
	   * 
	   * @param model
	   * @param pageable
	   * @return
	   */
	  @RequestMapping (value = "/list", method = RequestMethod.POST)
	  public @ResponseBody Page<Order> list (Model model, Pageable pageable){
	    return orderService.findPage (pageable,true);
	  }
	  

	  /**
	   * 删除
	   */
	  @RequestMapping(value = "/delete", method = RequestMethod.POST)
	  public @ResponseBody Message delete(Long[] ids) {
	    if (ids != null) {
	      // 检查是否能被删除
	      // if()
	      try {
	    	  orderService.delete(ids);
	      } catch (Exception e) {
	        e.printStackTrace();
	        return Message.error("csh.delete.fail");
	      }
	    }
	    return SUCCESS_MESSAGE;
	  }
	
	  
	  /**
	     * 确认
	     */
	    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
	    public String confirm(Long id, RedirectAttributes redirectAttributes) {
	      TenantAccount tenantAccount = tenantAccountService.getCurrent();
	            Order order = orderService.find(id);
	            if (order != null && !order.isExpired() && order.getOrderStatus() == OrderStatus.unconfirmed && !order.isLocked(tenantAccount)) {
	                orderService.confirm(order, tenantAccount);
	               // addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
	            } else {
	               // addFlashMessage(redirectAttributes, Message.warn("admin.common.invalid"));
	            }
	        
	        return "redirect:view.jhtml?id=" + id;
	    }

	  
	  
}
