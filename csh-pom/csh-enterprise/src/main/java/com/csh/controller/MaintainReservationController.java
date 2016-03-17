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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.DeviceInfo;
import com.csh.entity.MaintainReservation;
import com.csh.entity.Vehicle;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.MaintainReservationService;
import com.csh.utils.DateTimeUtils;

/**
 * 保养预约
 * @author huyong
 *
 */
@Controller ("maintainReservationController")
@RequestMapping ("console/maintainReservation")
public class MaintainReservationController extends BaseController
{

  @Resource (name = "maintainReservationServiceImpl")
  private MaintainReservationService maintainReservationService;
  
  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/maintainReservation", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/maintainReservation/maintainReservation";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<MaintainReservation> list(Model model, Pageable pageable,
      Date beginDate, Date endDate, String plateSearch,String userNameSearch) {
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
    if (plateQuery != null || userNameQuery != null || rangeQuery != null )
    {
      return maintainReservationService.search (query, pageable, analyzer,filter,true);
    }else {
      return maintainReservationService.findPage (pageable, true);
    }
  
  }
}
