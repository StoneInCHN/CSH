package com.csh.controller;

import java.util.Calendar;
import java.util.Date;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.CommissionRate;
import com.csh.entity.TenantClearingRecord;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.CarServiceRecordService;
import com.csh.service.CommissionRateService;
import com.csh.service.TenantAccountService;
import com.csh.service.TenantClearingRecordService;
import com.csh.utils.DateTimeUtils;

/**
 * 租户提成结算
 * @author huyong
 *
 */
@Controller ("tenantClearingRecordController")
@RequestMapping ("console/tenantClearingRecord")
public class TenantClearingRecordController extends BaseController
{

  @Resource (name = "tenantClearingRecordServiceImpl")
  private TenantClearingRecordService tenantClearingRecordService;
  @Resource (name = "carServiceRecordServiceImpl")
  private CarServiceRecordService carServiceRecordService;
  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  @Resource(name = "commissionRateServiceImpl")
  private CommissionRateService commissionRateService;
  
  
  @RequestMapping (value = "/tenantClearingRecord", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    Calendar current = Calendar.getInstance ();
    //星期一 = 2， 星期二 = 3
    int weekDay = current.get(Calendar.DAY_OF_WEEK);
    if (weekDay == 2 || weekDay == 3)
    {
      model.put ("allowClearing", true);
    }else {
      model.put ("allowClearing", true);
    }
    
    return "tenantClearingRecord/tenantClearingRecord";
  }
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<TenantClearingRecord> list (Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String clearingSnSearch)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "clearingSn",
        analyzer);
    Query nameQuery = null;
    TermRangeQuery rangeQuery = null;
   
    Filter filter = null;
    if (beginDate != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
    }
    if (endDate != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
    }
    if (clearingSnSearch != null)
    {
      String text = QueryParser.escape (clearingSnSearch);
        try
        {
          nameParser.setAllowLeadingWildcard (true);
          nameQuery = nameParser.parse ("*"+text+"*");
          query.add (nameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (TenantClearingRecordController.class))
          {
            LogUtil.debug (TenantClearingRecordController.class, "search", "Search device NO: "
                + clearingSnSearch );
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
      
      if (LogUtil.isDebugEnabled (TenantClearingRecordController.class))
      {
        LogUtil.debug (TenantClearingRecordController.class, "search", "Search start date: "+startDateStr
            +" end date: "+endDateStr);
      }
    }
    
    if (nameQuery != null || rangeQuery != null)
    {
      return tenantClearingRecordService.search (query, pageable, analyzer,filter,true);
    }
      return tenantClearingRecordService.findPage (pageable, true);
    
  }
  
  @RequestMapping (value = "/applyClearing", method = RequestMethod.GET)
  public String applyClearing (ModelMap mode)
  {
//    Map<String, Date> periodDateMap= tenantClearingRecordService.findPeriodBeginEndDate(tenantAccountService.getCurrentTenantInfo ());
//    mode.putAll (periodDateMap);
    List<CommissionRate> commissionRateList = commissionRateService.findAll ();
    mode.put ("commissionRate", commissionRateList.get (0));
    return "tenantClearingRecord/applyClearing";
  }
  
  @RequestMapping (value = "/details", method = RequestMethod.GET)
  public String detail (ModelMap mode,Long id)
  {
    TenantClearingRecord tenantClearingRecord= tenantClearingRecordService.find(id);
    mode.put ("tenantClearingRecord",tenantClearingRecord);
    return "tenantClearingRecord/details";
  }
  
  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (ModelMap mode,TenantClearingRecord tenantClearingRecord, Long[] ids)
  {
    Boolean result =tenantClearingRecordService.saveTenantClearingRecord(tenantClearingRecord
        , ids);
    if (result)
    {
      return SUCCESS_MESSAGE;
    }else {
      return ERROR_MESSAGE;
    }
    
  }
}
