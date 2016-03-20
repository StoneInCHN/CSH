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
    
    App app = appService.findEntityByTenantID ();
    model.put ("app", app);
    return "/app/app";
  }

  
//  @RequestMapping (value = "/add", method = RequestMethod.POST)
//  public @ResponseBody Message add (App app)
//  {
//    appService.save (app,true);
//    return SUCCESS_MESSAGE;
//  }
  
  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message update (App app)
  { 
    appService.update (app,"createDate","tenantID");
    return SUCCESS_MESSAGE;
  }
  
}
