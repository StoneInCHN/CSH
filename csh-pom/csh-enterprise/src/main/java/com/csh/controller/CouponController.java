package com.csh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.CarService;
import com.csh.entity.Coupon;
import com.csh.entity.ServiceCategory;
import com.csh.entity.TenantInfo;
import com.csh.entity.commonenum.CommonEnum.CouponOverDueType;
import com.csh.entity.commonenum.CommonEnum.CouponSendType;
import com.csh.entity.commonenum.CommonEnum.CouponType;
import com.csh.entity.commonenum.CommonEnum.ImageType;
import com.csh.entity.commonenum.CommonEnum.TreeNodeState;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.response.TreeNodeCarServiceResponse;
import com.csh.service.CarServiceService;
import com.csh.service.CouponService;
import com.csh.service.FileService;
import com.csh.service.ServiceCategoryService;
import com.csh.service.TenantAccountService;
import com.csh.utils.DateTimeUtils;

/**
 * 服务配置
 * @author huyong
 *
 */
@Controller ("couponController")
@RequestMapping ("console/coupon")
public class CouponController extends BaseController
{

  @Resource (name = "couponServiceImpl")
  private CouponService couponService;
  @Resource (name = "fileServiceImpl")
  private FileService fileService;
  @Resource (name = "serviceCategoryServiceImpl")
  private ServiceCategoryService serviceCategoryService;
  @Resource (name = "carServiceServiceImpl")
  private CarServiceService carServiceService;
  @Resource (name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping (value = "/coupon", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "/coupon/coupon";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Coupon> list (Model model, Pageable pageable,
      Date beginDate, Date endDate, CouponType couponTypeSearch,
      CouponSendType couponSendTypeSearch, CouponOverDueType couponOverDueTypeSearch)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();
  
    TermRangeQuery rangeQuery = null;
    TermQuery typeTermQuery = null;
    TermQuery sendTypeTermQuery = null;
    TermQuery overDueTypeQuery = null;
    Filter filter = null;
    
    TenantInfo tenantInfo = tenantAccountService.getCurrentTenantInfo ();
    if (beginDate != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
    }
    if (endDate != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
    }
   

    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("createDate", startDateStr,
          endDateStr, true, true);
      query.add (rangeQuery, Occur.MUST);

      if (LogUtil.isDebugEnabled (VehicleController.class))
      {
        LogUtil.debug (VehicleController.class, "search", "Search start date: "
            + startDateStr + " end date: " + endDateStr);
      }
    }
    if (couponTypeSearch != null)
    {
      typeTermQuery = new TermQuery (new Term ("type",
          couponTypeSearch.toString ()));
      query.add (typeTermQuery, Occur.MUST);
    }
    if (couponSendTypeSearch != null )
    {
      sendTypeTermQuery = new TermQuery (new Term ("sendType",
          couponSendTypeSearch.toString ()));
      query.add (sendTypeTermQuery, Occur.MUST);
    }
    
    if (couponOverDueTypeSearch != null)
    {
      overDueTypeQuery = new TermQuery (new Term ("overDueType",
          tenantInfo.getId ().toString ()));
      query.add (overDueTypeQuery, Occur.MUST);
      
    }
    if (typeTermQuery != null || rangeQuery != null || sendTypeTermQuery != null || overDueTypeQuery != null)
    {
      return couponService.search (query, pageable, analyzer, filter);
    }else{
      return couponService.findPage (pageable);
    }

  }

  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id)
  {
    Coupon coupon = couponService.find (id);
    model.put ("coupon", coupon);
    Set<CarService> carServices = coupon.getCarServices ();
    List<Long> carServiceIds = new ArrayList<Long> ();
    for (CarService carService : carServices)
    {
      carServiceIds.add (carService.getId ());
    }
    model.put ("carServiceIds", carServiceIds);
    return "coupon/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.GET)
  public String add (ModelMap model)
  {
    return "coupon/add";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (Coupon coupon,
      Long[] carServiceIds)
  {
    Set<CarService> carServiceSet  = new HashSet <CarService> ();
    for (Long carServiceId : carServiceIds)
    {
     CarService carService =  carServiceService.find (carServiceId);
     carServiceSet.add (carService);
    }
    coupon.setCarServices (carServiceSet);
    couponService.save (coupon);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (Coupon coupon, Long[] carServiceIds)
  {
    Set<CarService> carServiceSet  = new HashSet <CarService> ();
    for (Long carServiceId : carServiceIds)
    {
     CarService carService =  carServiceService.find (carServiceId);
     carServiceSet.add (carService);
    }
    coupon.setCarServices (carServiceSet);
    
    couponService.update (coupon, "createDate", "tenantID");
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
      couponService.delete (ids);
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
    Coupon coupon = couponService.find (id);
    model.put ("coupon", coupon);
    Set<CarService> carServices = coupon.getCarServices ();
    List<Long> carServiceIds = new ArrayList<Long> ();
    for (CarService carService : carServices)
    {
      carServiceIds.add (carService.getId ());
    }
    model.put ("carServiceIds", carServiceIds);
    return "coupon/details";
  }

  @RequestMapping (value = "/uploadPhoto", method = RequestMethod.POST)
  public @ResponseBody Message uploadPhoto (
      @RequestParam ("file") MultipartFile file, Long couponId)
  {
    //    String filePath = fileService.upload(FileType.PROFILE_PICTURE, file, identifier);
    String filePath = fileService.saveImage (file, ImageType.CARSERVICEPICTURE);
    if (filePath != null && couponId != null)
    {
      Coupon coupon = couponService.find (couponId);
      couponService.update (coupon);
      return Message.success (filePath);
    }
    else
    {
      return ERROR_MESSAGE;
    }
  }
  
  @RequestMapping (value = "/prepareTreeDataGrid", method = RequestMethod.POST)
  public @ResponseBody List<TreeNodeCarServiceResponse> prepareTreeDataGrid ( Pageable pageable,
       String serviceCategorySearch,String serviceNameSearch)
  {
    List<TreeNodeCarServiceResponse> treeNodeResponses = new ArrayList<TreeNodeCarServiceResponse>();
    
    List<ServiceCategory> categoryList = serviceCategoryService.findAll ();
    
    for (ServiceCategory serviceCategory : categoryList)
    {
      TreeNodeCarServiceResponse response = new TreeNodeCarServiceResponse ();
     
      response.setId (serviceCategory.getId ());
      response.setName (serviceCategory.getCategoryName ());
      response.setIconCls("icon-large-chart");
      response.setState (TreeNodeState.closed);
      Set<CarService> carServiceList = serviceCategory.getAutoServices ();
      
      List<TreeNodeCarServiceResponse> childList = new ArrayList<TreeNodeCarServiceResponse> ();
      for (CarService carService : carServiceList)
      {
        TreeNodeCarServiceResponse child = new TreeNodeCarServiceResponse();
        child.setIconCls("icon-large-chart");
        child.setState (TreeNodeState.closed);
        child.setId (carService.getId ());
        child.setName (carService.getServiceName ());
        child.setPrice (carService.getPrice ());
        child.setPromotionPrice (carService.getPromotionPrice ());
        childList.add (child);
      }
      response.setChildren (childList);
      treeNodeResponses.add (response);
    }
    
    return treeNodeResponses;
  }
}
