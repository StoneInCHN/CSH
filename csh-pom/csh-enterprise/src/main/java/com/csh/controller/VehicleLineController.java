package com.csh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.csh.entity.VehicleLine;
import com.csh.entity.commonenum.CommonEnum.TreeNodeState;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.response.TreeNodeResponse;
import com.csh.service.VehicleBrandService;
import com.csh.service.VehicleLineService;
import com.csh.utils.DateTimeUtils;


/**
 * 车系管理
 * @author huyong
 *
 */
@Controller ("vehicleLineController")
@RequestMapping ("console/vehicleLine")
public class VehicleLineController extends BaseController
{

  @Resource (name = "vehicleLineServiceImpl")
  private VehicleLineService vehicleLineService;
  @Resource (name = "vehicleBrandServiceImpl")
  private VehicleBrandService vehicleBrandService;
  @RequestMapping (value = "/vehicleLine", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "vehicleLine/vehicleLine";
  }
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<VehicleLine> list (Long vehicleBrandId,Pageable pageable, ModelMap model,
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
    TermQuery idQuery = null;
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
          
          if (LogUtil.isDebugEnabled (VehicleLineController.class))
          {
            LogUtil.debug (VehicleLineController.class, "search", "Search name: "
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
    if (vehicleBrandId != null)
    {
      idQuery = new TermQuery (new Term ("vehicleBrand.id",vehicleBrandId.toString ()));
      query.add (idQuery,Occur.MUST);
    }
    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("createDate", startDateStr, endDateStr, true, true);
      query.add (rangeQuery,Occur.MUST);
      
      if (LogUtil.isDebugEnabled (VehicleLineController.class))
      {
        LogUtil.debug (VehicleLineController.class, "search", "Search start date: "+startDateStr
            +" end date: "+endDateStr);
      }
    }
    if (nameqQuery != null || rangeQuery != null || codeQuery != null || idQuery != null)
    {
      return vehicleLineService.search (query, pageable, analyzer,filter);
    }
      return vehicleLineService.findPage (pageable);
    
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
    VehicleLine vehicleLine = vehicleLineService.find (id);
    model.put ("vehicleLine", vehicleLine);
    return "vehicleLine/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (VehicleLine vehicleLine)
  {
    vehicleLineService.save (vehicleLine,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (VehicleLine vehicleLine)
  { 
    vehicleLineService.update (vehicleLine,"createDate");
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
      vehicleLineService.delete (ids);
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
    VehicleLine vehicleLine = vehicleLineService.find(id);
    model.addAttribute("vehicleLine", vehicleLine);
    return "vehicleLine/details";
  }
  @RequestMapping(value = "/findVehicleLineByBrand", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findVehicleLineByBrand(Long vehicleBrandId) {
    VehicleBrand vehicleBrand = vehicleBrandService.find (vehicleBrandId);
    return vehicleLineService.findVehicleLineByBrand (vehicleBrand);
  }
}
