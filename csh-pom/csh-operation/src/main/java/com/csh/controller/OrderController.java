package com.csh.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csh.controller.base.BaseController;
import com.csh.entity.Sn.Type;
import com.csh.entity.commonenum.CommonEnum.Method;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.Refunds;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Pageable;
import com.csh.service.AdminService;
import com.csh.service.OrderService;
import com.csh.service.RefundsService;
import com.csh.service.SnService;
import com.csh.service.TenantInfoService;

@RequestMapping("console/order")
@Controller("orderController")
public class OrderController extends BaseController{

  @Resource(name="orderServiceImpl")
  private OrderService orderService;
  
  @Resource(name="tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;
  
  @Resource(name="adminServiceImpl")
  private AdminService adminService;
  
  @Resource(name = "snServiceImpl")
  private SnService snService;
  
  @Resource (name = "refundsServiceImpl")
  private RefundsService refundsService;
  
  
  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model,String sn,
      Date beginDate, Date endDate) {
    
    List<Filter> filters = pageable.getFilters();
    if (sn != null) {
      filters.add(new Filter("sn", Operator.eq, sn));
    }
    if (beginDate !=null) {
      filters.add(new Filter("createDate",Operator.ge,beginDate));
    }
    if (endDate !=null) {
      filters.add(new Filter("createDate",Operator.le,endDate));
    }
    model.addAttribute("beginDate", beginDate);
    model.addAttribute("endDate", endDate);
    model.addAttribute("page", orderService.findPage(pageable));
    return "/order/list";
  }
  /**
   * 订单详情
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(Long id, ModelMap model) {
    Order order = orderService.find(id);
    model.addAttribute("order", order);
    model.addAttribute("tenantName", tenantInfoService.find(order.getTenantID()).getTenantName());
    return "/order/details";
  }
  /**
   * 查看退款单
   */
  @RequestMapping(value = "/viewRefunds", method = RequestMethod.GET)
  public String viewRefunds(Long orderId, ModelMap model) {
    Order order = orderService.find(orderId);
    Set<Refunds> refunds = order.getRefunds();
    model.put ("refunds", refunds);
    return "/order/viewRefunds";
  }  
  /**
   * 添加退款
   */
  @RequestMapping (value = "/addRefunds", method = RequestMethod.GET)
  public String addRefunds(ModelMap model, Long orderId) {
    Order order = orderService.find(orderId);
    model.put ("order", order);
    model.addAttribute("refundsMethods", Method.values());
    model.addAttribute("tenantName", tenantInfoService.find(order.getTenantID()).getTenantName());
    model.addAttribute("paymentType", order.getPaymentType());
    return "order/addRefunds";
  }
  /**
   * 保存退款单
   */
  @RequestMapping (value = "/saveRefunds", method = RequestMethod.POST)
  public String saveRefunds(Long orderId, Long areaId, Refunds refunds) {
    //获取订单
    Order order = orderService.find(orderId);
    if (order == null || order.getPaymentType() == null) {
      return "redirect:list.jhtml";
    }
    refunds.setOrder(order);
    refunds.setSn(snService.generate(Type.refunds));
    refunds.setOperator(adminService.getCurrentUsername());
    refunds.setTenantID(order.getTenantID());
    //保存退款单退款项，修改订单状态等（事务）
    refundsService.saveRefunds(order, refunds);
        
    return "redirect:list.jhtml";
  } 
  
    
}
