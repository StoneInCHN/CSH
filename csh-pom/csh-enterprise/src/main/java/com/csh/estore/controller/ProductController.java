package com.csh.estore.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
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
import com.csh.entity.Sn.Type;
import com.csh.entity.commonenum.CommonEnum.ImageType;
import com.csh.entity.estore.Brand;
import com.csh.entity.estore.Parameter;
import com.csh.entity.estore.ParameterGroup;
import com.csh.entity.estore.Product;
import com.csh.entity.estore.ProductCategory;
import com.csh.entity.estore.ProductImage;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.request.PropertyListRequest;
import com.csh.json.response.PropertyGridResponse;
import com.csh.service.BrandService;
import com.csh.service.FileService;
import com.csh.service.ProductCategoryService;
import com.csh.service.ProductService;
import com.csh.service.SnService;
import com.csh.utils.DateTimeUtils;

/**
 * 商品
 * @author huyong
 *
 */
@Controller ("productController")
@RequestMapping ("console/product")
public class ProductController extends BaseController
{

  @Resource (name = "productServiceImpl")
  private ProductService productService;
  @Resource (name = "productCategoryServiceImpl")
  private ProductCategoryService productCategoryService;
  @Resource(name = "brandServiceImpl")
  private BrandService brandService;
  @Resource (name = "snServiceImpl")
  private SnService snService;
  @Resource (name = "fileServiceImpl")
  private FileService fileService;
  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping (value = "/product", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "estore/product/product";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Product> list (Model model, Pageable pageable,
     String productNameSearch, Date beginDate, Date endDate)
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
    
    if (productNameSearch != null)
    {
      String text = QueryParser.escape (productNameSearch);
        try
        {
          nameParser.setAllowLeadingWildcard (true);
          nameQuery = nameParser.parse ("*"+text+"*");
          query.add (nameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (DeviceInfoController.class))
          {
            LogUtil.debug (DeviceInfoController.class, "search", "Search brand name: "
                + productNameSearch );
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
      return productService.search (query, pageable, analyzer, filter,true);
    }else{
      return productService.findPage (pageable,true);
    }

  }

  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id)
  {
    Product product = productService.find (id);
    model.put ("product", product);
    return "estore/product/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.GET)
  public String add (ModelMap model)
  {
    
    model.put ("sn", snService.generate (Type.product));
    return "estore/product/add";
  }

  @RequestMapping (value = "/add",method = RequestMethod.POST)
  public @ResponseBody Message add (Product product,Long productCategoryId,String[] productImageSrcs,Long brandId,PropertyListRequest request)
  {
	List<ProductImage> productImageList = new ArrayList<ProductImage>();
	for (String src: productImageSrcs) {
		ProductImage image = new ProductImage();
		image.setSource(src);
		productImageList.add(image);
	}
	product.setProductImages(productImageList);
    ProductCategory category =productCategoryService.find (productCategoryId);
    product.setProductCategory (category);
    Brand brand = brandService.find (brandId);
    product.setBrand (brand);
    
    //商品参数配置
    PropertyGridResponse[] propertyGrids = request.getPropertyGridResponses ();
    for (ParameterGroup parameterGroup : product.getProductCategory().getParameterGroups()) {
      for (Parameter parameter : parameterGroup.getParameters()) {
        for (PropertyGridResponse propertyGridResponse : propertyGrids)
        {
          if (propertyGridResponse.getId () == parameter.getId ())
          {
            String parameterValue = propertyGridResponse.getValue ();
            if (StringUtils.isNotEmpty(parameterValue) && !"null".equals (parameterValue)) {
              product.getParameterValue().put(parameter, parameterValue);
            } else {
              product.getParameterValue().remove(parameter);
            }
          }
        }
      }
    }
    
    
    productService.save (product,true);
    
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (Product product,Long productCategoryId,Long brandId)
  {
	ProductCategory productCategory=  productCategoryService.find(productCategoryId);
	product.setProductCategory(productCategory);
	
    productService.update (product,"image","tenantID","createDate","treePath","isMarketable","hits", 
    		"grade", "children", "products", "parameterGroups", "attributes","allocatedStock","isGift","isList");
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
        productService.delete (ids);
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
   * 上架状态
   */
  @RequestMapping (value = "/marketable", method = RequestMethod.POST)
  public @ResponseBody Message marketable (Long[] ids,Boolean isMarketable)
  {
    if (ids != null)
    {
      List<Product > prudoctList =productService.findList (ids);
      for (Product product : prudoctList)
      {
        product.setIsMarketable (isMarketable);
      }
      productService.update (prudoctList);
    }
    return SUCCESS_MESSAGE;
  }
  @RequestMapping (value = "/getCurrentParameter", method = RequestMethod.GET)
  public @ResponseBody List<PropertyGridResponse> getCurrentParameter (Long productId)
  {
    Product product = productService.find(productId);
    
    Map<Parameter, String> pramValueMap = product.getParameterValue();
    
    List<PropertyGridResponse> responseList = new ArrayList<PropertyGridResponse>();
    for (Parameter parameter : pramValueMap.keySet())
    {
    	String value = pramValueMap.get(parameter);
        PropertyGridResponse response = new PropertyGridResponse ();
        response.setId (parameter.getId ());
        response.setGroup (parameter.getParameterGroup().getName ());
        response.setName (parameter.getName ());
        response.setValue(value);
        responseList.add (response);
    }
    return responseList;

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
    Product product = productService.find (id);
    model.put ("product", product);
    return "estore/product/details";
  }
  
  @RequestMapping (value = "/uploadImage", method = RequestMethod.POST)
  public @ResponseBody Message uploadPhoto (
      @RequestParam ("file") MultipartFile file, Long productId)
  {
    String filePath = fileService.saveImage (file, ImageType.PRODUCTIMAGE);
    if (filePath != null && productId != null)
    {
      Product product = productService.find (productId);
      product.setImage(filePath);
      productService.update (product);
      return Message.success (filePath);
    }
    else
    {
      return ERROR_MESSAGE;
    }
  }
}
