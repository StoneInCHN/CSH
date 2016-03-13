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
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.Vehicle;
import com.csh.entity.commonenum.CommonEnum.DeviceStatus;
import com.csh.entity.commonenum.CommonEnum.Status;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.VehicleService;
import com.csh.utils.DateTimeUtils;

/**
 * 车辆管理
 * @author huyong
 *
 */
@Controller ("vehicleController")
@RequestMapping ("console/vehicle")
public class VehicleController extends BaseController
{

  @Resource (name = "vehicleServiceImpl")
  private VehicleService vehicleService;
  
  @RequestMapping (value = "/vehicle", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "vehicle/vehicle";
  }
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Vehicle> list (Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String plateSearch,String vehicleBrandSearch,Status vehicleStatusSearch)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser plateParser = new QueryParser (Version.LUCENE_35, "plate",
        analyzer);
    QueryParser brandParser = new QueryParser (Version.LUCENE_35, "vehicleBrand.name",
        analyzer);
    Query plateQuery = null;
    Query brandQuery = null;
    TermRangeQuery rangeQuery = null;
    TermQuery statusQuery = null;
    
    Filter filter = null;
    if (beginDate != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
    }
    if (endDate != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
    }
    if (plateSearch != null)
    {
      String text = QueryParser.escape (plateSearch);
        try
        {
          //通配符查询，开启*开头，但影响效率
          plateParser.setAllowLeadingWildcard (true);

          plateQuery = plateParser.parse ("*"+text+"*");
          
          query.add (plateQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (VehicleController.class))
          {
            LogUtil.debug (VehicleController.class, "search", "Search plate: "
                + plateSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
    }
    if (vehicleBrandSearch != null)
    {
      String text = QueryParser.escape (vehicleBrandSearch);
        try
        {
          brandQuery = brandParser.parse (text);
          query.add (brandQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (VehicleController.class))
          {
            LogUtil.debug (VehicleController.class, "search", "Search vehicle brand: "
                + vehicleBrandSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
    }
    if (vehicleStatusSearch != null)
    {
      statusQuery = new TermQuery (new Term ("status",vehicleStatusSearch.toString ()));
      query.add (statusQuery,Occur.MUST);
    }
    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("createDate", startDateStr, endDateStr, true, true);
      query.add (rangeQuery,Occur.MUST);
      
      if (LogUtil.isDebugEnabled (VehicleController.class))
      {
        LogUtil.debug (VehicleController.class, "search", "Search start date: "+startDateStr
            +" end date: "+endDateStr);
      }
    }
    if (plateQuery != null || brandQuery != null || rangeQuery != null || statusQuery != null)
    {
      return vehicleService.search (query, pageable, analyzer,filter,true);
    }
      return vehicleService.findPage (pageable, true);
    
  }

  /**
   * get data for vendor edit page
   * 
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id)
  {
    Vehicle vehicle = vehicleService.find (id);
    model.put ("vehicle", vehicle);
    return "vehicle/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (Vehicle vehicle)
  {
    vehicleService.save (vehicle,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (Vehicle vehicle)
  { 
    vehicleService.update (vehicle);
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
      vehicleService.delete (ids);
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
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {
    Vehicle vehicle = vehicleService.find(id);
    model.addAttribute("vehicle", vehicle);
    return "vehicle/details";
  }
}
