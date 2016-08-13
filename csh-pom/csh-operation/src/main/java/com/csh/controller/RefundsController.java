package com.csh.controller;

import java.util.Date;
import java.util.HashSet;
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
import com.csh.entity.commonenum.CommonEnum.RefundsStatus;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.Refunds;
import com.csh.entity.estore.Returns;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Pageable;
import com.csh.service.AdminService;
import com.csh.service.OrderService;
import com.csh.service.RefundsService;
import com.csh.service.ReturnsService;
import com.csh.service.SnService;
import com.csh.service.TenantInfoService;

@RequestMapping("console/refunds")
@Controller("refundsController")
public class RefundsController extends BaseController{

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
  
  @Resource (name = "returnsServiceImpl")
  private ReturnsService returnsService;  
  
  /**
   * 退款单列表
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
    model.addAttribute("page", refundsService.findPage(pageable));
    return "/refunds/list";
  } 
  /**
   * 退款单详情
   */
  @RequestMapping (value = "/refunds", method = RequestMethod.GET)
  public String refunds(ModelMap model, Long refundsId, String path) {
    //获取退款单
    Refunds refunds = refundsService.find(refundsId);
    model.put ("refunds", refunds);
    //获取退货单
    Returns returns = returnsService.find(refunds.getReturnsID());
    model.put ("returns", returns);
    model.put ("returnsItems", returns.getReturnsItems());
    
    model.addAttribute("tenantName", tenantInfoService.find(refunds.getTenantID()).getTenantName());
    return "refunds/" + path;
  }
  /**
   * 更新退款单
   */
  @RequestMapping (value = "/updateRefunds", method = RequestMethod.POST)
  public String updateRefunds(Refunds refunds) {
    Refunds refundsDB = refundsService.find(refunds.getId());
    //refundsDB.setAmount(refunds.getAmount());
    //获取订单
    Order order = refundsDB.getOrder();
    if (order == null || order.getPaymentType() == null) {
      return "redirect:list.jhtml";
    }
//    refunds.setOrder(order);
//    refunds.setSn(snService.generate(Type.refunds));
//    refunds.setOperator(adminService.getCurrentUsername());
//    refunds.setTenantID(order.getTenantID());
    //更新退款单，修改订单状态等（事务）
    refundsService.saveRefunds(order, refundsDB);
        
    return "redirect:list.jhtml";
  } 
  
    
}
