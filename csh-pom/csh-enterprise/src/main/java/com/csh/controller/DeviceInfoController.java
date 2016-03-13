package com.csh.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
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
import com.csh.entity.DeviceInfo;
import com.csh.entity.EndUser;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;
import com.csh.entity.commonenum.CommonEnum.DeviceStatus;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.DeviceInfoService;
import com.csh.service.EndUserService;
import com.csh.service.RSAService;
import com.csh.utils.DateTimeUtils;

/**
 * 设备管理
 * @author huyong
 *
 */
@Controller ("deviceController")
@RequestMapping ("console/deviceInfo")
public class DeviceInfoController extends BaseController
{

  @Resource (name = "deviceInfoServiceImpl")
  private DeviceInfoService deviceInfoService;
  
  @RequestMapping (value = "/deviceInfo", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "deviceInfo/deviceInfo";
  }
  @RequestMapping (value = "/commonDeviceInfoSearch", method = RequestMethod.GET)
  public String commonDeviceInfoSearch (ModelMap model)
  {
    return "common/commonDeviceInfoSearch";
  }
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<DeviceInfo> list (Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String deviceNoSearch,DeviceStatus deviceStatusSearch)
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
          nameQuery = nameParser.parse (text);
          query.add (nameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (DeviceInfoController.class))
          {
            LogUtil.debug (DeviceInfoController.class, "search", "Search device NO: "
                + deviceNoSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
    }
    if (deviceStatusSearch != null)
    {
      statusQuery = new TermQuery (new Term ("deviceStatus",deviceStatusSearch.toString ()));
      query.add (statusQuery,Occur.MUST);
    }
    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("createDate", startDateStr, endDateStr, true, true);
      query.add (rangeQuery,Occur.MUST);
      
      if (LogUtil.isDebugEnabled (DeviceInfoController.class))
      {
        LogUtil.debug (DeviceInfoController.class, "search", "Search start date: "+startDateStr
            +" end date: "+endDateStr);
      }
    }
    if (nameQuery != null || rangeQuery != null || statusQuery != null)
    {
      return deviceInfoService.search (query, pageable, analyzer,filter,true);
    }
      return deviceInfoService.findPage (pageable, true);
    
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
    DeviceInfo deviceInfo = deviceInfoService.find (id);
    model.put ("deviceInfo", deviceInfo);
    return "deviceInfo/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (DeviceInfo deviceInfo)
  {
    deviceInfoService.save (deviceInfo,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (DeviceInfo deviceInfo)
  { 
    deviceInfoService.update (deviceInfo);
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
      deviceInfoService.delete (ids);
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
    DeviceInfo deviceInfo = deviceInfoService.find(id);
    model.addAttribute("deviceInfo", deviceInfo);
    return "endUser/details";
  }
}
