package com.csh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
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
import org.springframework.web.multipart.MultipartFile;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.beans.FileInfo.FileType;
import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.CarService;
import com.csh.entity.CarServiceRecord;
import com.csh.entity.EndUser;
import com.csh.entity.ServiceCategory;
import com.csh.entity.Vehicle;
import com.csh.entity.VehicleInsurance;
import com.csh.entity.commonenum.CommonEnum.ChargeStatus;
import com.csh.entity.commonenum.CommonEnum.ImageType;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.CarServiceService;
import com.csh.service.EndUserService;
import com.csh.service.FileService;
import com.csh.service.ServiceCategoryService;
import com.csh.service.VehicleInsuranceService;
import com.csh.service.VehicleService;
import com.csh.utils.DateTimeUtils;
import com.csh.utils.ToolsUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Controller - 车辆保险
 * 
 * @author yohu
 *
 */
@Controller("vehicleInsuranceController")
@RequestMapping("console/vehicleInsurance")
public class VehicleInsuranceController extends BaseController {
  @Resource(name = "vehicleInsuranceServiceImpl")
  private VehicleInsuranceService vehicleInsuranceService;
  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;
  @Resource(name="vehicleServiceImpl")
  private VehicleService vehicleService;
  @Resource(name = "fileServiceImpl")
  private FileService fileService;
  @Resource (name = "carServiceServiceImpl")
  private CarServiceService carServiceService;
  
  @Resource (name = "serviceCategoryServiceImpl")
  private ServiceCategoryService serviceCategoryService;
  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/vehicleInsurance", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/vehicleInsurance/vehicleInsurance";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<VehicleInsurance> list(Model model, Pageable pageable,
      Date vehicleInsuranceEndDateStrart, Date vehicleInsuranceEndDateStrartEnd, String mobileNumSearch,
      String userNameSearch,String plateSearch) {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "endUser.userName",
        analyzer);
    QueryParser mobileNumParser = new QueryParser (Version.LUCENE_35, "endUser.mobile",
        analyzer);
    QueryParser plateParser = new QueryParser (Version.LUCENE_35, "vehicle.plate",
        analyzer);
    
    Query nameQuery = null;
    Query mobileNumQuery = null;
    Query plateQuery = null;
    TermRangeQuery rangeQuery = null;
    
