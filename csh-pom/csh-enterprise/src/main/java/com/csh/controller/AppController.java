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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.App;
import com.csh.entity.commonenum.CommonEnum.ReservationInfoFrom;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.AppService;
import com.csh.utils.DateTimeUtils;

/**
 * App配置
 * @author huyong
 *
 */
@Controller ("appController")
@RequestMapping ("console/app")
public class AppController extends BaseController
{

  @Resource (name = "appServiceImpl")
  private AppService appService;
  
  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/app", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/app/app";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<App> list(Model model, Pageable pageable,
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
    if (plateQuery != null || userNameQuery != null || rangeQuery != null || termQuery!= null)
    {
      return appService.search (query, pageable, analyzer,filter,true);
    }else {
      return appService.findPage (pageable, true);
    }
  
  }
  
  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id)
  {
    App app = appService.find (id);
    model.put ("app", app);
    return "app/edit";
  }
  @RequestMapping (value = "/add", method = RequestMethod.GET)
  public String add (ModelMap model)
  {
    return "maintainReservation/add";
  }
  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (App app)
  {
    appService.save (app,true);
    return SUCCESS_MESSAGE;
  }
  
  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (App app)
  { 
    appService.update (app,"createDate","tenantID");
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
      appService.delete (ids);
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
    App app = appService.find(id);
    model.addAttribute("app", app);
    return "app/details";
  }
}
