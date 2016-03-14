package com.csh.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.VehicleBrandDetail;
import com.csh.entity.commonenum.CommonEnum.Status;
import com.csh.framework.paging.Pageable;
import com.csh.service.VehicleBrandDetailService;

/**
 * 车型管理
 * @author huyong
 *
 */
@Controller ("vehicleBrandController")
@RequestMapping ("console/vehicleBrand")
public class VehicleBrandDetailController extends BaseController
{

  @Resource (name = "vehicleBrandServiceImpl")
  private VehicleBrandDetailService vehicleBrandService;
  
  @RequestMapping (value = "/vehicleBrand", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "vehicleBrand/vehicleBrand";
  }
//  @RequestMapping (value = "/list", method = RequestMethod.POST)
//  public @ResponseBody Page<VehicleBrand> list (Pageable pageable, ModelMap model,
//      Date beginDate, Date endDate, String nameSearch,Status statusSearch)
//  {
//    String startDateStr = null;
//    String endDateStr = null;
//
//    IKAnalyzer analyzer = new IKAnalyzer ();
//    analyzer.setMaxWordLength (true);
//    BooleanQuery query = new BooleanQuery ();
//
//    QueryParser namepParser = new QueryParser (Version.LUCENE_35, "name",
//        analyzer);
//    Query nameqQuery = null;
//    TermRangeQuery rangeQuery = null;
//    TermQuery statusQuery = null;
//    
//    Filter filter = null;
//    if (beginDate != null)
//    {
//      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
//    }
//    if (endDate != null)
//    {
//      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
//    }
//    if (nameSearch != null)
//    {
//      String text = QueryParser.escape (nameSearch);
//        try
//        {
//          //通配符查询，开启*开头，但影响效率
//          namepParser.setAllowLeadingWildcard (true);
//
//          nameqQuery = namepParser.parse (text);
//          
//          query.add (nameqQuery, Occur.MUST);
//          
//          if (LogUtil.isDebugEnabled (VehicleBrandController.class))
//          {
//            LogUtil.debug (VehicleBrandController.class, "search", "Search name: "
//                + nameSearch );
//          }
//        }
//        catch (ParseException e)
//        {
//          e.printStackTrace();
//        }
//    }
//    if (statusSearch != null)
//    {
//      statusQuery = new TermQuery (new Term ("status",statusSearch.toString ()));
//      query.add (statusQuery,Occur.MUST);
//    }
//    if (startDateStr != null || endDateStr != null)
//    {
//      rangeQuery = new TermRangeQuery ("createDate", startDateStr, endDateStr, true, true);
//      query.add (rangeQuery,Occur.MUST);
//      
//      if (LogUtil.isDebugEnabled (VehicleBrandController.class))
//      {
//        LogUtil.debug (VehicleBrandController.class, "search", "Search start date: "+startDateStr
//            +" end date: "+endDateStr);
//      }
//    }
//    if (nameqQuery != null || rangeQuery != null || statusQuery != null)
//    {
//      return vehicleBrandService.search (query, pageable, analyzer,filter,true);
//    }
//      return vehicleBrandService.findPage (pageable, true);
//    
//  }
  @RequestMapping (value = "/list", method = RequestMethod.GET)
  public @ResponseBody List<VehicleBrandDetail> list(Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String nameSearch,Status statusSearch)
  {
    return vehicleBrandService.findRoots (null);
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
    VehicleBrandDetail vehicleBrand = vehicleBrandService.find (id);
    model.put ("vehicleBrand", vehicleBrand);
    return "vehicleBrand/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (VehicleBrandDetail vehicleBrand)
  {
    vehicleBrandService.save (vehicleBrand,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (VehicleBrandDetail vehicleBrand)
  { 
    vehicleBrandService.update (vehicleBrand);
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
      vehicleBrandService.delete (ids);
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
    VehicleBrandDetail vehicleBrand = vehicleBrandService.find(id);
    model.addAttribute("vehicleBrand", vehicleBrand);
    return "vehicleBrand/details";
  }
}
