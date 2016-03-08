package com.csh.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.PositionDao;
import com.csh.entity.Department;
import com.csh.entity.Position;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.PositionService;
import com.csh.utils.FieldFilterUtils;

/**
 * 职位
 * @author huyong
 *
 */
@Service("positionServiceImpl")
public class PositionServiceImpl extends BaseServiceImpl<Position, Long> implements PositionService {

  @Resource(name = "positionDaoImpl")
  private PositionDao positionDao;
  
  @Resource
  public void setBaseDao(PositionDao positionDao) {
    super.setBaseDao(positionDao);
  }

  @Override
  public List<Map<String, Object>> findPositions (Department department){
    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter("department",Operator.eq, department);
    filters.add(filter);
    List<Position> positionList = positionDao.findList (null, null, filters, null);
    String[] propertys =
      {"id", "name"};
    return FieldFilterUtils.filterCollectionMap(propertys, positionList);
  }

}
