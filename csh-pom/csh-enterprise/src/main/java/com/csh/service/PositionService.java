package com.csh.service;

import java.util.List;
import java.util.Map;

import com.csh.entity.Department;
import com.csh.entity.Position;
import com.csh.framework.service.BaseService;


/**
 * 职位
 * @author huyong
 *
 */
public interface PositionService extends BaseService<Position, Long> {
  /**
   * 获取所有部门，用于下拉菜单
   * @return
   */
  List<Map<String, Object>> findPositions(Department department);
}
