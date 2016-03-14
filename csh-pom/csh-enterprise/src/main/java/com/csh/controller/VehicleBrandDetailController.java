package com.csh.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.csh.entity.VehicleBrand;
import com.csh.entity.VehicleBrandDetail;
import com.csh.entity.VehicleLine;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.VehicleBrandDetailService;
import com.csh.service.VehicleLineService;
import com.csh.utils.DateTimeUtils;


/**
 * 车型信息管理
 * @author huyong
 *
 */
@Controller ("vehicleBrandDetailController")
@RequestMapping ("console/vehicleBrandDetail")
public class VehicleBrandDetailController extends BaseController
{

  @Resource (name = "vehicleBrandDetailServiceImpl")
  private VehicleBrandDetailService vehicleBrandDetailService;
  @Resource (name = "vehicleLineServiceImpl")
  private VehicleLineService vehicleLineService;
  
  @RequestMapping (value = "/vehicleBrandDetail", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "vehicleBrandDetail/vehicleBrandDetail";
  }
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<VehicleBrandDetail> list (Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String nameSearch,String codeSearch)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser namepParser = new QueryParser (Version.LUCENE_35, "name",
        analyzer);
    Query nameqQuery = null;
    TermRangeQuery rangeQuery = null;
    TermQuery codeQuery = null;
    
    Filter filter = null;
    if (beginDate != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
    }
    if (endDate != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
    }
    if (nameSearch != null)
    {
      String text = QueryParser.escape (nameSearch);
        try
        {
          //通配符查询，开启*开头，但影响效率
          namepParser.setAllowLeadingWildcard (true);

          nameqQuery = namepParser.parse (text);
          
          query.add (nameqQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (VehicleBrandDetailController.class))
          {
            LogUtil.debug (VehicleBrandDetailController.class, "search", "Search name: "
                + nameSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
    }
    if (codeSearch != null)
    {
      codeQuery = new TermQuery (new Term ("code",codeSearch));
      query.add (codeQuery,Occur.MUST);
    }
    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("createDate", startDateStr, endDateStr, true, true);
      query.add (rangeQuery,Occur.MUST);
      
      if (LogUtil.isDebugEnabled (VehicleBrandDetailController.class))
      {
        LogUtil.debug (VehicleBrandDetailController.class, "search", "Search start date: "+startDateStr
            +" end date: "+endDateStr);
      }
    }
    if (nameqQuery != null || rangeQuery != null || codeQuery != null)
    {
      return vehicleBrandDetailService.search (query, pageable, analyzer,filter);
    }
      return vehicleBrandDetailService.findPage (pageable);
    
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
    VehicleBrandDetail vehicleBrandDetail = vehicleBrandDetailService.find (id);
    model.put ("vehicleBrandDetail", vehicleBrandDetail);
    return "vehicleBrandDetail/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (VehicleBrandDetail vehicleBrandDetail)
  {
    vehicleBrandDetailService.save (vehicleBrandDetail,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (VehicleBrandDetail vehicleBrandDetail)
  { 
    vehicleBrandDetailService.update (vehicleBrandDetail,"createDate");
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
      vehicleBrandDetailService.delete (ids);
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
    VehicleBrandDetail vehicleBrandDetail = vehicleBrandDetailService.find(id);
    model.addAttribute("vehicleBrandDetail", vehicleBrandDetail);
    return "vehicleBrandDetail/details";
  }
  @RequestMapping(value = "/findVehicleBrandDetailByLine", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findVehicleBrandDetailByLine(Long vehicleLineId) {
    VehicleLine vehicleLine = vehicleLineService.find (vehicleLineId);
    return vehicleBrandDetailService.findVehicleBrandDetailByLine (vehicleLine);
  }
}
