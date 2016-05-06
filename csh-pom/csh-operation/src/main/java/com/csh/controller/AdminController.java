package com.csh.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csh.beans.Message;
import com.csh.beans.Setting;
import com.csh.controller.base.BaseController;
import com.csh.entity.Admin;
import com.csh.entity.Admin.AdminStatus;
import com.csh.entity.commonenum.CommonEnum.SystemType;
import com.csh.entity.Role;
import com.csh.framework.entity.BaseEntity.Save;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Pageable;
import com.csh.service.AdminService;
import com.csh.service.DistributorService;
import com.csh.service.RoleService;
import com.csh.utils.SettingUtils;

/**
 * Controller - 管理员
 * 
 */
@Controller("adminController")
@RequestMapping("/console/admin")
public class AdminController extends BaseController {

  @Resource(name = "adminServiceImpl")
  private AdminService adminService;
  @Resource(name = "roleServiceImpl")
  private RoleService roleService;
  @Resource(name = "distributorServiceImpl")
  private DistributorService distributorService;

  /**
   * 检查用户名是否存在
   */
  @RequestMapping(value = "/check_username", method = RequestMethod.GET)
  public @ResponseBody boolean checkUsername(String username) {
    if (StringUtils.isEmpty(username)) {
      return false;
    }
    if (adminService.usernameExists(username)) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model) {
    List<Filter> filters = new ArrayList<Filter>();
    Setting setting = SettingUtils.get();
    filters.add(Filter.eq("systemType", SystemType.OPERATION));
    filters.add(Filter.ne("id", setting.getDefaultDistributorRoleId()));
    model.addAttribute("roles", roleService.findList(null, filters, null));
    model.addAttribute("adminStatusTypes", AdminStatus.values());
    return "/admin/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(Admin admin,Long[] roleIds) {
    admin.setRoles(new HashSet<Role>(roleService.findList(roleIds)));
    if (!isValid(admin, Save.class)) {
      return ERROR_VIEW;
    }
    if (adminService.usernameExists(admin.getUsername())) {
      return ERROR_VIEW;
    }
    admin.setIsDistributor(false);
    admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
    admin.setIsSystem(false);
    adminService.save(admin);
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    List<Filter> filters = new ArrayList<Filter>();
    Setting setting = SettingUtils.get();
    filters.add(Filter.eq("systemType", SystemType.OPERATION));
    filters.add(Filter.ne("id", setting.getDefaultDistributorRoleId()));
    model.addAttribute("roles", roleService.findList(null, filters, null));
    model.addAttribute("admin", adminService.find(id));
    model.addAttribute("adminStatusTypes", AdminStatus.values());
    return "/admin/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(Admin admin, Long[] roleIds, RedirectAttributes redirectAttributes) {
    admin.setRoles(new HashSet<Role>(roleService.findList(roleIds)));
    if (!isValid(admin)) {
      return ERROR_VIEW;
    }
    Admin pAdmin = adminService.find(admin.getId());
    if (pAdmin == null) {
      return ERROR_VIEW;
    }
    if (StringUtils.isNotEmpty(admin.getPassword())) {
      admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
    } else {
      admin.setPassword(pAdmin.getPassword());
    }
    adminService
        .update(admin, "username", "loginDate", "loginIp", "isDistributor", "distributor", "isSystem");

    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    List<Filter> filters = new ArrayList<Filter>();
    filters.add(Filter.ne("isDistributor", true));
    pageable.setFilters(filters);
    model.addAttribute("page", adminService.findPage(pageable));
    model.addAttribute("adminStatusTypes", AdminStatus.values());
    return "/admin/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids.length >= adminService.count()) {
      return Message.error("admin.common.deleteAllNotAllowed");
    }
    adminService.delete(ids);
    return SUCCESS_MESSAGE;
  }

  
  @RequestMapping(value = "/selectDistributor", method = RequestMethod.GET)
  public String selectDistributor(ModelMap modelMap, Pageable pageable) {
    pageable.setPageSize(5);;
    modelMap.addAttribute("page", distributorService.findPage(pageable));
    return "/apply/selectDistributor";
  }
  
  /**
   * 添加
   */
  @RequestMapping(value = "/add4distributor", method = RequestMethod.GET)
  public String add4distributor(ModelMap model) {
    return "/admin/add4distributor";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save4distributor", method = RequestMethod.POST)
  public String save4distributor(Admin admin, Long[] roleIds,Long distributorId) {
   
    
    Setting setting = SettingUtils.get();
    Role role = roleService.find(setting.getDefaultDistributorRoleId());
    Set<Role> roles = new HashSet<Role>();
    roles.add(role);
    admin.setRoles(roles);
    
    if (!isValid(admin, Save.class)) {
      return ERROR_VIEW;
    }
    if (adminService.usernameExists(admin.getUsername())) {
      return ERROR_VIEW;
    }
    admin.setIsDistributor(true);
    admin.setDistributor(distributorService.find(distributorId));
    admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
    admin.setIsSystem(false);
    adminService.save(admin);
    return "redirect:list4distributor.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit4distributor", method = RequestMethod.GET)
  public String edit4distributor(Long id, ModelMap model) {
    model.addAttribute("admin", adminService.find(id));
    model.addAttribute("adminStatusTypes", AdminStatus.values());
    return "/admin/edit4distributor";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update4distributor", method = RequestMethod.POST)
  public String update4distributor(Admin admin) {
    Admin pAdmin = adminService.find(admin.getId());
    if (StringUtils.isNotEmpty(admin.getPassword())) {
      pAdmin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
    } 
    if (StringUtils.isNotEmpty(admin.getEmail())) {
      pAdmin.setEmail(admin.getEmail());
    }
    if (StringUtils.isNotEmpty(admin.getName())) {
      pAdmin.setName(admin.getName());
    }
    pAdmin.setAdminStatus(admin.getAdminStatus());
    adminService.update(pAdmin);
    return "redirect:list4distributor.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list4distributor", method = RequestMethod.GET)
  public String list4distributor(Pageable pageable, ModelMap model) {
    List<Filter> filters = new ArrayList<Filter>();
    filters.add(Filter.eq("isDistributor", true));
    pageable.setFilters(filters);
    model.addAttribute("page", adminService.findPage(pageable));
    model.addAttribute("adminStatusTypes", AdminStatus.values());
    return "/admin/list4distributor";
  }
  
}
