package com.csh.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import com.csh.entity.Role;
import com.csh.entity.TenantAccount;
import com.csh.entity.TenantUser;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.RSAService;
import com.csh.service.RoleService;
import com.csh.service.TenantAccountService;
import com.csh.service.TenantUserService;
import com.csh.utils.DateTimeUtils;

/**
 * 租户用户
 * @author huyong
 *
 */
@Controller ("tenantAccountController")
@RequestMapping ("console/tenantAccount")
public class TenantAccountController extends BaseController
{

  @Resource (name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  @Resource (name = "tenantUserServiceImpl")
  private TenantUserService tenantUserService;
  @Resource(name = "roleServiceImpl")
  private RoleService roleService;
  @Resource(name = "rsaServiceImpl")
  private RSAService rsaService;
  
  @RequestMapping (value = "/tenantAccount", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "tenantAccount/tenantAccount";
  }
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<TenantAccount> list (Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String userNameSearch,AccountStatus accountStatusSearch)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "userName",
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
    if (userNameSearch != null)
    {
      String text = QueryParser.escape (userNameSearch);
        try
        {
          nameParser.setAllowLeadingWildcard (true);
          nameQuery = nameParser.parse ("*"+text+"*");
          query.add (nameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (TenantAccountController.class))
          {
            LogUtil.debug (TenantAccountController.class, "search", "Search real name: "
                + userNameSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
    }
    if (accountStatusSearch != null)
    {
      statusQuery = new TermQuery (new Term ("accoutStatus",accountStatusSearch.toString ()));
      query.add (statusQuery,Occur.MUST);
    }
    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("createDate", startDateStr, endDateStr, true, true);
      query.add (rangeQuery,Occur.MUST);
      
      if (LogUtil.isDebugEnabled (TenantAccountController.class))
      {
        LogUtil.debug (TenantAccountController.class, "search", "Search start date: "+startDateStr
            +" end date: "+endDateStr);
      }
    }
    if (nameQuery != null || rangeQuery != null || statusQuery != null)
    {
      return tenantAccountService.search (query, pageable, analyzer,filter,true);
    }
      return tenantAccountService.findPage (pageable, true);
    
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
    TenantAccount tenantAccount = tenantAccountService.find (id);
    Set<Role> roles =tenantAccount.getRoles ();
    model.put ("tenantAccount", tenantAccount);
    if (roles != null && roles.iterator ().hasNext ())
    {
      model.put ("roleInfo", roles.iterator ().next ());
    }
    return "tenantAccount/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (TenantAccount tenantAccount,Long tenantUserID,Long roleID)
  {
    
    TenantUser tenantUser=tenantUserService.find(tenantUserID);
    Role role =roleService.find (roleID);
    Set<Role> roleSet = new HashSet <Role> ();
    roleSet.add (role);
    tenantAccount.setTenantUser(tenantUser);
    tenantAccount.setIsSystem (false);
    tenantAccount.setRoles (roleSet);
    tenantAccount.setPassword (DigestUtils.md5Hex(tenantAccount.getPassword ()));
    tenantAccountService.save (tenantAccount,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (String enPassword,TenantAccount tenantAccount,Long tenantUserID,Long roleID)
  {
    TenantUser tenantUser=tenantUserService.find(tenantUserID);
    Role role =roleService.find (roleID);
    Set<Role> roleSet = new HashSet<Role> ();
    roleSet.add (role);
    if (!enPassword.equals (tenantAccount.getPassword ()))
    {
      tenantAccount.setPassword (DigestUtils.md5Hex(tenantAccount.getPassword ()));
    }
    tenantAccount.setTenantUser(tenantUser);
    tenantAccount.setRoles (roleSet);
    tenantAccountService.update (tenantAccount,"createDate","isSystem","tenantID");
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
        tenantAccountService.delete (ids);
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
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {
    TenantAccount tenantAccount = tenantAccountService.find(id);
    model.addAttribute("tenantAccount", tenantAccount);
    return "tenantAccount/details";
  }
  
  @RequestMapping (value = "/uniqueFieldCheck", method = RequestMethod.POST)
  public @ResponseBody Boolean uniqueFieldCheck (Long id,String filedName, String value)
  {

    com.csh.framework.filter.Filter tenantFilter = new com.csh.framework.filter.Filter (
        "tenantID", Operator.eq, tenantAccountService.getCurrentTenantID ());
    com.csh.framework.filter.Filter fieldFilter = new com.csh.framework.filter.Filter (
        filedName, Operator.eq, value);
    if (id != null)
    { 
      com.csh.framework.filter.Filter idFilter = new com.csh.framework.filter.Filter (
        "id", Operator.ne, id);
      return !tenantAccountService.exists (tenantFilter,fieldFilter,idFilter);
    }else {
      return !tenantAccountService.exists (tenantFilter,fieldFilter);
    }
  }
    
}