    if (vehicleInsuranceEndDateStrart != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (vehicleInsuranceEndDateStrart, null);
    }
    if (vehicleInsuranceEndDateStrartEnd != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (vehicleInsuranceEndDateStrartEnd, null);
    }
    if (userNameSearch != null)
    {
      String text = QueryParser.escape (userNameSearch);
        try
        {
          nameQuery = nameParser.parse (text);
          query.add (nameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (TenantAccountController.class))
          {
            LogUtil.debug (TenantAccountController.class, "search", "Search user name: "
                + userNameSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
    }
    if (mobileNumSearch != null)
    {
      String text = QueryParser.escape (mobileNumSearch);
        try
        {
          mobileNumParser.setAllowLeadingWildcard (true);
          mobileNumQuery = mobileNumParser.parse ("*"+text+"*");
          query.add (mobileNumQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (TenantAccountController.class))
          {
            LogUtil.debug (TenantAccountController.class, "search", "Search mobile: "
                + mobileNumSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
    }
    if (plateSearch != null)
    {
      String text = QueryParser.escape (plateSearch);
        try
        {
          plateParser.setAllowLeadingWildcard (true);
          plateQuery = plateParser.parse ("*"+text+"*");
          query.add (plateQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (TenantAccountController.class))
          {
            LogUtil.debug (TenantAccountController.class, "search", "Search plate: "
                + plateSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
    }
    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("createDate", startDateStr, endDateStr, true, true);
      query.add (rangeQuery,Occur.MUST);
      
      if (LogUtil.isDebugEnabled (TenantAccountController.class))
      {
        LogUtil.debug (TenantAccountController.class, "search", "Search start date: "+startDateStr
            +" end date: "+endDateStr);
      }
    }
    if (nameQuery != null || rangeQuery != null 
        || plateQuery != null || mobileNumQuery != null)
    {
      return vehicleInsuranceService.search (query, pageable, analyzer,null,true);
    }
    
    return vehicleInsuranceService.findPage (pageable, true);
  }

  /**
   * 添加
   * 
   * @param vehicleInsurance
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(VehicleInsurance vehicleInsurance,Long vehicleId
      ,Long endUserId) {
    
    ServiceCategory category = serviceCategoryService.find (6L);
    EndUser endUser = endUserService.find (endUserId);
    Vehicle vehicle = vehicleService.find (vehicleId);

    List<com.csh.framework.filter.Filter> filters = new ArrayList<com.csh.framework.filter.Filter>();
    com.csh.framework.filter.Filter categoryFilter = new com.csh.framework.filter.Filter();
    categoryFilter.setProperty ("serviceCategory");
    categoryFilter.setOperator (Operator.eq);
    categoryFilter.setValue (category);
    filters.add (categoryFilter);
    
    List<CarService> carServiceList = carServiceService.findList (null, filters, null);
//    if (carServiceList == null || carServiceList.size () != 1)
//    {
//      return ERROR_MESSAGE;
//    }
    String recordNo = ToolsUtils.generateRecordNo(carServiceList.get (0).getTenantInfo().getOrgCode());
    CarServiceRecord record = new CarServiceRecord ();
    record.setSubscribeDate (new Date ());
    record.setRecordNo (recordNo);
    record.setCarService (carServiceList.get (0));
    record.setChargeStatus (ChargeStatus.RESERVATION);
    record.setEndUser (endUser);
    record.setPrice (carServiceList.get (0).getPrice ());
    record.setTenantID (carServiceList.get (0).getTenantInfo ().getId ());
    
   
    
    vehicleInsurance.setEndUser (endUser);
    vehicleInsurance.setVehicle (vehicle);
    vehicleInsurance.setCarServiceRecord (record);
    vehicleInsuranceService.save (vehicleInsurance,true);
    return SUCCESS_MESSAGE;
  }
  /**
   * 添加
   * 
   * @param vehicleInsurance
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add() {
    return "vehicleInsurance/add";
    
  }
  /**
   * 删除
   * 
   * @param id arrays
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      vehicleInsuranceService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

  /**
   * 获取数据进入编辑页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    VehicleInsurance vehicleInsurance = vehicleInsuranceService.find(id);
    List<Map<String, Object>> vehicleListMap = vehicleService.findVehicleUnderUser(vehicleInsurance.getEndUser ().getId ());
    
    ObjectMapper objectMapper = new ObjectMapper();
    String result = null;
    try
    {
      result= objectMapper.writeValueAsString (vehicleListMap);
    }
    catch (JsonProcessingException e)
    {
      e.printStackTrace();
    }
    model.put ("vehicleListMap", result);
    
    model.addAttribute("vehicleInsurance", vehicleInsurance);
    return "vehicleInsurance/edit";
  }

  /**
   * 更新
   * 
   * @param vehicleInsurance
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(VehicleInsurance vehicleInsurance,Long vehicleId,Long endUserId) {
	    
    EndUser endUser = endUserService.find (endUserId);
    Vehicle vehicle = vehicleService.find (vehicleId);
    vehicleInsurance.setEndUser (endUser);
    vehicleInsurance.setVehicle (vehicle);
    vehicleInsuranceService.update(vehicleInsurance,"createDate","tenantID","carServiceRecord");
    return SUCCESS_MESSAGE;
  }
  
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {
    VehicleInsurance vehicleInsurance = vehicleInsuranceService.find(id);
    
    
    model.addAttribute("vehicleInsurance", vehicleInsurance);
    return "vehicleInsurance/details";
  }
}
