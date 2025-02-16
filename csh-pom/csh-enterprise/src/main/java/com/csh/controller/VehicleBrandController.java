package com.csh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
import com.csh.entity.ItemPart;
import com.csh.entity.VehicleBrand;
import com.csh.entity.VehicleBrandDetail;
import com.csh.entity.VehicleLine;
import com.csh.entity.commonenum.CommonEnum.TreeNodeState;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.ordering.Ordering;
import com.csh.framework.ordering.Ordering.Direction;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.response.TreeNodeResponse;
import com.csh.service.ItemPartService;
import com.csh.service.VehicleBrandService;
import com.csh.service.VehicleLineService;
import com.csh.utils.DateTimeUtils;


/**
 * 车型管理
 * @author huyong
 *
 */
@Controller ("vehicleBrandController")
@RequestMapping ("console/vehicleBrand")
public class VehicleBrandController extends BaseController
{

  @Resource (name = "vehicleBrandServiceImpl")
  private VehicleBrandService vehicleBrandService;
  @Resource (name = "vehicleLineServiceImpl")
  private VehicleLineService vehicleLineService;
  @Resource (name = "itemPartServiceImpl")
  private ItemPartService itemPartService;
  @RequestMapping (value = "/vehicleBrand", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "vehicleBrand/vehicleBrand";
  }
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<VehicleBrand> list (Pageable pageable, ModelMap model,
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
          
          if (LogUtil.isDebugEnabled (VehicleBrandController.class))
          {
            LogUtil.debug (VehicleBrandController.class, "search", "Search name: "
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
      
      if (LogUtil.isDebugEnabled (VehicleBrandController.class))
      {
        LogUtil.debug (VehicleBrandController.class, "search", "Search start date: "+startDateStr
            +" end date: "+endDateStr);
      }
    }
    if (nameqQuery != null || rangeQuery != null || codeQuery != null)
    {
      return vehicleBrandService.search (query, pageable, analyzer,filter);
    }
      return vehicleBrandService.findPage (pageable);
    
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
    VehicleBrand vehicleBrand = vehicleBrandService.find (id);
    model.put ("vehicleBrand", vehicleBrand);
    return "vehicleBrand/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (VehicleBrand vehicleBrand)
  {
    vehicleBrandService.save (vehicleBrand,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (VehicleBrand vehicleBrand)
  { 
    vehicleBrandService.update (vehicleBrand,"createDate");
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
        vehicleBrandService.delete (ids);
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
    VehicleBrand vehicleBrand = vehicleBrandService.find(id);
    model.addAttribute("vehicleBrand", vehicleBrand);
    return "vehicleBrand/details";
  }
  @RequestMapping(value = "/findAllVehicleBrand", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAllVehicleBrand() {
    return vehicleBrandService.findAllVehicleBrand ();
  }
  
  /**
   * 车品牌树列表
   * 
   * @param pageable
   * @param id
   * @return
   */
  @RequestMapping(value = "/listVehicleBrand", method = RequestMethod.POST)
  public @ResponseBody List<TreeNodeResponse> listVehicleBrand (Pageable pageable,
      Long vehicleLineId,Long vehicleLineParentId, Long vehicleBrandId,Integer size,Integer selectCount,Long itemPartId)
  {
    return vehicleBrandService.listVehicleBrandWithDetail(vehicleLineId,vehicleLineParentId,vehicleBrandId,
        size,selectCount,itemPartId);
  }
  /**
   * 车品牌树列表
   * 
   * @param pageable
   * @param id
   * @return
   */
  @RequestMapping(value = "/listAllVehicleBrand", method = RequestMethod.POST)
  public @ResponseBody List<TreeNodeResponse> listAllVehicleBrand (Pageable pageable,
      Long vehicleLineId,Long vehicleLineParentId, Long vehicleBrandId,Integer size,Integer selectCount,Long itemPartId)
  {
//    List<TreeNodeResponse> vehicleBrandResponse = new ArrayList<TreeNodeResponse> ();
//    List<TreeNodeResponse> vehicleLineParentResponse = new ArrayList<TreeNodeResponse> ();
//    List<TreeNodeResponse> vehicleLineChildResponse = new ArrayList<TreeNodeResponse> ();
//    List<TreeNodeResponse> vehicleBrandDetailResponse = new ArrayList<TreeNodeResponse> ();
    
    List<TreeNodeResponse> vehicleBrandList= vehicleBrandService.listVehicleBrandWithDetail(null,null,null,
        null,null,itemPartId);
    
    for (TreeNodeResponse treeNode : vehicleBrandList)
    {
      List<TreeNodeResponse> vehicleLineParentList =vehicleBrandService.listVehicleBrandWithDetail(null,null,treeNode.getId (),
          null,null,itemPartId);
      for (TreeNodeResponse treeNodeParent : vehicleLineParentList)
      {
        List<TreeNodeResponse> vehicleLineChildList =vehicleBrandService.listVehicleBrandWithDetail(null,treeNodeParent.getId (),null,
            null,null,itemPartId);
        for (TreeNodeResponse treeNodeChild : vehicleLineChildList)
        {
          List<TreeNodeResponse> vehicleBrandDetailList =vehicleBrandService.listVehicleBrandWithDetail(treeNodeChild.getId (),null,null,
              null,null,itemPartId);
          treeNodeChild.setChildren (vehicleBrandDetailList);
        }
        treeNodeParent.setChildren (vehicleLineChildList);
      }
      treeNode.setChildren (vehicleLineParentList); 
    }
    return vehicleBrandList;
  }
}
