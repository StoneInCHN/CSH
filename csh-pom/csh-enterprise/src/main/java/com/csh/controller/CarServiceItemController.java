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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.CarService;
import com.csh.entity.CarServiceItem;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.CarServiceItemService;
import com.csh.service.CarServiceService;
import com.csh.utils.DateTimeUtils;

/**
 * 服务项配置
 * @author huyong
 *
 */
@Controller ("carServiceItemController")
@RequestMapping ("console/carServiceItem")
public class CarServiceItemController extends BaseController
{

  @Resource (name = "carServiceItemServiceImpl")
  private CarServiceItemService carServiceItemService;
  @Resource (name = "carServiceServiceImpl")
  private CarServiceService carServiceService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping (value = "/carServiceItem", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "/carServiceItem/carServiceItem";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<CarServiceItem> list (Model model, Pageable pageable,
      Date beginDate, Date endDate, Long carServiceId,
      String serviceItemNameSearch)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser serviceItemParser = new QueryParser (Version.LUCENE_35,
        "serviceItemName", analyzer);
    TermRangeQuery rangeQuery = null;
    Query serviceItemQuery = null;
    TermQuery carServiceTermQuery = null;
    Filter filter = null;
    
    if (beginDate != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
    }
    if (endDate != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
    }
    if (serviceItemNameSearch != null)
    {
      String text = QueryParser.escape (serviceItemNameSearch);
      try
      {
        //通配符查询，开启*开头，但影响效率
        serviceItemParser.setAllowLeadingWildcard (true);

        serviceItemQuery = serviceItemParser.parse ("*" + text + "*");

        query.add (serviceItemQuery, Occur.MUST);

        if (LogUtil.isDebugEnabled (VehicleController.class))
        {
          LogUtil.debug (VehicleController.class, "search",
              "Search service item name: " + serviceItemNameSearch);
        }
      }
      catch (ParseException e)
      {
        e.printStackTrace ();
      }
    }

    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("createDate", startDateStr,
          endDateStr, true, true);
      query.add (rangeQuery, Occur.MUST);

      if (LogUtil.isDebugEnabled (VehicleController.class))
      {
        LogUtil.debug (CarServiceItemController.class, "search", "Search start date: "
            + startDateStr + " end date: " + endDateStr);
      }
    }
    if (carServiceId != null)
    {
      carServiceTermQuery = new TermQuery (new Term ("carService.id",
          carServiceId.toString ()));
      query.add (carServiceTermQuery, Occur.MUST);
    }
    
    if (carServiceTermQuery != null || serviceItemQuery!= null || rangeQuery != null)
    {
      return carServiceItemService.search (query, pageable, analyzer, filter,true);
    }
    else
    {
      return carServiceItemService.findPage (pageable,true);
    }

  }

  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id)
  {
    CarServiceItem carServiceItem = carServiceItemService.find (id);
    model.put ("carServiceItem", carServiceItem);
    return "carServiceItem/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.GET)
  public String add (ModelMap model)
  {
    return "carServiceItem/add";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (Long carServiceId,CarServiceItem carServiceItem)
  {
    CarService carService = carServiceService.find (carServiceId);
    carServiceItem.setCarService (carService);
    carServiceItemService.save (carServiceItem,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (CarServiceItem carServiceItem)
  {
    carServiceItemService.update (carServiceItem, "createDate", "tenantID","imgPath");
    return SUCCESS_MESSAGE;
  }

  /**
   * 删除
   */
  @RequestMapping (value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete (Long[] ids)
  {
    if (ids != null)
    {
      // 检查是否能被删除
      // if()
      try
      {
        carServiceItemService.delete (ids);
      }
      catch (Exception e)
      {
        e.printStackTrace ();
        return Message.error ("csh.delete.fail");
      }
    }
    return SUCCESS_MESSAGE;
  }

  /**
   * 获取数据进入详情页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping (value = "/details", method = RequestMethod.GET)
  public String details (ModelMap model, Long id)
  {
    CarServiceItem carServiceItem = carServiceItemService.find (id);
    model.addAttribute ("carServiceItem", carServiceItem);
    return "carServiceItem/details";
  }
}