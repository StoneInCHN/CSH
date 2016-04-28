package com.csh.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.Department;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.service.DepartmentService;
import com.csh.service.TenantAccountService;

/**
 * 部门管理
 * 
 * @author huyong
 *
 */
@Controller("departmentController")
@RequestMapping("console/department")
public class DepartmentController extends BaseController {

  @Resource(name = "departmentServiceImpl")
  private DepartmentService departmentService;
  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @RequestMapping(value = "/department", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "department/department";
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public @ResponseBody List<Department> list() {
    return departmentService.findRoots();
  }

  /**
   * get data for vendor edit page
   * 
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("department", departmentService.find(id));
    return "department/edit";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(Long parentId, Department department) {

    if (parentId != null) {
      Department parent = departmentService.find(parentId);
      department.setParent(parent);
      Integer grade = parent.getGrade();
      if (grade == 0) {
        grade = 1;
      }
      department.setGrade(++grade);
    } else {
      department.setGrade(1);
    }
    departmentService.save(department, true);;
    return SUCCESS_MESSAGE;
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(Department department, Long parentId) {
    if (parentId != null) {
      Department parent = departmentService.find(parentId);
      department.setParent(parent);
      department.setGrade(parent.getGrade() + 1);
    }
    departmentService.update(department,"tenantID");
    return SUCCESS_MESSAGE;
  }


  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      // 检查是否能被删除
      // if()
      departmentService.delete(ids);
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

    model.addAttribute("department", departmentService.find(id));
    return "department/details";
  }

  @RequestMapping(value = "/findAllDepartments", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAllDepartments() {
    return departmentService.findAllDepartments();
  }

  /**
   * 
   * @param name
   * @param grade
   * @return
   * true: 存在相同的名称
   * false:不存在相同的名称
   */
  @RequestMapping(value = "/checkUniqueName", method = RequestMethod.POST)
  public @ResponseBody Boolean checkUniqueName(String name,Long parentId,Long departmentId) {
    
    Department parentDepartment = departmentService.find (parentId);
    Filter tenantFilter = new Filter("tenantID",Operator.eq,tenantAccountService.getCurrentTenantID ());
    Filter nameFilter = new Filter("name",Operator.eq,name);
    Filter parentDepartmentFilter = new Filter ("parent",Operator.eq,parentDepartment);
    if (departmentId != null)
    {
      Filter departmentIdFilter = new Filter("id",Operator.ne,departmentId);
      return !departmentService.exists (tenantFilter,nameFilter,parentDepartmentFilter,departmentIdFilter);
    }else {
      return !departmentService.exists (tenantFilter,nameFilter,parentDepartmentFilter);
    }
    
    
    
  }
}
