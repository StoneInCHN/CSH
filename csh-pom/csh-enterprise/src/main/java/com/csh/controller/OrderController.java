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

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.estore.Order;
import com.csh.framework.filter.Filter;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.OrderService;

@RequestMapping("console/estore/order")
@Controller("orderController")
public class OrderController extends BaseController{

	@Resource(name="orderServiceImpl")
	private OrderService orderService;

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
		  List<Filter> filters = new ArrayList<Filter>();
		  filters.add(Filter.isNull("parent"));
		  pageable.setFilters(filters);
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
	
}
