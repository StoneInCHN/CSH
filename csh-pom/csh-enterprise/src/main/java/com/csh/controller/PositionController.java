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
import com.csh.entity.Position;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.DepartmentService;
import com.csh.service.PositionService;
import com.csh.service.TenantAccountService;

/**
 * 部门管理
 * @author huyong
 *
 */
@Controller ("PositionController")
@RequestMapping ("console/position")
public class PositionController extends BaseController{
  
  @Resource (name = "departmentServiceImpl")
  private DepartmentService departmentService;

  @Resource (name = "positionServiceImpl")
  private PositionService positionService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @RequestMapping (value = "/position", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "position/position";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Position> list (Pageable pageable){
      return positionService.findPage (pageable,true);
  }


  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id){
    model.addAttribute ("position", positionService.find (id));
    return "position/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (Position position,Long departmentId)
  {
    if(departmentId !=null){
     Department department =  departmentService.find(departmentId);
     position.setDepartment(department);
     positionService.save(position, true);
     return SUCCESS_MESSAGE;
    }else{
      return ERROR_MESSAGE;
    }
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (Position position ,Long departmentId)
  {
    if(departmentId !=null){
      Department department =  departmentService.find(departmentId);
      position.setDepartment(department);
     }
    positionService.update (position,"tenantID");
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
      positionService.delete (ids);
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

    model.addAttribute ("position", positionService.find (id));
    return "position/details";
  }
  
  @RequestMapping(value = "/findPositions", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findPositions(Long id) {
    if (id !=null) {
      return positionService.findPositions (departmentService.find(id));
    }else{
      return null;
    }
    
  }
  /**
   * 
   * @param name
   * @param grade
   * @return
   */
  @RequestMapping(value = "/checkUniqueName", method = RequestMethod.POST)
  public @ResponseBody Boolean checkUniqueName(String name,Long departmentId,Long positionId) {
    
    Department parentDepartment = departmentService.find (departmentId);
    Filter tenantFilter = new Filter("tenantID",Operator.eq,tenantAccountService.getCurrentTenantID ());
    Filter nameFilter = new Filter("name",Operator.eq,name);
    Filter parentDepartmentFilter = new Filter ("department",Operator.eq,parentDepartment);
    if (positionId != null)
    {
      Filter positionIdFilter = new Filter("id",Operator.ne,positionId);
      return !positionService.exists (tenantFilter,nameFilter,parentDepartmentFilter,positionIdFilter);
    }else {
      return !positionService.exists (tenantFilter,nameFilter,parentDepartmentFilter);
    }
    
  }
}
