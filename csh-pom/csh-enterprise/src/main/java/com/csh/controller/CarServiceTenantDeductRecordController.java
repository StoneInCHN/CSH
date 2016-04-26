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

import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.CarServiceTenantDeductRecord;
import com.csh.entity.commonenum.CommonEnum.ChargeStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.CarServiceTenantDeductRecordService;
import com.csh.service.TenantAccountService;
import com.csh.utils.DateTimeUtils;

/**
 * 服务消费记录
 * @author huyong
 *
 */
@Controller ("carServiceTenantDeductRecordController")
@RequestMapping ("console/carServiceTenantDeductRecord")
public class CarServiceTenantDeductRecordController extends BaseController
{

  @Resource (name = "carServiceTenantDeductRecordServiceImpl")
  private CarServiceTenantDeductRecordService carServiceTenantDeductRecordService;
 
  @Resource(name="tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping (value = "/carServiceTenantDeductRecord", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "/carServiceTenantDeductRecord/carServiceTenantDeductRecord";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<CarServiceTenantDeductRecord> list (Model model, Pageable pageable,
      Date beginDate, Date endDate,String recordNoSearch, String serviceCategorySearch,
      String serviceNameSearch,String endUserSearch, ChargeStatus chargeStatusSearch,
      PaymentType paymentTypeSearch)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser serviceNameParser = new QueryParser (Version.LUCENE_35,
        "carService.serviceName", analyzer);
    QueryParser userNameParser = new QueryParser (Version.LUCENE_35,
        "endUser.userName", analyzer);
    QueryParser recordNoParser = new QueryParser (Version.LUCENE_35,
        "recordNo", analyzer);
    Query serviceNameQuery = null;
    Query recordNoQuery = null;
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
    
    if (recordNoSearch != null)
    {
      String text = QueryParser.escape (recordNoSearch);
      try
      {
        //通配符查询，开启*开头，但影响效率
        recordNoParser.setAllowLeadingWildcard (true);

        recordNoQuery = recordNoParser.parse ("*" + text + "*");

        query.add (recordNoQuery, Occur.MUST);

        if (LogUtil.isDebugEnabled (VehicleController.class))
        {
          LogUtil.debug (VehicleController.class, "search",
              "Search service name: " + recordNoSearch);
        }
      }
      catch (ParseException e)
      {
        e.printStackTrace ();
      }
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
    
    if (serviceNameQuery != null || recordNoQuery != null || userNameQuery != null || rangeQuery != null
        || categoryTermQuery != null || statusTermQuery != null || typeTermQuery != null)
    {
      return carServiceTenantDeductRecordService.search (query, pageable, analyzer, filter, true);
    }
    else
    {
      return  carServiceTenantDeductRecordService.findPage (pageable,true);
    }
  }
  /**
   * 获取当前周期的账单
   * @param model
   * @return
   */
  @RequestMapping (value = "/showCurrentClearingRecord", method = RequestMethod.POST)
  public @ResponseBody List<CarServiceTenantDeductRecord> showCurrentClearingRecord (Model model)
  {
    return carServiceTenantDeductRecordService.findCurrentClearingRecords ();
  }
  /**
   * 获取当前周期的账单
   * @param model
   * @return
   */
  @RequestMapping (value = "/details", method = RequestMethod.GET)
  public String details (ModelMap model,Long id)
  {
    CarServiceTenantDeductRecord carServiceTenantDeductRecord = carServiceTenantDeductRecordService.find (id);
    model.put ("carServiceTenantDeductRecord", carServiceTenantDeductRecord);
    return "/carServiceTenantDeductRecord/details";
  }
}
