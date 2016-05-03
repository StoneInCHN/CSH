package com.csh.controller;

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
import com.csh.entity.Advertisement;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.AdvertisementService;
import com.csh.utils.DateTimeUtils;

/**
 * 广告
 * @author huyong
 *
 */
@Controller ("advertisementController")
@RequestMapping ("console/advertisement")
public class AdvertisementController extends BaseController
{

  @Resource (name = "advertisementServiceImpl")
  private AdvertisementService advertisementService;


  @RequestMapping (value = "/advertisement", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "advertisement/advertisement";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Advertisement> list (String name,String title,Date beginDate, Date endDate, Pageable pageable, ModelMap model)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "operator",
        analyzer);
    QueryParser titleParser = new QueryParser (Version.LUCENE_35, "title",
        analyzer);
    Query nameQuery = null;
    Query titleQuery = null;
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
    if (name != null)
    {
      String text = QueryParser.escape (name);
        try
        {
          nameQuery = nameParser.parse (text);
          query.add (nameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (AdvertisementController.class))
          {
            LogUtil.debug (AdvertisementController.class, "search", "Search assetName: "
                + name );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
        
    }
    if (title != null)
    {
      String text = QueryParser.escape (title);
        try
        {
          titleQuery = titleParser.parse (text);
          query.add (titleQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (AdvertisementController.class))
          {
            LogUtil.debug (AdvertisementController.class, "search", "Search title: "
                + title );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
        
    }
    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("publishTime", startDateStr, endDateStr, true, true);
      query.add (rangeQuery,Occur.MUST);
      
      if (LogUtil.isDebugEnabled (AdvertisementController.class))
      {
        LogUtil.debug (AdvertisementController.class, "search", "Search start date: "+startDateStr
            +" end date: "+endDateStr);
      }
    }
    if (nameQuery != null || rangeQuery != null || titleQuery != null)
    {
      return advertisementService.search (query, pageable, analyzer,filter);
    }
    return advertisementService.findPage (pageable);
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
    model.put ("advertisement", advertisementService.find (id));
    return "advertisement/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (Advertisement advertisement)
  {
    advertisementService.save (advertisement,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (Advertisement advertisement)
  {
    advertisementService.update (advertisement,"createDate");
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
      advertisementService.delete (ids);
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
    Advertisement advertisement = advertisementService.find(id);
    model.addAttribute("advertisement", advertisement);
    return "advertisement/details";
  }
  
}
