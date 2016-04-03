package com.csh.controller;

import java.util.Date;

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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.TenantClearingRecord;
import com.csh.entity.commonenum.CommonEnum.BindStatus;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.TenantClearingRecordService;
import com.csh.utils.DateTimeUtils;

/**
 * 租户结算
 * @author huyong
 *
 */
@Controller ("tenantClearingRecordController")
@RequestMapping ("console/tenantClearingRecord")
public class TenantClearingRecordController extends BaseController
{

  @Resource (name = "tenantClearingRecordServiceImpl")
  private TenantClearingRecordService tenantClearingRecordService;
  
  @RequestMapping (value = "/tenantClearingRecord", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "tenantClearingRecord/tenantClearingRecord";
  }
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<TenantClearingRecord> list (Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String deviceNoSearch,String deviceTpyeSearch,BindStatus bindStatusSearch)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "deviceNo",
        analyzer);
    Query nameQuery = null;
    TermRangeQuery rangeQuery = null;
    TermQuery statusQuery = null;
    TermQuery typeqQuery =null;
   
    Filter filter = null;
    if (beginDate != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
    }
    if (endDate != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
    }
    if (deviceNoSearch != null)
    {
      String text = QueryParser.escape (deviceNoSearch);
        try
        {
          nameParser.setAllowLeadingWildcard (true);
          nameQuery = nameParser.parse ("*"+text+"*");
          query.add (nameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (TenantClearingRecordController.class))
          {
            LogUtil.debug (TenantClearingRecordController.class, "search", "Search device NO: "
                + deviceNoSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
    }
    if (bindStatusSearch != null)
    {
      statusQuery = new TermQuery (new Term ("bindStatus",bindStatusSearch.toString ()));
      query.add (statusQuery,Occur.MUST);
    }
    if (deviceTpyeSearch != null)
    {
      typeqQuery = new TermQuery (new Term ("type.name",deviceTpyeSearch.toString ()));
      query.add (typeqQuery,Occur.MUST);
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
    if (nameQuery != null || rangeQuery != null || typeqQuery != null || statusQuery != null)
    {
      return tenantClearingRecordService.search (query, pageable, analyzer,filter,true);
    }
      return tenantClearingRecordService.findPage (pageable, true);
    
  }
  
  @RequestMapping (value = "/applyClearing", method = RequestMethod.GET)
  public String applyClearing ()
  {
    return "tenantClearingRecord/applyClearing";
  }
}
