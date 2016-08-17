package com.csh.estore.controller;

import java.util.ArrayList;
import java.util.List;

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






import com.csh.entity.Sn.Type;
import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.commonenum.CommonEnum.OrderStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentStatus;
import com.csh.entity.commonenum.CommonEnum.ShippingStatus;
import com.csh.entity.estore.DeliveryCorp;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.OrderItem;
import com.csh.entity.estore.Shipping;
import com.csh.entity.estore.ShippingItem;
import com.csh.entity.estore.ShippingMethod;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.ordering.Ordering;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.DeliveryCorpService;
import com.csh.service.OrderService;
import com.csh.service.ShippingMethodService;
import com.csh.service.ShippingService;
import com.csh.service.SnService;
import com.csh.service.TenantAccountService;

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
  @Resource (name = "deliveryCorpServiceImpl")
  private DeliveryCorpService deliveryCorpService;
  @Resource (name = "shippingMethodServiceImpl")
  private ShippingMethodService shippingMethodService;
  @Resource(name = "tenantAccountServiceImpl")
  protected TenantAccountService tenantAccountService;
  @Resource(name = "snServiceImpl")
  private SnService snService;
  
  
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
  public @ResponseBody Page<Shipping> shippingList (Pageable pageable, String shippingSnSearch, ModelMap model) {
    if (shippingSnSearch != null) {
      BooleanQuery booleanQuery = new BooleanQuery();  
      IKAnalyzer analyzer = new IKAnalyzer ();
      analyzer.setMaxWordLength (true);
        QueryParser snParser = new QueryParser (Version.LUCENE_35, "sn",analyzer);
        snParser.setAllowLeadingWildcard (true);
        String sn = QueryParser.escape (shippingSnSearch);
        Query snQuery;
        try {
          snQuery = snParser.parse ("*"+sn+"*");
          booleanQuery.add (snQuery, Occur.MUST);
          if (LogUtil.isDebugEnabled (getClass())){
            LogUtil.debug (getClass(), "shippingList", "Search Shipping sn: %s", snQuery);
          }
        } catch (ParseException e) {
          e.printStackTrace();
        }
        return shippingService.search (booleanQuery, pageable, null, null,true);
    }else {
        return shippingService.findPage(pageable, true);
    }
    
  }
  /**
   * 待发货的订单列表
   */
  @RequestMapping (value = "/listUnshippedOrder", method = RequestMethod.POST)
  public @ResponseBody Page<Order> listUnshippedOrder (Pageable pageable, String orderSnSearch, ModelMap model) {
/*不用lucene查询，因为代码中更新Order的发货状态shippingStatus字段老是不能生成索引，暂时改成用传统的Filter过滤
//    //订单状态=已确认,支付状态=已支付,配送状态=未发货 的所有订单(是否时间倒序?)
//    BooleanQuery booleanQuery = new BooleanQuery();
//    TermQuery orderStatusQuery = new TermQuery 
//        (new Term ("orderStatus",OrderStatus.confirmed.toString ()));
//    booleanQuery.add (orderStatusQuery, Occur.MUST);
//    TermQuery paymentStatusQuery = new TermQuery 
//        (new Term ("paymentStatus",PaymentStatus.paid.toString ()));
//    booleanQuery.add (paymentStatusQuery, Occur.MUST);
//    TermQuery shippingStatusQuery = new TermQuery 
//        (new Term ("shippingStatus",ShippingStatus.unshipped.toString ()));
//    booleanQuery.add (shippingStatusQuery, Occur.MUST);
//    
//    IKAnalyzer analyzer = new IKAnalyzer ();
//    analyzer.setMaxWordLength (true);
//    if (orderSnSearch != null) {
//        QueryParser snParser = new QueryParser (Version.LUCENE_35, "sn",analyzer);
//        snParser.setAllowLeadingWildcard (true);
//        String sn = QueryParser.escape (orderSnSearch);
//        Query snQuery;
//        try {
//          snQuery = snParser.parse ("*"+sn+"*");
//          booleanQuery.add (snQuery, Occur.MUST);
//          if (LogUtil.isDebugEnabled (getClass())){
//            LogUtil.debug (getClass(), "listUnshippedOrder", "Search Order sn: %s", snQuery);
//          }
//        } catch (ParseException e) {
//          e.printStackTrace();
//        }
//    }
//    return orderService.search (booleanQuery, pageable, null, null,true);
*/
    List<Filter> filters = pageable.getFilters();
    //订单状态=已确认,支付状态=已支付,配送状态=未发货 的所有订单(按照订单创建时间倒序)
    filters.add(Filter.eq("orderStatus", OrderStatus.confirmed));
    filters.add(Filter.eq("paymentStatus", PaymentStatus.paid));
    filters.add(Filter.eq("shippingStatus", ShippingStatus.unshipped));
    List<Ordering> ordering = pageable.getOrderings();
    ordering.add(Ordering.desc("createDate"));
    if (orderSnSearch != null) {//如果查询条件 “订单号”不为空
      filters.add(Filter.like("sn", "%"+orderSnSearch+"%"));
    }
    return orderService.findPage(pageable, true);
    
  }  
    
  
  /**
   * 保存发货单
   */
  @RequestMapping (value = "/saveShipping", method = RequestMethod.POST)
  public @ResponseBody Message saveShipping(Long orderId, Long shippingMethodId, Long deliveryCorpId, Long areaId,Shipping shipping) {
    //获取当前租户tenant ID
    Long tenantID = tenantAccountService.getCurrentTenantID();
    //获取订单
    Order order = orderService.find(orderId);
    if (order == null) {
        return ERROR_MESSAGE;
    }
    shipping.setOrder(order);
    //配送方式
    ShippingMethod shippingMethod = shippingMethodService.find(shippingMethodId);
    shipping.setShippingMethod(shippingMethod != null ? shippingMethod.getName() : null);
    //物流公司
    DeliveryCorp deliveryCorp = deliveryCorpService.find(deliveryCorpId);
    shipping.setDeliveryCorp(deliveryCorp != null ? deliveryCorp.getName() : null);
    shipping.setDeliveryCorpUrl(deliveryCorp != null ? deliveryCorp.getUrl() : null);
    shipping.setDeliveryCorpCode(deliveryCorp != null ? deliveryCorp.getCode() : null);
    //生成发货单号
    shipping.setSn(snService.generate(Type.shipping));
    //操作人员
    shipping.setOperator(tenantAccountService.getCurrentUsername());
    
    //添加发货项，现在只做全部发货
    List<ShippingItem> shippingItems = shipping.getShippingItems();
    List<OrderItem> orderItems = order.getOrderItems();
    for (int i = 0; i < orderItems.size(); i++) {
      OrderItem orderItem = orderItems.get(i);
      ShippingItem shippingItem = new ShippingItem();
      shippingItem.setName(orderItem.getFullName());
      shippingItem.setShipping(shipping);
      shippingItem.setQuantity(orderItem.getQuantity());
      shippingItem.setSn(orderItem.getSn());
      shippingItem.setTenantID(tenantID);
      shippingItems.add(shippingItem);
    }
    shipping.setTenantID(tenantID);
    //保存发货单发货项，修改订单状态和产品库存等（事务）
    shippingService.saveShipping(order, shipping);
        
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
   * 添加发货单
   */
  @RequestMapping (value = "/addShipping", method = RequestMethod.GET)
  public String addShipping(ModelMap model, Long orderId) {
    Order order = orderService.find(orderId);
    model.put ("order", order);
    List<DeliveryCorp> deliveryCorps = deliveryCorpService.findAll(true);
    model.put ("deliveryCorps", deliveryCorps);
    List<ShippingMethod> shippingMethods = shippingMethodService.findAll(true);
    model.put ("shippingMethods", shippingMethods);
    return "estore/shipping/addShipping";
  }  

}
