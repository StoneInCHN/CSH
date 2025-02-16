package com.csh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

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
import com.csh.entity.ConfigMeta;
import com.csh.entity.Role;
import com.csh.entity.commonenum.CommonEnum.TreeNodeState;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.response.TreeNodeResponse;
import com.csh.service.ConfigMetaService;
import com.csh.service.RoleService;
import com.csh.service.TenantAccountService;
import com.csh.service.TenantInfoService;
import com.csh.utils.DateTimeUtils;

/**
 * Controller - 角色
 * 
 * @author pengyanan
 *
 */
@Controller("roleController")
@RequestMapping("console/role")
public class RoleController extends BaseController {
  @Resource(name = "roleServiceImpl")
  private RoleService roleService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @Resource(name = "configMetaServiceImpl")
  private ConfigMetaService configMetaService;
  @Resource(name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/role", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/role/role";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Role> list(Model model, Pageable pageable,
      Date beginDate, Date endDate, String roleNameSearch) {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "name",
        analyzer);
    Query nameQuery = null;
    TermRangeQuery rangeQuery = null;
    
    if (beginDate != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
    }
    if (endDate != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
    }
    if (roleNameSearch != null)
    {
      String text = QueryParser.escape (roleNameSearch);
        try
        {
          nameParser.setAllowLeadingWildcard (true);
          nameQuery = nameParser.parse ("*"+text+"*");
          query.add (nameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (TenantAccountController.class))
          {
            LogUtil.debug (TenantAccountController.class, "search", "Search role name: "
                + roleNameSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
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
    if (nameQuery != null || rangeQuery != null)
    {
      return roleService.search (query, pageable, analyzer,null,true);
    }
    
    return roleService.findPage (pageable, true);
  }

  /**
   * 添加
   * 
   * @param role
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(Role role) {
    if (role != null) {
      role.setTenantID(tenantAccountService.getCurrentTenantID());
      role.setIsSystem(true);
      roleService.save(role);
    }
    return SUCCESS_MESSAGE;
  }
  /**
   * 查询角色
   * @param model
   * @return
   */
  @RequestMapping(value = "/commonRolesSearch", method = RequestMethod.GET)
  public String commonRolesSearch(ModelMap model) {
    return "/common/commonRolesSearch";
  }
  /**
   * 删除
   * 
   * @param id arrays
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      
      try
      {
        roleService.delete(ids);
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
   * 获取数据进入编辑页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    Role role = roleService.find(id);
    model.addAttribute("role", role);
    return "role/edit";
  }

  /**
   * 更新
   * 
   * @param role
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(Role role) {
    roleService.update(role);
    return SUCCESS_MESSAGE;
  }

  /**
   * 权限树列表
   * 
   * @param pageable
   * @param id
   * @return
   */
  @RequestMapping(value = "/listAuth", method = RequestMethod.POST)
  public @ResponseBody List<TreeNodeResponse> listAuth(Pageable pageable, Long id) {
    List<TreeNodeResponse> treeNodeResponses = new ArrayList<TreeNodeResponse>();
    Map<Long, TreeNodeResponse> parentMap = new HashMap<Long, TreeNodeResponse>();
    Role role = roleService.find(id);
    Set<ConfigMeta> currentAuthList = null;// 当前角色的权限
    if (role != null && role.getConfigMetas () != null) {
      currentAuthList = role.getConfigMetas();
    }

    // 租户能使用的所有功能包
    Set<ConfigMeta> packageAll = tenantInfoService.getCurrentTenantVersionPackage ();

    /*
     * 创建树结构，定义树结构只有2级，子节点必须有父节点Id,没有parentId的肯定是父节点
     */
    // 创建父节点（package）
    for (ConfigMeta packagecConfigMeta : packageAll) {
      TreeNodeResponse treeNodeResponse = new TreeNodeResponse();
        treeNodeResponse.setId(packagecConfigMeta.getId());
        treeNodeResponse.setText(packagecConfigMeta.getName ());
        if (currentAuthList.contains(packagecConfigMeta)) {
//          treeNodeResponse.setChecked(true);
        } else {
          treeNodeResponse.setChecked(false);
        }
        treeNodeResponse.setIconCls("icon-large-chart");
        treeNodeResponse.setState (TreeNodeState.closed);
        
        
        //配置子节点（function）
        List<ConfigMeta> relatedFunctions = configMetaService.findRelationFunction (packagecConfigMeta);
        List<TreeNodeResponse> childList = new ArrayList<TreeNodeResponse>();
        for (ConfigMeta function : relatedFunctions)
        {
          
          TreeNodeResponse treeNodeResponseChild = new TreeNodeResponse();
          
          treeNodeResponseChild.setId(function.getId());
          treeNodeResponseChild.setText(function.getName ());
          if (currentAuthList.contains(function)) {
            treeNodeResponseChild.setChecked(true);
          } else {
            treeNodeResponseChild.setChecked(false);
          }
          treeNodeResponseChild.setIconCls("icon-large-shapes");
          
          treeNodeResponseChild.setState (TreeNodeState.open);
          
          if (treeNodeResponse != null && treeNodeResponse.getChildren() != null) {
            childList = treeNodeResponse.getChildren();
          }
          
          
          childList.add(treeNodeResponseChild);
          
        }
        treeNodeResponse.setChildren(childList);
        
//        parentMap.put(treeNodeResponse.getId(), treeNodeResponse);
        treeNodeResponses.add(treeNodeResponse);
    }
    // 创建子节点

    return treeNodeResponses;
  }


  /**
   * 授权
   * 
   * @param role
   * @return
   */
  @RequestMapping(value = "/addAuth", method = RequestMethod.POST)
  public @ResponseBody Message addAuth(Long[] authIds, Long id) {
    Role role = roleService.find(id);
    Set<ConfigMeta> authorityResources = new HashSet<ConfigMeta>();

    if (authIds == null || id == null) {
      LogUtil.debug(RoleController.class, "RoleController.addAuth()",
          "authId=null or id=null, tenant ID=%s", tenantAccountService.getCurrentTenantID());
      return ERROR_MESSAGE;
    }
    List<ConfigMeta> authorityResourceList = configMetaService.findList (authIds);
    authorityResources.addAll (authorityResourceList);
    role.setConfigMetas (authorityResources);
    roleService.update (role);

    return SUCCESS_MESSAGE;
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
      return !roleService.exists (tenantFilter,fieldFilter,idFilter);
    }else {
      return !roleService.exists (tenantFilter,fieldFilter);
    }
  }
}
