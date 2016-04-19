package com.csh.controller;

import java.util.Date;
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
import com.csh.entity.CarServiceRecord;
import com.csh.entity.commonenum.CommonEnum.ChargeStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.CarServiceRecordService;
import com.csh.service.TenantAccountService;
import com.csh.utils.DateTimeUtils;

/**
 * 服务消费记录
 * @author huyong
 *
 */
@Controller ("CarServiceRecordController")
@RequestMapping ("console/carServiceRecord")
public class CarServiceRecordController extends BaseController
{

  @Resource (name = "carServiceRecordServiceImpl")
  private CarServiceRecordService carServiceRecordService;
 
  @Resource(name="tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping (value = "/carServiceRecord", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "/carServiceRecord/carServiceRecord";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<CarServiceRecord> list (Model model, Pageable pageable,
      Date beginDate, Date endDate, String serviceCategorySearch,
      String serviceNameSearch,String endUserSearch, ChargeStatus chargeStatusSearch,
      PaymentType paymentTypeSearch)
  {
    Page<CarServiceRecord> carServiceRecordPage;
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser serviceNameParser = new QueryParser (Version.LUCENE_35,
        "carService.serviceName", analyzer);
    QueryParser userNameParser = new QueryParser (Version.LUCENE_35,
        "endUser.userName", analyzer);
    Query serviceNameQuery = null;
    Query userNameQuery = null;
    TermRangeQuery rangeQuery = null;
    TermQuery categoryTermQuery = null;
    TermQuery statusTermQuery = null;
    TermQuery typeTermQuery = null;
    
    Filter filter = null;
    
    if (beginDate != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
    }
    if (endDate != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
    }
    if (serviceNameSearch != null)
    {
      String text = QueryParser.escape (serviceNameSearch);
      try
      {
        //通配符查询，开启*开头，但影响效率
        serviceNameParser.setAllowLeadingWildcard (true);

        serviceNameQuery = serviceNameParser.parse ("*" + text + "*");

        query.add (serviceNameQuery, Occur.MUST);

        if (LogUtil.isDebugEnabled (VehicleController.class))
        {
          LogUtil.debug (VehicleController.class, "search",
              "Search service name: " + serviceNameSearch);
        }
      }
      catch (ParseException e)
      {
        e.printStackTrace ();
      }
    }
    if (endUserSearch != null)
    {
      String text = QueryParser.escape (endUserSearch);
      try
      {
        //通配符查询，开启*开头，但影响效率
        userNameParser.setAllowLeadingWildcard (true);

        userNameQuery = userNameParser.parse ("*" + text + "*");

        query.add (userNameQuery, Occur.MUST);

        if (LogUtil.isDebugEnabled (VehicleController.class))
        {
          LogUtil.debug (VehicleController.class, "search",
              "Search user name: " + endUserSearch);
        }
      }
      catch (ParseException e)
      {
        e.printStackTrace ();
      }
    }
    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("paymentDate", startDateStr,
          endDateStr, true, true);
      query.add (rangeQuery, Occur.MUST);

      if (LogUtil.isDebugEnabled (VehicleController.class))
      {
        LogUtil.debug (VehicleController.class, "search", "Search start date: "
            + startDateStr + " end date: " + endDateStr);
      }
    }
    if (chargeStatusSearch != null)
    {
      statusTermQuery = new TermQuery (new Term ("chargeStatus",
          chargeStatusSearch.toString ()));
      query.add (statusTermQuery, Occur.MUST);
    }
    if (paymentTypeSearch != null)
    {
      typeTermQuery = new TermQuery (new Term ("paymentType",
          paymentTypeSearch.toString ()));
      query.add (typeTermQuery, Occur.MUST);
    }
    if (serviceCategorySearch != null && serviceCategorySearch != null)
    {
      categoryTermQuery = new TermQuery (new Term ("serviceCategory.categoryName",
          serviceCategorySearch));
      query.add (categoryTermQuery, Occur.MUST);
    }
    
    if (serviceNameQuery != null || userNameQuery != null || rangeQuery != null
        || categoryTermQuery != null || statusTermQuery != null || typeTermQuery != null)
    {
      carServiceRecordPage= carServiceRecordService.search (query, pageable, analyzer, filter, true);
    }
    else
    {
      carServiceRecordPage= carServiceRecordService.findPage (pageable,true);
    }
    checkOverDue (carServiceRecordPage.getRows ());
    return carServiceRecordPage;
  }
  /**
   * 获取当前周期的账单
   * @param model
   * @return
   */
  @RequestMapping (value = "/showCurrentClearingRecord", method = RequestMethod.POST)
  public @ResponseBody List<CarServiceRecord> showCurrentClearingRecord (Model model)
  {
    return carServiceRecordService.findCurrentClearingRecords ();
  }
  /**
   * 获取当前周期的账单
   * @param model
   * @return
   */
  @RequestMapping (value = "/details", method = RequestMethod.GET)
  public String details (ModelMap model,Long id)
  {
    CarServiceRecord carServiceRecord = carServiceRecordService.find (id);
    model.put ("carServiceRecord", carServiceRecord);
    return "/carServiceRecord/details";
  }
  /**
   * get data for vendor edit page
   * 
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("carServiceRecord", carServiceRecordService.find(id));
    return "carServiceRecord/edit";
  }
  /**
   * get data for vendor edit page
   * 
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(ModelMap model, CarServiceRecord carServiceRecord) {
    CarServiceRecord newCarServiceRecord =carServiceRecordService.find (carServiceRecord.getId ());
    newCarServiceRecord.setPrice (carServiceRecord.getPrice ());
    newCarServiceRecord.setChargeStatus (carServiceRecord.getChargeStatus ());
    carServiceRecordService.update (newCarServiceRecord);

    return SUCCESS_MESSAGE;
  }
  
  @RequestMapping(value = "/payCodeCheck", method = RequestMethod.POST)
  public @ResponseBody Boolean payCodeCheck(ModelMap model, Long carServiceRecordId,String payCode) {
    CarServiceRecord carServiceRecord =carServiceRecordService.find (carServiceRecordId);
    
    if (carServiceRecord.getPayCode ().equals (payCode))
    {
      return true;
    }else {
      return false;
    }
    
  }
  private void checkOverDue(List<CarServiceRecord> recordList){
    for (CarServiceRecord carServiceRecord : recordList)
    {
      Date currentDate= new Date ();
      if ((carServiceRecord.getChargeStatus () == ChargeStatus.RESERVATION || carServiceRecord.getChargeStatus () == ChargeStatus.RESERVATION_SUCCESS)
          &&carServiceRecord.getSubscribeDate () != null && DateTimeUtils.daysBetween (currentDate, carServiceRecord.getSubscribeDate ()) > 1)
      {
        carServiceRecord.setChargeStatus (ChargeStatus.OVERDUE);
      }
    }
  }
}
