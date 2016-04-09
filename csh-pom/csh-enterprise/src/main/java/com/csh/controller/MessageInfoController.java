package com.csh.controller;

import java.util.ArrayList;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.MessageInfo;
import com.csh.entity.commonenum.CommonEnum.BindStatus;
import com.csh.entity.commonenum.CommonEnum.SendType;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.MessageInfoService;
import com.csh.utils.DateTimeUtils;

/**
 * 设备管理
 * @author huyong
 *
 */
@Controller ("messageInfoController")
@RequestMapping ("console/messageInfo")
public class MessageInfoController extends BaseController
{

  @Resource (name = "messageInfoServiceImpl")
  private MessageInfoService messageInfoService;

  @RequestMapping (value = "/shortMessagePush", method = RequestMethod.GET)
  public String shortMessagePush (ModelMap model)
  {
    model.put ("sendType", SendType.SMS);
    return "smsMessageInfo/messageInfo";
  }

  @RequestMapping (value = "/notificationPush", method = RequestMethod.GET)
  public String notificationPush (ModelMap model)
  {
    model.put ("sendType", SendType.PUSH);
    return "pushMessageInfo/messageInfo";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<MessageInfo> list (Pageable pageable,
      ModelMap model, Date beginDate, Date endDate, String deviceNoSearch,
      String deviceTpyeSearch, BindStatus bindStatusSearch,SendType sendType)
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
    TermQuery typeqQuery = null;
    TermQuery sendTypeQuery = null;
    
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
        nameQuery = nameParser.parse ("*" + text + "*");
        query.add (nameQuery, Occur.MUST);

        if (LogUtil.isDebugEnabled (MessageInfoController.class))
        {
          LogUtil.debug (MessageInfoController.class, "search",
              "Search device NO: " + deviceNoSearch);
        }
      }
      catch (ParseException e)
      {
        e.printStackTrace ();
      }
    }
    if (bindStatusSearch != null)
    {
      statusQuery = new TermQuery (new Term ("bindStatus",
          bindStatusSearch.toString ()));
      query.add (statusQuery, Occur.MUST);
    }
    if (sendType != null)
    {
      sendTypeQuery = new TermQuery (new Term ("sendType",
          sendType.toString ()));
      query.add (sendTypeQuery, Occur.MUST);
    }
    if (deviceTpyeSearch != null)
    {
      typeqQuery = new TermQuery (new Term ("type.name",
          deviceTpyeSearch.toString ()));
      query.add (typeqQuery, Occur.MUST);
    }
    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("createDate", startDateStr, endDateStr,
          true, true);
      query.add (rangeQuery, Occur.MUST);

      if (LogUtil.isDebugEnabled (MessageInfoController.class))
      {
        LogUtil.debug (MessageInfoController.class, "search",
            "Search start date: " + startDateStr + " end date: " + endDateStr);
      }
    }
    if (nameQuery != null || rangeQuery != null || typeqQuery != null
        || statusQuery != null)
    {
      return messageInfoService
          .search (query, pageable, analyzer, filter, true);
    }else {
      List<com.csh.framework.filter.Filter> filters = new ArrayList<com.csh.framework.filter.Filter>();
      com.csh.framework.filter.Filter sendTypeFilter = new com.csh.framework.filter.Filter("sendType",Operator.eq,sendType);
      filters.add (sendTypeFilter);
      pageable.setFilters (filters);
      
      return messageInfoService.findPage (pageable, true);
    }
    

  }

  /**
   * get data for vendor edit page
   * 
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id)
  {
    MessageInfo messageInfo = messageInfoService.find (id);
    model.put ("messageInfo", messageInfo);
    if (messageInfo.getSendType () == SendType.SMS)
    {
      return "smsMessageInfo/edit";
    }else {
      return "pushMessageInfo/edit";
    }
    
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (MessageInfo messageInfo, Long[] ids)
  {

    messageInfoService.saveMessage (messageInfo, ids);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/add", method = RequestMethod.GET)
  public String add (ModelMap modelMap,SendType sendType)
  {
    modelMap.put ("sendType", sendType);
    if (sendType == SendType.SMS)
    {
      return "smsMessageInfo/add";
    }else {
      return "pushMessageInfo/add";
    }
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (MessageInfo messageInfo)
  {
    messageInfoService.update (messageInfo);
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
      messageInfoService.delete (ids);
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
    MessageInfo messageInfo = messageInfoService.find (id);
    model.addAttribute ("messageInfo", messageInfo);
    if (messageInfo.getSendType () == SendType.SMS)
    {
      return "smsMessageInfo/details";
    }else {
      return "pushMessageInfo/details";
    }
  }
}
