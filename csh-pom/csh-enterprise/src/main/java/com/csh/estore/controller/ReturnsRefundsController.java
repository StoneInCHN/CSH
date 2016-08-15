package com.csh.estore.controller;

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
import com.csh.entity.commonenum.CommonEnum.Method;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.entity.commonenum.CommonEnum.RefundsStatus;
import com.csh.entity.commonenum.CommonEnum.ReturnsStatus;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.OrderLog;
import com.csh.entity.estore.Refunds;
import com.csh.entity.estore.Returns;
import com.csh.entity.estore.Shipping;
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
import com.csh.utils.SpringUtils;

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
   * 退货单列表
   */
  @RequestMapping (value = "/listReturns", method = RequestMethod.POST)
  public @ResponseBody Page<Returns> listOrder (Pageable pageable, String returnsSnSearch,
      ReturnsStatus statusSearch, ModelMap model) {
    if (returnsSnSearch == null && statusSearch == null) {
      return returnsService.findPage(pageable, true);
    }
    
    BooleanQuery booleanQuery = new BooleanQuery();
    if (statusSearch != null) {
      TermQuery statusQuery = new TermQuery(new Term ("returnsStatus", statusSearch.toString ()));
      booleanQuery.add (statusQuery, Occur.MUST);
    }
    if (returnsSnSearch != null) {
        IKAnalyzer analyzer = new IKAnalyzer ();
        analyzer.setMaxWordLength (true);
        QueryParser snParser = new QueryParser (Version.LUCENE_35, "sn",analyzer);
        snParser.setAllowLeadingWildcard (true);
        String sn = QueryParser.escape (returnsSnSearch);
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
    return returnsService.search (booleanQuery, pageable, null, null,true);
  }  
  
  
  
  /**
   * 页面
   */
  @RequestMapping (value = "/returnsRefunds", method = RequestMethod.GET)
  public String config(ModelMap model) {
    return "estore/returnsRefunds/returnsRefunds";
  }
  
  /**
   * 批准退货页面
   */
  @RequestMapping (value = "/approvedReturn", method = RequestMethod.GET)
  public String addReturns(ModelMap model, Long returnsId) {
    Returns returns = returnsService.find(returnsId);
    model.put ("returns", returns);
//    List<DeliveryCorp> deliveryCorps = deliveryCorpService.findAll(true);
//    model.put ("deliveryCorps", deliveryCorps);
//    List<ShippingMethod> shippingMethods = shippingMethodService.findAll(true);
//    model.put ("shippingMethods", shippingMethods);
    return "estore/returnsRefunds/approvedReturn";
  }
  
  /**
   * 批准退货
   */
  @RequestMapping (value = "/updateApprovedReturn", method = RequestMethod.POST)
  public @ResponseBody Message updateApprovedReturn(Long returnsId) {
    //获取当前租户tenant ID
    Long tenantID = tenantAccountService.getCurrentTenantID();
    Returns returns = returnsService.find(returnsId);
    if (returns.getReturnsStatus() != ReturnsStatus.applyReturn) {
      return ERROR_MESSAGE;
    }
    //获取订单
    Order order = returns.getOrder();
    if (order == null) {
        return ERROR_MESSAGE;
    }
    //设置退货单状态为 “已批准退货”
    returns.setReturnsStatus(ReturnsStatus.approvedReturn);
    //操作人员
    returns.setOperator(tenantAccountService.getCurrentUsername());
    returns.setTenantID(tenantID);
    //批准退货单，修改订单状态等（事务）
    returnsService.approvedReturns(order, returns);
        
    return SUCCESS_MESSAGE;
  } 
  /**
   * 确认退货页面
   */
  @RequestMapping (value = "/confirmReturn", method = RequestMethod.GET)
  public String confirmReturn(ModelMap model, Long returnsId) {
    Returns returns = returnsService.find(returnsId);
    model.put ("returns", returns);
    return "estore/returnsRefunds/confirmReturn";
  }  
  /**
   * 确认收到退货并批准退款
   */
  @RequestMapping (value = "/confirmApprovedRefunds", method = RequestMethod.POST)
  public @ResponseBody Message confirmApprovedRefunds(Long returnsId) {
    //获取当前租户tenant ID
    Long tenantID = tenantAccountService.getCurrentTenantID();
    Returns returns = returnsService.find(returnsId);
    if (returns.getReturnsStatus() != ReturnsStatus.approvedReturn) {
      return ERROR_MESSAGE;
    }
    //获取订单
    Order order = returns.getOrder();
    if (order == null) {
        return ERROR_MESSAGE;
    }
    //设置退货单状态为 “退货成功”
    returns.setReturnsStatus(ReturnsStatus.return_success);
    //操作人员
    returns.setOperator(tenantAccountService.getCurrentUsername());
    returns.setTenantID(tenantID);
    //创建初始退款单
    Refunds refunds = new Refunds();
    refunds.setAmount(returns.getReturnAmount());
    if (order.getPaymentType() == PaymentType.ALIPAY || order.getPaymentType() == PaymentType.WECHAT) {
      refunds.setMethod(Method.online);
    }else {
      refunds.setMethod(Method.deposit);
    }
    refunds.setReturnsID(returnsId);
    refunds.setRefundsStatus(RefundsStatus.noRefund);
    refunds.setOperator(tenantAccountService.getCurrentUsername());
    refunds.setOrder(order);
    refunds.setPayee(order.getEndUser().getUserName());
    refunds.setPaymentMethod(SpringUtils.getMessage("csh.commonEnum.PaymentType."+order.getPaymentType().toString()));
    refunds.setSn(snService.generate(Type.refunds));
    refunds.setTenantID(tenantID);
    //确认退货成功，批准退款，修改订单状态等（事务）
    returnsService.confirmApprovedRefunds(order, returns, refunds);
        
    return SUCCESS_MESSAGE;
  } 
  
  /**
   * 查看订单页面
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
