package com.csh.controller;

import java.util.Calendar;
import java.util.Date;

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
import com.csh.entity.TenantDeductClearingRecord;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.CarServiceRecordService;
import com.csh.service.TenantAccountService;
import com.csh.service.TenantDeductClearingRecordService;
import com.csh.utils.DateTimeUtils;

/**
 * 租户提成结算
 * @author huyong
 *
 */
@Controller ("tenantDeductClearingRecordController")
@RequestMapping ("console/tenantDeductClearingRecord")
public class TenantDeductClearingRecordController extends BaseController
{

  @Resource (name = "tenantDeductClearingRecordServiceImpl")
  private TenantDeductClearingRecordService tenantDeductClearingRecordService;
  @Resource (name = "carServiceRecordServiceImpl")
  private CarServiceRecordService carServiceRecordService;
  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  
  @RequestMapping (value = "/tenantDeductClearingRecord", method = RequestMethod.GET)
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
    return "tenantDeductClearingRecord/tenantDeductClearingRecord";
  }
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<TenantDeductClearingRecord> list (Pageable pageable, ModelMap model,
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
          
          if (LogUtil.isDebugEnabled (TenantDeductClearingRecordController.class))
          {
            LogUtil.debug (TenantDeductClearingRecordController.class, "search", "Search device NO: "
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
      
      if (LogUtil.isDebugEnabled (TenantDeductClearingRecordController.class))
      {
        LogUtil.debug (TenantDeductClearingRecordController.class, "search", "Search start date: "+startDateStr
            +" end date: "+endDateStr);
      }
    }
    
    if (nameQuery != null || rangeQuery != null)
    {
      return tenantDeductClearingRecordService.search (query, pageable, analyzer,filter,true);
    }
      return tenantDeductClearingRecordService.findPage (pageable, true);
    
  }
  
  @RequestMapping (value = "/applyDeductClearing", method = RequestMethod.GET)
  public String applyDeductClearing (ModelMap mode)
  {
//    Map<String, Date> periodDateMap= tenantClearingRecordService.findPeriodBeginEndDate(tenantAccountService.getCurrentTenantInfo ());
//    mode.putAll (periodDateMap);
    
    return "tenantDeductClearingRecord/applyDeductClearing";
  }
  
  @RequestMapping (value = "/details", method = RequestMethod.GET)
  public String detail (ModelMap mode,Long id)
  {
    TenantDeductClearingRecord tenantDeductClearingRecord= tenantDeductClearingRecordService.find(id);
    mode.put ("tenantDeductClearingRecord",tenantDeductClearingRecord);
    return "tenantDeductClearingRecord/details";
  }
  
  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (ModelMap mode,TenantDeductClearingRecord tenantDeductClearingRecord, Long[] ids)  {
    
    tenantDeductClearingRecordService.saveTenantDeductClearingRecord (tenantDeductClearingRecord, ids);
    return SUCCESS_MESSAGE;
  }
}
