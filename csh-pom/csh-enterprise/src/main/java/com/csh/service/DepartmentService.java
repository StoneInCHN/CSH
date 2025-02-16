package com.csh.service;

import java.util.List;
import java.util.Map;

import com.csh.entity.Department;
import com.csh.framework.service.BaseService;


/**
 * 部门
 * 
 * @author huyong
 *
 */
public interface DepartmentService extends BaseService<Department, Long> {
  /**
   * 获取所有部门，用于下拉菜单
   * 
   * @return
   */
  List<Map<String, Object>> findAllDepartments();


  List<Department> findRoots();


  List<Department> findRoots(Integer count);


  List<Department> findRoots(Integer count, String cacheRegion);


  List<Department> findParents(Department department);


  List<Department> findParents(Department department, Integer count);


  List<Department> findParents(Department department, Integer count, String cacheRegion);


  List<Department> findTree();


  List<Department> findChildren(Department department);


  List<Department> findChildren(Department department, Integer count);


  List<Department> findChildren(Department department, Integer count, String cacheRegion);


}
