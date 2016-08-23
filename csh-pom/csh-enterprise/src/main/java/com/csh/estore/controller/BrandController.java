package com.csh.estore.controller;

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
import com.csh.controller.DeviceInfoController;
import com.csh.controller.VehicleController;
import com.csh.controller.base.BaseController;
import com.csh.entity.commonenum.CommonEnum.ImageType;
import com.csh.entity.estore.Brand;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.BrandService;
import com.csh.service.FileService;
import com.csh.utils.DateTimeUtils;
import com.csh.utils.FieldFilterUtils;

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
    return "estore/brand/brand";
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
     String brandNameSearch, Date beginDate, Date endDate)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();
  
    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "name",
        analyzer);
    TermRangeQuery rangeQuery = null;
    Query nameQuery = null;
    Filter filter = null;
    
    if (brandNameSearch != null)
    {
      String text = QueryParser.escape (brandNameSearch);
        try
        {
          nameParser.setAllowLeadingWildcard (true);
          nameQuery = nameParser.parse ("*"+text+"*");
          query.add (nameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (DeviceInfoController.class))
          {
            LogUtil.debug (DeviceInfoController.class, "search", "Search brand name: "
                + brandNameSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
    }
    
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
   
    if (nameQuery != null || rangeQuery != null)
    {
      return brandService.search (query, pageable, analyzer, filter);
    }else{
      return brandService.findPage (pageable);
    }

  }

  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id)
  {
    Brand brand = brandService.find (id);
    model.put ("brand", brand);
    return "estore/brand/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.GET)
  public String add (ModelMap model)
  {
    return "estore/brand/add";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (Brand brand)
  {
    brandService.save (brand,true);
    
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (Brand brand)
  {
    brandService.update (brand,"tenantID","createDate");
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
    return "estore/brand/details";
  }
  
  @RequestMapping (value = "/uploadLogo", method = RequestMethod.POST)
  public @ResponseBody Message uploadPhoto (
      @RequestParam ("file") MultipartFile file, Long brandId)
  {
    String filePath = fileService.saveImage (file, ImageType.PRODUCTBRANDIMAGE);
    if (filePath != null && brandId != null)
    {
      Brand brand = brandService.find (brandId);
      brand.setLogo (filePath);
      brandService.update (brand);
      return Message.success (filePath);
    }
    else
    {
      return ERROR_MESSAGE;
    }
  }
  
  @RequestMapping (value = "/findAll", method = RequestMethod.GET)
  public @ResponseBody List<Map<String, Object>> findAll (ModelMap model)
  {
    List<Brand> brandlist = brandService.findAll ();
    String[] propertys = {"id", "name"};
    return FieldFilterUtils.filterCollectionMap(propertys, brandlist);
  }
}
