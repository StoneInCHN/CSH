package com.csh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.CarService;
import com.csh.entity.CarServiceRecord;
import com.csh.entity.EndUser;
import com.csh.entity.MaintainReservation;
import com.csh.entity.RepareReservation;
import com.csh.entity.ServiceCategory;
import com.csh.entity.commonenum.CommonEnum.ChargeStatus;
import com.csh.entity.commonenum.CommonEnum.ReservationInfoFrom;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.CarServiceService;
import com.csh.service.EndUserService;
import com.csh.service.RepareReservationService;
import com.csh.service.ServiceCategoryService;
import com.csh.service.VehicleService;
import com.csh.utils.DateTimeUtils;
import com.csh.utils.ToolsUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 维修预约
 * @author huyong
 *
 */
@Controller ("repareReservationController")
@RequestMapping ("console/repareReservation")
public class RepareReservationController extends BaseController
{

  @Resource (name = "repareReservationServiceImpl")
  private RepareReservationService repareReservationService;
  
  @Resource (name = "endUserServiceImpl")
  private EndUserService endUserService;
  
  @Resource (name = "vehicleServiceImpl")
  private VehicleService vehicleService;
  
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
  @RequestMapping(value = "/repareReservation", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/repareReservation/repareReservation";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<RepareReservation> list(Model model, Pageable pageable,
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
    if (plateQuery != null || userNameQuery != null || rangeQuery != null || infoFromSearch != null)
    {
      return repareReservationService.search (query, pageable, analyzer,filter,true);
    }else {
      return repareReservationService.findPage (pageable, true);
    }
  
  }
  
  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id)
  {
    RepareReservation repareReservation = repareReservationService.find (id);
    List<Map<String, Object>> vehicleListMap = vehicleService.findVehicleUnderUser(repareReservation.getEndUser ().getId ());
    
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
    model.put ("repareReservation", repareReservation);
    return "repareReservation/edit";
  }
  @RequestMapping (value = "/add", method = RequestMethod.GET)
  public String add (ModelMap model)
  {
    return "repareReservation/add";
  }
  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (RepareReservation repareReservation,Long endUserID)
  {
    //查询出维修服务的类型
    ServiceCategory category = serviceCategoryService.find (3L);
    
    List<com.csh.framework.filter.Filter> filters = new ArrayList<com.csh.framework.filter.Filter>();
    com.csh.framework.filter.Filter categoryFilter = new com.csh.framework.filter.Filter();
    categoryFilter.setProperty ("serviceCategory");
    categoryFilter.setOperator (Operator.eq);
    categoryFilter.setValue (category);
    filters.add (categoryFilter);
    
    List<CarService> carServiceList = carServiceService.findList (null, filters,null,true,null);
    if (carServiceList == null)
    {
      return ERROR_MESSAGE;
    }
    EndUser endUser = endUserService.find (endUserID);
    String recordNo = ToolsUtils.generateRecordNo(carServiceList.get (0).getTenantInfo().getOrgCode());
    CarServiceRecord record = new CarServiceRecord ();
    record.setSubscribeDate (new Date ());
    record.setRecordNo (recordNo);
    record.setCarService (carServiceList.get (0));
    record.setChargeStatus (ChargeStatus.RESERVATION);
    record.setEndUser (endUser);
    record.setPrice (carServiceList.get (0).getPrice ());
    record.setTenantID (carServiceList.get (0).getTenantInfo ().getId ());
    repareReservation.setEndUser (endUser);
    repareReservation.setReservationInfoFrom (ReservationInfoFrom.CALL);
    repareReservation.setCarServiceRecord (record);
    repareReservationService.save (repareReservation,true);
    return SUCCESS_MESSAGE;
  }
  
  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (RepareReservation repareReservation,Long endUserID)
  { 
    EndUser endUser = endUserService.find (endUserID);
    repareReservation.setEndUser (endUser);
    repareReservation.setReservationInfoFrom (ReservationInfoFrom.CALL);
    repareReservationService.update (repareReservation,"createDate","tenantID","carServiceRecord");
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
      repareReservationService.delete (ids);
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
    RepareReservation repareReservation = repareReservationService.find(id);
    model.addAttribute("repareReservation", repareReservation);
    return "repareReservation/details";
  }
  
  @RequestMapping(value = "/approve", method = RequestMethod.GET)
  public @ResponseBody Message approve(Long id) {
    RepareReservation repareReservation = repareReservationService.find(id);
    repareReservation.getCarServiceRecord ().setChargeStatus (ChargeStatus.RESERVATION_SUCCESS);
    repareReservationService.save (repareReservation);
    return SUCCESS_MESSAGE;
  }
  @RequestMapping(value = "/reject", method = RequestMethod.GET)
  public @ResponseBody Message reject(Long id) {
    RepareReservation repareReservation = repareReservationService.find(id);
    repareReservation.getCarServiceRecord ().setChargeStatus (ChargeStatus.RESERVATION_FAIL);
    repareReservationService.save (repareReservation);
    return SUCCESS_MESSAGE;
  }
}
