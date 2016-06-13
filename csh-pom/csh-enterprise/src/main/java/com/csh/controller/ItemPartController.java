package com.csh.controller;

import java.util.Date;
import java.util.HashSet;
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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.CarServiceItem;
import com.csh.entity.ItemPart;
import com.csh.entity.VehicleBrand;
import com.csh.entity.VehicleBrandDetail;
import com.csh.entity.VehicleLine;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.CarServiceItemService;
import com.csh.service.ItemPartService;
import com.csh.service.VehicleBrandDetailService;
import com.csh.service.VehicleBrandService;
import com.csh.service.VehicleLineService;
import com.csh.utils.DateTimeUtils;

/**
 * 服务项配件配置
 * @author huyong
 *
 */
@Controller ("itemPartController")
@RequestMapping ("console/itemPart")
public class ItemPartController extends BaseController
{

  @Resource (name = "itemPartServiceImpl")
  private ItemPartService itemPartService;
  
  @Resource (name = "carServiceItemServiceImpl")
  private CarServiceItemService carServiceItemService;

  @Resource (name = "vehicleBrandServiceImpl")
  private VehicleBrandService vehicleBrandService;
  
  @Resource (name = "vehicleLineServiceImpl")
  private VehicleLineService vehicleLineService;
  
  @Resource (name = "vehicleBrandDetailServiceImpl")
  private VehicleBrandDetailService vehicleBrandDetailService;
  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping (value = "/itemPart", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "/itemPart/itemPart";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<ItemPart> list (Model model, Pageable pageable,
      Date beginDate, Date endDate, Long carServiceItemId,
      String serviceItemPartNameSearch)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser serviceItemParser = new QueryParser (Version.LUCENE_35,
        "serviceItemPartName", analyzer);
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
    if (serviceItemPartNameSearch != null)
    {
      String text = QueryParser.escape (serviceItemPartNameSearch);
      try
      {
        //通配符查询，开启*开头，但影响效率
        serviceItemParser.setAllowLeadingWildcard (true);

        serviceItemQuery = serviceItemParser.parse ("*" + text + "*");

        query.add (serviceItemQuery, Occur.MUST);

        if (LogUtil.isDebugEnabled (VehicleController.class))
        {
          LogUtil.debug (VehicleController.class, "search",
              "Search service item part name: " + serviceItemPartNameSearch);
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
        LogUtil.debug (ItemPartController.class, "search", "Search start date: "
            + startDateStr + " end date: " + endDateStr);
      }
    }
    if (carServiceItemId != null)
    {
      carServiceTermQuery = new TermQuery (new Term ("carServiceItem.id",
          carServiceItemId.toString ()));
      query.add (carServiceTermQuery, Occur.MUST);
    }
    
    if (carServiceTermQuery != null || serviceItemQuery!= null || rangeQuery != null)
    {
      return itemPartService.search (query, pageable, analyzer, filter,true);
    }
    else
    {
      return itemPartService.findPage (pageable,true);
    }

  }

  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id)
  {
    ItemPart itemPart = itemPartService.find (id);
    model.put ("itemPart", itemPart);
    return "itemPart/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.GET)
  public String add (ModelMap model)
  {
    return "itemPart/add";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (Long carServiceItemId,ItemPart itemPart,Boolean selectAll
      ,Long[] vehicleBrandIds,Long[] vehicleLineIds,Long[] vehicleBrandDetailIds)
  {
    if (selectAll== null || !selectAll)
    {
      List<VehicleBrand> vehicleBrands = vehicleBrandService.findList (vehicleBrandIds);
      List<VehicleLine> vehicleLines = vehicleLineService.findList (vehicleLineIds);
      List<VehicleBrandDetail> vehicleBrandDetails = vehicleBrandDetailService.findList (vehicleBrandDetailIds);
      
      itemPart.setVehicleBrands (new HashSet<VehicleBrand> (vehicleBrands));
      itemPart.setVehicleLines (new HashSet<VehicleLine> (vehicleLines));
      itemPart.setVehicleBrandDetails (new HashSet<VehicleBrandDetail> (vehicleBrandDetails));
    }else {
      List<VehicleBrand> vehicleBrands =vehicleBrandService.findAll ();
      itemPart.setVehicleBrands (new HashSet<VehicleBrand> (vehicleBrands));
    }
    CarServiceItem carServiceItem = carServiceItemService.find (carServiceItemId);
    itemPart.setCarServiceItem (carServiceItem);
    
    itemPartService.saveItemPart (itemPart);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (ItemPart itemPart,Boolean selectAll
      ,Long[] deleteBrandItemIds,Long[] selectBrandItemIds,Long[] deleteLineParentItemIds,
      Long[] selectLineParentItemIds,Long[] selectLineChildItemIds,Long[] deleteLineChildItemIds,
      Long[] deleteBrandDetailItemIds,Long[] selectBrandDetailItemIds)
  {
    itemPartService.updateItemParts (itemPart, selectAll
        , deleteBrandItemIds, selectBrandItemIds, deleteLineParentItemIds,
        selectLineParentItemIds, selectLineChildItemIds,deleteLineChildItemIds,
        deleteBrandDetailItemIds,selectBrandDetailItemIds);
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
        itemPartService.delete (ids);
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
    ItemPart itemPart = itemPartService.find (id);
    model.addAttribute ("itemPart", itemPart);
    return "itemPart/details";
  }
}