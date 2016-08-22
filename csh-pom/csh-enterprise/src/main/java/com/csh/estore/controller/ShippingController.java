package com.csh.estore.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
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
import com.csh.entity.estore.DeliveryCorp;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.OrderItem;
import com.csh.entity.estore.Shipping;
import com.csh.entity.estore.ShippingItem;
import com.csh.entity.estore.ShippingMethod;
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
    model.put ("order", shipping.getOrder());
    model.put ("orderLogs", shipping.getOrder().getOrderLogs());
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
