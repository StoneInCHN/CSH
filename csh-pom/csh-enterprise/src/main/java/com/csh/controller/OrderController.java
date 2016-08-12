package com.csh.controller;

import java.util.Date;

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
import com.csh.entity.estore.Order;
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
        if (LogUtil.isDebugEnabled(TenantAccountController.class)) {
          LogUtil.debug(TenantAccountController.class, "search", "Search sn: " + sn);
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
      if (LogUtil.isDebugEnabled(TenantAccountController.class)) {
        LogUtil.debug(TenantAccountController.class, "search", "Search start date: " + startDateStr
            + " end date: " + endDateStr);
      }
    }
    if (snQuery != null || rangeQuery != null || statusQuery != null) {
      return orderService.search(query, pageable, analyzer, filter, true);
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
