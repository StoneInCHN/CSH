package com.csh.controller;

import java.util.Date;
import java.util.HashMap;
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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.beans.FileInfo.FileType;
import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.CarService;
import com.csh.entity.ServiceCategory;
import com.csh.entity.commonenum.CommonEnum.ReservationInfoFrom;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.CarServiceService;
import com.csh.service.FileService;
import com.csh.service.ServiceCategoryService;
import com.csh.utils.DateTimeUtils;

/**
 * 服务配置
 * @author huyong
 *
 */
@Controller ("carServiceController")
@RequestMapping ("console/carService")
public class CarServiceController extends BaseController
{

  @Resource (name = "carServiceServiceImpl")
  private CarServiceService carServiceService;
  @Resource (name = "fileServiceImpl")
  private FileService fileService;
  @Resource(name = "serviceCategoryServiceImpl")
  private ServiceCategoryService serviceCategoryService;
  
  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/carService", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/carService/carService";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<CarService> list(Model model, Pageable pageable,
      Date beginDate, Date endDate, String plateSearch,String userNameSearch,ReservationInfoFrom infoFromSearch) {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser plateParser = new QueryParser (Version.LUCENE_35, "plate",
        analyzer);
    QueryParser userNameParser = new QueryParser (Version.LUCENE_35, "endUser.userName",
        analyzer);
    Query plateQuery = null;
    Query userNameQuery = null;
    TermRangeQuery rangeQuery = null;
    TermQuery termQuery = null;
    
    Filter filter = null;
    if (beginDate != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
    }
    if (endDate != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
    }
    if (plateSearch != null)
    {
      String text = QueryParser.escape (plateSearch);
        try
        {
          //通配符查询，开启*开头，但影响效率
          plateParser.setAllowLeadingWildcard (true);

          plateQuery = plateParser.parse ("*"+text+"*");
          
          query.add (plateQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (VehicleController.class))
          {
            LogUtil.debug (VehicleController.class, "search", "Search plate: "
                + plateSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
    }
    if (userNameSearch != null)
    {
      String text = QueryParser.escape (userNameSearch);
        try
        {
          userNameQuery = userNameParser.parse (text);
          query.add (userNameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (VehicleController.class))
          {
            LogUtil.debug (VehicleController.class, "search", "Search user name: "
                + userNameSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
    }
    
    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("reservationDate", startDateStr, endDateStr, true, true);
      query.add (rangeQuery,Occur.MUST);
      
      if (LogUtil.isDebugEnabled (VehicleController.class))
      {
        LogUtil.debug (VehicleController.class, "search", "Search start date: "+startDateStr
            +" end date: "+endDateStr);
      }
    }
    if (infoFromSearch != null)
    {
      termQuery = new TermQuery (new Term ("reservationInfoFrom", infoFromSearch.toString ()));
      query.add (termQuery,Occur.MUST);
    }
    if (plateQuery != null || userNameQuery != null || rangeQuery != null || termQuery!= null)
    {
      return carServiceService.search (query, pageable, analyzer,filter);
    }else {
      return carServiceService.findPage (pageable);
    }
  
  }
  
  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id)
  {
    CarService carService = carServiceService.find (id);
    model.put ("carService", carService);
    return "carService/edit";
  }
  @RequestMapping (value = "/add", method = RequestMethod.GET)
  public String add (ModelMap model)
  {
    return "carService/add";
  }
  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (CarService carService,Long serviceCategoryId)
  {
	ServiceCategory category = serviceCategoryService.find(serviceCategoryId);
	carService.setServiceCategory(category);
    carServiceService.save (carService);
    return SUCCESS_MESSAGE;
  }
  
  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (CarService carService)
  { 
    carServiceService.update (carService,"createDate","tenantID");
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
      carServiceService.delete (ids);
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
    CarService carService = carServiceService.find(id);
    model.addAttribute("carService", carService);
    return "carService/details";
  }
  
  @RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST)
  public @ResponseBody Message uploadPhoto(@RequestParam("file") MultipartFile file,
      Long carServiceId) {
    Map<String, String> paramMap = new HashMap<String, String>();

//    String filePath = fileService.upload(FileType.PROFILE_PICTURE, file, identifier);
    String filePath = fileService.upload(FileType.PROFILE_PICTURE, file, paramMap);
    if (filePath != null && carServiceId != null) {
      CarService carService = carServiceService.find(carServiceId);
      carService.setImgPath(filePath);
      carServiceService.update(carService);
      return Message.success(filePath);
    } else {
      return ERROR_MESSAGE;
    }

  }
}
