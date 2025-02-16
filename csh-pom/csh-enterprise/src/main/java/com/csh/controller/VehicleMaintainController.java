package com.csh.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
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
import com.csh.entity.VehicleMaintain;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.VehicleMaintainService;
import com.csh.service.VehicleService;
import com.csh.utils.DateTimeUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 车辆保养管理
 * @author huyong
 *
 */
@Controller ("vehicleMaintainController")
@RequestMapping ("console/vehicleMaintain")
public class VehicleMaintainController extends BaseController
{

  @Resource (name = "vehicleMaintainServiceImpl")
  private VehicleMaintainService vehicleMaintainService;
  @Resource (name = "vehicleServiceImpl")
  private VehicleService vehicleService;
  
  @RequestMapping (value = "/vehicleMaintain", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "vehicleMaintain/vehicleMaintain";
  }
  @RequestMapping (value = "/add", method = RequestMethod.GET)
  public String add (ModelMap model)
  {
    return "vehicleMaintain/add";
  }
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<VehicleMaintain> list (Pageable pageable, ModelMap model,
      Date nextMaintainDateStart, Date nextMaintainDateEnd, String plateSearch,String userNameSearch)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser namepParser = new QueryParser (Version.LUCENE_35, "vehicle.endUser.userName",
        analyzer);
    Query nameqQuery = null;
    
    QueryParser plateParser = new QueryParser (Version.LUCENE_35, "vehicle.plate",
        analyzer);
    Query plateQuery = null;
    
    TermRangeQuery rangeQuery = null;
    
    Filter filter = null;
    if (nextMaintainDateStart != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (nextMaintainDateStart, null);
    }
    if (nextMaintainDateEnd != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (nextMaintainDateEnd, null);
    }
    if (userNameSearch != null)
    {
      String text = QueryParser.escape (userNameSearch);
        try
        {
          //通配符查询，开启*开头，但影响效率
          namepParser.setAllowLeadingWildcard (true);

          nameqQuery = namepParser.parse ("*"+text+"*");
          
          query.add (nameqQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (VehicleMaintainController.class))
          {
            LogUtil.debug (VehicleMaintainController.class, "search", "Search user: "
                + userNameSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
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
          
          if (LogUtil.isDebugEnabled (VehicleMaintainController.class))
          {
            LogUtil.debug (VehicleMaintainController.class, "search", "Search plate: "
                + plateSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
    }
    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("nextMaintainDate", startDateStr, endDateStr, true, true);
      query.add (rangeQuery,Occur.MUST);
      
      if (LogUtil.isDebugEnabled (VehicleMaintainController.class))
      {
        LogUtil.debug (VehicleMaintainController.class, "search", "Search start date: "+startDateStr
            +" end date: "+endDateStr);
      }
    }
    
    if (nameqQuery != null || rangeQuery != null || plateQuery != null)
    {
      return vehicleMaintainService.search (query, pageable, analyzer,filter,true);
    }
      return vehicleMaintainService.findPage (pageable,true);
    
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
    VehicleMaintain vehicleMaintain = vehicleMaintainService.find (id);
    
    List<Map<String, Object>> vehicleListMap = vehicleService.findVehicleUnderUser(vehicleMaintain.getVehicle ().getEndUser ().getId ());
    
    ObjectMapper objectMapper = new ObjectMapper();
    String result = null;
    try
    {
      result= objectMapper.writeValueAsString (vehicleListMap);
    }
    catch (JsonProcessingException e)
    {
      e.printStackTrace();
    }
    model.put ("vehicleMaintain", vehicleMaintain);
    model.put ("vehicleListMap", result);
    return "vehicleMaintain/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (VehicleMaintain vehicleMaintain,Long vehicleId)
  {
    Vehicle vehicle= vehicleService.find (vehicleId);
    vehicleMaintain.setVehicle (vehicle);
    vehicleMaintainService.save (vehicleMaintain,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (VehicleMaintain vehicleMaintain,Long vehicleId)
  { 
    Vehicle vehicle= vehicleService.find (vehicleId);
    vehicleMaintain.setVehicle (vehicle);
    vehicleMaintainService.update (vehicleMaintain,"createDate","tenantID");
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
        vehicleMaintainService.delete (ids);
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
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {
    VehicleMaintain vehicleMaintain = vehicleMaintainService.find(id);
    model.addAttribute("vehicleMaintain", vehicleMaintain);
    return "vehicleMaintain/details";
  }
}
