package com.csh.estore.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.DeviceInfoController;
import com.csh.controller.VehicleController;
import com.csh.controller.base.BaseController;
import com.csh.entity.estore.Brand;
import com.csh.entity.estore.ProductCategory;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.BrandService;
import com.csh.service.ProductCategoryService;
import com.csh.utils.DateTimeUtils;

/**
 * 商品分类
 * @author huyong
 *
 */
@Controller ("productCategoryController")
@RequestMapping ("console/productCategory")
public class ProductCategoryController extends BaseController
{

  @Resource (name = "productCategoryServiceImpl")
  private ProductCategoryService productCategoryService;
  @Resource(name = "brandServiceImpl")
  private BrandService brandService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping (value = "/productCategory", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "estore/productCategory/productCategory";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<ProductCategory> list (Model model, Pageable pageable,
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
      return productCategoryService.search (query, pageable, analyzer, filter,true);
    }else{
      return productCategoryService.findPage (pageable,true);
    }

  }

  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id)
  {
    ProductCategory productCategory = productCategoryService.find (id);
    model.put ("productCategory", productCategory);
    if (productCategory.getParent () != null)
    {
      model.put ("parentId", productCategory.getParent ().getId ());
    }
    model.put ("brands",brandService.findAll (true));
    return "estore/productCategory/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.GET)
  public String add (ModelMap model)
  {
    model.put ("brands",brandService.findAll (true));
    return "estore/productCategory/add";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (ProductCategory productCategory,Long parentId,Long[] brandIds)
  {
    if (parentId != null)
    {
      ProductCategory parent=productCategoryService.find (parentId);
      productCategory.setParent (parent);
    }
    if (brandIds != null && brandIds.length>0)
    {
      List<Brand> brands = brandService.findList (brandIds);
      productCategory.setBrands (new HashSet<Brand> (brands));
    }
    productCategoryService.save (productCategory,true);
    
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (ProductCategory productCategory,Long parentId,Long[] brandIds)
  {
    if (parentId != null)
    {
      ProductCategory parent=productCategoryService.find (parentId);
      productCategory.setParent (parent);
    }
    if (brandIds != null && brandIds.length>0)
    {
      List<Brand> brands = brandService.findList (brandIds);
      productCategory.setBrands (new HashSet<Brand> (brands));
    }
    productCategoryService.update (productCategory,"tenantID","createDate","treePath", "grade", "children", "products", "parameterGroups", "attributes");
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
        productCategoryService.delete (ids);
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
    ProductCategory productCategory = productCategoryService.find (id);
    if (productCategory.getParent () != null)
    {
      model.put ("parentId", productCategory.getParent ().getId ());
    }
    model.put ("brands",brandService.findAll (true));
    model.put ("productCategory", productCategory);
    return "estore/productCategory/details";
  }
  
  @RequestMapping (value = "/findAll", method = RequestMethod.GET)
  public @ResponseBody List<ProductCategory> findAll (ModelMap model, Long id)
  {
    List<ProductCategory> productCategoryList = productCategoryService.findRoots (true);
    return productCategoryList;
  }
 
}
