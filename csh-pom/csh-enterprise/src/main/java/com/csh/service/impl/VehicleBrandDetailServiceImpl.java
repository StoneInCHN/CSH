package com.csh.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.VehicleBrandDetailDao;
import com.csh.entity.VehicleBrandDetail;
import com.csh.entity.VehicleLine;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.VehicleBrandDetailService;
import com.csh.utils.FieldFilterUtils;

@Service ("vehicleBrandDetailServiceImpl")
public class VehicleBrandDetailServiceImpl extends
    BaseServiceImpl<VehicleBrandDetail, Long> implements VehicleBrandDetailService
{

  @Resource (name = "vehicleBrandDetailDaoImpl")
  private VehicleBrandDetailDao vehicleBrandDetailDaoDao;

  @Resource
  public void setBaseDao (VehicleBrandDetailDao vehicleBrandDetailDao)
  {
    super.setBaseDao (vehicleBrandDetailDao);
  }

  @Override
  public List<Map<String, Object>> findVehicleBrandDetailByLine (VehicleLine vehicleLine)
  {
    
    List<Filter> filters = new ArrayList<Filter>();
    Filter vehicleLineFilter = new Filter ();
    vehicleLineFilter.setOperator (Operator.eq);
    vehicleLineFilter.setValue (vehicleLine);
    vehicleLineFilter.setProperty ("vehicleLine");
    
    filters.add (vehicleLineFilter);
    List<VehicleBrandDetail> vehicleBrandDetailList = vehicleBrandDetailDaoDao.findList (null, null, filters, null);
    String[] propertys = {"id", "name"};
    return FieldFilterUtils.filterCollectionMap(propertys, vehicleBrandDetailList);
  }
}