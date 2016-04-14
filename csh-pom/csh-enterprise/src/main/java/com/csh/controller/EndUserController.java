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
import com.csh.entity.EndUser;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.EndUserService;
import com.csh.service.RSAService;
import com.csh.utils.DateTimeUtils;

/**
 * 终端用户
 * @author huyong
 *
 */
@Controller ("endUserController")
@RequestMapping ("console/endUser")
public class EndUserController extends BaseController
{

  @Resource (name = "endUserServiceImpl")
  private EndUserService endUserService;
  @Resource(name = "rsaServiceImpl")
  private RSAService rsaService;
  
  @RequestMapping (value = "/endUser", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "endUser/endUser";
  }
  @RequestMapping (value = "/commonEndUserSearch", method = RequestMethod.GET)
  public String commonEndUserSearch (ModelMap model)
  {
    return "common/commonEndUserSearch";
  }
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<EndUser> list (Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String userNameSearch,AccountStatus accountStatusSearch)
  {
    
     return endUserService.findEndUser (pageable, model, beginDate, endDate, userNameSearch, accountStatusSearch);
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
    EndUser endUser = endUserService.find (id);
    model.put ("endUser", endUser);
    return "endUser/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (EndUser endUser)
  {
    endUserService.save (endUser,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (String enPassword,EndUser endUser)
  {
    if (!enPassword.equals (endUser.getPassword ()))
    {
      endUser.setPassword (DigestUtils.md5Hex(endUser.getPassword ()));
    }
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
      endUserService.delete (ids);
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
    EndUser endUser = endUserService.find(id);
    model.addAttribute("endUser", endUser);
    return "endUser/details";
  }
}
