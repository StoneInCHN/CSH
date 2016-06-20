package com.csh.estore.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.beans.Message;
import com.csh.beans.Setting;
import com.csh.common.log.LogUtil;
import com.csh.controller.VehicleController;
import com.csh.controller.base.BaseController;
import com.csh.entity.CarService;
import com.csh.entity.Coupon;
import com.csh.entity.MessageInfo;
import com.csh.entity.TenantInfo;
import com.csh.entity.commonenum.CommonEnum.CouponOverDueType;
import com.csh.entity.commonenum.CommonEnum.CouponSendType;
import com.csh.entity.commonenum.CommonEnum.CouponType;
import com.csh.entity.commonenum.CommonEnum.SystemType;
import com.csh.entity.estore.Brand;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.BrandService;
import com.csh.service.CarServiceService;
import com.csh.service.CouponService;
import com.csh.service.FileService;
import com.csh.service.ServiceCategoryService;
import com.csh.service.TenantAccountService;
import com.csh.utils.ApiUtils;
import com.csh.utils.DateTimeUtils;
import com.csh.utils.SettingUtils;

/**
 * 商品品牌
 * @author huyong
 *
 */
@Controller ("brandController")
@RequestMapping ("console/brand")
public class BrandController extends BaseController
{

  @Resource (name = "brandServiceImpl")
  private BrandService brandService;
  @Resource (name = "fileServiceImpl")
  private FileService fileService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping (value = "/brand", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "/brand/brand";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Brand> list (Model model, Pageable pageable,
      Date beginDate, Date endDate)
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
   
    if (typeTermQuery != null || rangeQuery != null || sendTypeTermQuery != null || overDueTypeQuery != null)
    {
      return brandService.search (query, pageable, analyzer, filter,true);
    }else{
      return brandService.findPage (pageable,true);
    }

  }

  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id)
  {
    Brand brand = brandService.find (id);
    model.put ("brand", brand);
    return "brand/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.GET)
  public String add (ModelMap model)
  {
    return "brand/add";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (Brand brand)
  {
    brandService.save (brand);
    
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (Brand brand)
  {
    brandService.update (brand);
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
        brandService.delete (ids);
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
    Brand brand = brandService.find (id);
    model.put ("brand", brand);
    return "brand/details";
  }
}
