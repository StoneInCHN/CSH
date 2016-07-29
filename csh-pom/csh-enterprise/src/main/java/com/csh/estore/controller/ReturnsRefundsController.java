package com.csh.estore.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.Sn.Type;
import com.csh.entity.commonenum.CommonEnum.OrderStatus;
import com.csh.entity.estore.DeliveryCorp;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.OrderItem;
import com.csh.entity.estore.OrderLog;
import com.csh.entity.estore.Refunds;
import com.csh.entity.estore.Returns;
import com.csh.entity.estore.ReturnsItem;
import com.csh.entity.estore.Shipping;
import com.csh.entity.estore.ShippingMethod;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.DeliveryCorpService;
import com.csh.service.OrderService;
import com.csh.service.RefundsService;
import com.csh.service.ReturnsItemService;
import com.csh.service.ReturnsService;
import com.csh.service.ShippingMethodService;
import com.csh.service.SnService;
import com.csh.service.TenantAccountService;

/**
 * 退货退款
 *
 */
@Controller
@RequestMapping ("console/returnsRefunds")
public class ReturnsRefundsController extends BaseController {

  
  @Resource (name = "returnsServiceImpl")
  private ReturnsService returnsService;
  @Resource (name = "returnsItemServiceImpl")
  private ReturnsItemService returnsItemService;
  @Resource (name = "refundsServiceImpl")
  private RefundsService refundsService;
  @Resource (name = "orderServiceImpl")
  private OrderService orderService;
  @Resource (name = "deliveryCorpServiceImpl")
  private DeliveryCorpService deliveryCorpService;
  @Resource (name = "shippingMethodServiceImpl")
  private ShippingMethodService shippingMethodService;
  @Resource(name = "tenantAccountServiceImpl")
  protected TenantAccountService tenantAccountService;
  @Resource(name = "snServiceImpl")
  private SnService snService;
//  @Resource(name = "paymentMethodServiceImpl")
//  private PaymentMethodService paymentMethodService;  
  

  
  /**
   * 可退货可退款的订单列表
   */
  @RequestMapping (value = "/listOrder", method = RequestMethod.POST)
  public @ResponseBody Page<Order> listOrder (Pageable pageable, String orderSnSearch, ModelMap model) {
    //订单状态=已确认,支付状态=已支付
    BooleanQuery booleanQuery = new BooleanQuery();
    TermQuery orderStatusQuery = new TermQuery 
        (new Term ("orderStatus",OrderStatus.confirmed.toString ()));
    booleanQuery.add (orderStatusQuery, Occur.MUST);
//    TermQuery paymentStatusQuery = new TermQuery 
//        (new Term ("paymentStatus",PaymentStatus.paid.toString ()));
//    booleanQuery.add (paymentStatusQuery, Occur.MUST);
    
    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    if (orderSnSearch != null) {
        QueryParser snParser = new QueryParser (Version.LUCENE_35, "sn",analyzer);
        snParser.setAllowLeadingWildcard (true);
        String sn = QueryParser.escape (orderSnSearch);
        Query snQuery;
        try {
          snQuery = snParser.parse ("*"+sn+"*");
          booleanQuery.add (snQuery, Occur.MUST);
          if (LogUtil.isDebugEnabled (getClass())){
            LogUtil.debug (getClass(), "listOrder", "Search Order sn: %s", snQuery);
          }
        } catch (ParseException e) {
          e.printStackTrace();
        }
    }
    return orderService.search (booleanQuery, pageable, null, null,true);
  }  
  
  
  
  /**
   * 页面
   */
  @RequestMapping (value = "/returnsRefunds", method = RequestMethod.GET)
  public String config(ModelMap model) {
    return "estore/returnsRefunds/returnsRefunds";
  }
  
  /**
   * 添加退货
   */
  @RequestMapping (value = "/addReturns", method = RequestMethod.GET)
  public String addReturns(ModelMap model, Long orderId) {
    Order order = orderService.find(orderId);
    model.put ("order", order);
    List<DeliveryCorp> deliveryCorps = deliveryCorpService.findAll(true);
    model.put ("deliveryCorps", deliveryCorps);
    List<ShippingMethod> shippingMethods = shippingMethodService.findAll(true);
    model.put ("shippingMethods", shippingMethods);
    return "estore/returnsRefunds/addReturns";
  }
  
