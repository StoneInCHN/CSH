package com.csh.estore.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.TenantAccount;
import com.csh.entity.commonenum.CommonEnum.OrderStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentStatus;
import com.csh.entity.commonenum.CommonEnum.ShippingStatus;
import com.csh.entity.estore.Order;
import com.csh.framework.ordering.Ordering;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.OrderService;
import com.csh.service.TenantAccountService;
import com.csh.utils.DateTimeUtils;

@RequestMapping("console/estore/order")
@Controller("orderController")
public class OrderController extends BaseController {

  @Resource(name = "orderServiceImpl")
  private OrderService orderService;

  @Resource(name = "tenantAccountServiceImpl")
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


  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id, String sn) {
    Order order = orderService.find(id);
    if(order.getSn().equals(sn)){
      model.addAttribute("order", order);
    }else{
      model.addAttribute("order", new Order());
    }
    return "/estore/order/details";
  }

  /**
   * 列表
   * 
   * @param sn 订单号
   * @param beginDate 起始时间
   * @param endDate 结束时间
   * @param orderStatus 订单状态
   * @param pageable
   * @return Page<Order>
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Order> list(String sn, Date beginDate, Date endDate,
      OrderStatus orderStatus, Pageable pageable) {
    String startDateStr = null;
    String endDateStr = null;
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    BooleanQuery query = new BooleanQuery();
    QueryParser snParser = new QueryParser(Version.LUCENE_35, "sn", analyzer);
    Query snQuery = null;
    TermRangeQuery rangeQuery = null;
    TermQuery statusQuery = null;
    Filter filter = null;
    if (beginDate != null) {
      startDateStr = DateTimeUtils.convertDateToString(beginDate, null);
    }
    if (endDate != null) {
      endDateStr = DateTimeUtils.convertDateToString(endDate, null);
    }
    if (sn != null) {
      String text = QueryParser.escape(sn);
      try {
        snParser.setAllowLeadingWildcard(true);
        snQuery = snParser.parse("*" + text + "*");
        query.add(snQuery, Occur.MUST);
        if (LogUtil.isDebugEnabled(getClass())) {
          LogUtil.debug(getClass(), "search", "Search sn: " + sn);
        }
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    if (orderStatus != null) {
      statusQuery = new TermQuery(new Term("orderStatus", orderStatus.toString()));
      query.add(statusQuery, Occur.MUST);
    }
    if (startDateStr != null || endDateStr != null) {
      rangeQuery = new TermRangeQuery("createDate", startDateStr, endDateStr, true, true);
      query.add(rangeQuery, Occur.MUST);
      if (LogUtil.isDebugEnabled(getClass())) {
        LogUtil.debug(getClass(), "search", "Search start date: " + startDateStr
            + " end date: " + endDateStr);
      }
    }
    if (snQuery != null || rangeQuery != null || statusQuery != null) {
      return orderService.search(query, pageable, analyzer, filter, true);
    }
    return orderService.findPage(pageable, true);

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
    
    List<com.csh.framework.filter.Filter> filters = pageable.getFilters();
    //订单状态=已确认,支付状态=已支付,配送状态=未发货 的所有订单(按照订单创建时间倒序)
    filters.add(com.csh.framework.filter.Filter.eq("orderStatus", OrderStatus.confirmed));
    filters.add(com.csh.framework.filter.Filter.eq("paymentStatus", PaymentStatus.paid));
    filters.add(com.csh.framework.filter.Filter.eq("shippingStatus", ShippingStatus.unshipped));
    List<Ordering> ordering = pageable.getOrderings();
    ordering.add(Ordering.desc("createDate"));
    if (orderSnSearch != null) {//如果查询条件 “订单号”不为空
      filters.add(com.csh.framework.filter.Filter.like("sn", "%"+orderSnSearch+"%"));
    }
    return orderService.findPage(pageable, true);
    
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
        // orderService.delete(ids);
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
  public @ResponseBody Message confirm(Long id, String sn, RedirectAttributes redirectAttributes) {
    TenantAccount tenantAccount = tenantAccountService.getCurrent();
    Order order = orderService.find(id);
    if (order != null && !order.isExpired() && order.getOrderStatus() == OrderStatus.unconfirmed
        && !order.isLocked(tenantAccount) && order.getSn().equals(sn)) {
      orderService.confirm(order, tenantAccount);
      return SUCCESS_MESSAGE;
    } else {
      return ERROR_MESSAGE;
    }
  }

}
