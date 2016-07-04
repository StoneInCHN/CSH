package com.csh.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.BooleanClause.Occur;
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
import com.csh.entity.estore.ParameterGroup;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.ParameterGroupService;
import com.csh.utils.DateTimeUtils;

/**
 * 商品参数
 * @author huyong
 *
 */
@Controller ("parameterGroupController")
@RequestMapping ("console/parameterGroup")
public class ParameterGroupController extends BaseController
{

  @Resource (name = "parameterGroupServiceImpl")
  private ParameterGroupService parameterGroupService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping (value = "/parameterGroup", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "estore/parameterGroup/parameterGroup";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<ParameterGroup> list (Model model, Pageable pageable,
      Date beginDate, Date endDate, String  parameterGroupNameSearch
      )
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();
  
    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "name",
        analyzer);
    
    TermRangeQuery rangeQuery = null;
    Query nameQuery = null;
    Filter filter = null;
    if (parameterGroupNameSearch != null)
    {
      String text = QueryParser.escape (parameterGroupNameSearch);
        try
        {
          nameParser.setAllowLeadingWildcard (true);
          nameQuery = nameParser.parse ("*"+text+"*");
          query.add (nameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (DeviceInfoController.class))
          {
            LogUtil.debug (DeviceInfoController.class, "search", "Search brand name: "
                + parameterGroupNameSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
    }
    
    if (beginDate != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
    }
    if (endDate != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
    }
   

    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("createDate", startDateStr,
          endDateStr, true, true);
      query.add (rangeQuery, Occur.MUST);

      if (LogUtil.isDebugEnabled (VehicleController.class))
      {
        LogUtil.debug (VehicleController.class, "search", "Search start date: "
            + startDateStr + " end date: " + endDateStr);
      }
    }
    if (rangeQuery != null || nameQuery != null)
    {
      return parameterGroupService.search (query, pageable, analyzer, filter,true);
    }else{
      return parameterGroupService.findPage (pageable,true);
    }

  }

  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id)
  {
    ParameterGroup parameterGroup = parameterGroupService.find (id);
    model.put ("parameterGroup", parameterGroup);
    return "estore/parameterGroup/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.GET)
  public String add (ModelMap model)
  {
    return "estore/parameterGroup/add";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (Long productCategoryId,ParameterGroup parameterGroup)
  {
    parameterGroupService.saveParameterGrpoup (productCategoryId,parameterGroup);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (ParameterGroup parameterGroup)
  {
    
    parameterGroupService.updateParameterGroup (parameterGroup);
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
      
      try
      {
        parameterGroupService.delete (ids);
      }
      catch (Exception e)
      {
        e.printStackTrace ();
        return Message.error ("csh.delete.fail");
      }
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
    ParameterGroup parameterGroup = parameterGroupService.find (id);
    model.put ("parameterGroup", parameterGroup);
    return "estore/parameterGroup/details";
  }


}