  /**
   * 保存退货单
   */
  @RequestMapping (value = "/saveReturns", method = RequestMethod.POST)
  public @ResponseBody Message saveReturns(Long orderId, Long shippingMethodId, Long deliveryCorpId, Long areaId,Returns returns) {
    //获取当前租户tenant ID
    Long tenantID = tenantAccountService.getCurrentTenantID();
    //获取订单
    Order order = orderService.find(orderId);
    if (order == null) {
        return ERROR_MESSAGE;
    }
    returns.setOrder(order);
    //配送方式
    ShippingMethod shippingMethod = shippingMethodService.find(shippingMethodId);
    returns.setShippingMethod(shippingMethod != null ? shippingMethod.getName() : null);
    //物流公司
    DeliveryCorp deliveryCorp = deliveryCorpService.find(deliveryCorpId);
    returns.setDeliveryCorp(deliveryCorp != null ? deliveryCorp.getName() : null);
    //生成退货单号
    returns.setSn(snService.generate(Type.returns));
    //操作人员
    returns.setOperator(tenantAccountService.getCurrentUsername());
    //地区
    returns.setArea("");
    
    //添加退货项
    List<ReturnsItem> returnsItems = returns.getReturnsItems();
    List<OrderItem> orderItems = order.getOrderItems();
    for (int i = 0; i < orderItems.size(); i++) {
      OrderItem orderItem = orderItems.get(i);
      ReturnsItem returnsItem = new ReturnsItem();
      returnsItem.setName(orderItem.getFullName());
      returnsItem.setReturns(returns);
      returnsItem.setQuantity(orderItem.getQuantity());
      returnsItem.setSn(orderItem.getSn());
      returnsItem.setTenantID(tenantID);
      returnsItems.add(returnsItem);
    }
    returns.setTenantID(tenantID);
    //保存退货单退货项，修改订单状态等（事务）
    returnsService.saveReturns(order, returns);
        
    return SUCCESS_MESSAGE;
  } 
  
  /**
   * 添加退款
   */
  @RequestMapping (value = "/addRefunds", method = RequestMethod.GET)
  public String addRefunds(ModelMap model, Long orderId) {
    Order order = orderService.find(orderId);
    model.put ("order", order);
    model.addAttribute("refundsMethods", Refunds.Method.values());
    //model.addAttribute("paymentMethods", paymentMethodService.findAll());
    return "estore/returnsRefunds/addRefunds";
  }
  /**
   * 保存退款单
   */
  @RequestMapping (value = "/saveRefunds", method = RequestMethod.POST)
  public @ResponseBody Message saveRefunds(Long orderId, Long paymentMethodId, Long areaId,Refunds refunds) {
    //获取当前租户tenant ID
    Long tenantID = tenantAccountService.getCurrentTenantID();
    //获取订单
    Order order = orderService.find(orderId);
    if (order == null) {
        return ERROR_MESSAGE;
    }
    refunds.setOrder(order);
    if (paymentMethodId != null) {//支付方式
      //PaymentMethod paymentMethod = paymentMethodService.find(paymentMethodId);
      //refunds.setPaymentMethod(paymentMethod != null ? paymentMethod.getName() : null);
    }
    
    refunds.setSn(snService.generate(Type.refunds));
    refunds.setOperator(tenantAccountService.getCurrentUsername());
    refunds.setTenantID(tenantID);
    //保存退款单退款项，修改订单状态等（事务）
    refundsService.saveRefunds(order, refunds);
        
    return SUCCESS_MESSAGE;
  } 
  
  /**
   * 查看 订单
   */
  @RequestMapping (value = "/viewOrder", method = RequestMethod.GET)
  public String viewOrder(ModelMap model, Long orderId) {
    Order order = orderService.find(orderId);
    if (order == null) {
        return "";
    }
    model.put ("order", order);
    Set<Shipping> shippings = order.getShippings();
    Set<Returns> returns = order.getReturns();
    Set<Refunds> refunds = order.getRefunds();
    Set<OrderLog> orderLogs =  order.getOrderLogs();
    model.put ("shippings", shippings);
    model.put ("returns", returns);
    model.put ("refunds", refunds);
    model.put ("orderLogs", orderLogs);
    return "estore/returnsRefunds/viewOrder";
  }
  /**
   * 更新
   */
  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message updateReturns(Returns returns) {   
    returnsService.update(returns,"shippingMethods");
    return SUCCESS_MESSAGE;
  } 
  /**
   * 删除
   */
  @RequestMapping (value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message deleteReturns (Long[] ids) {
    if (ids != null) {
      returnsService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

}
