package com.csh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.VehicleBrandDetailDao;
import com.csh.entity.VehicleBrandDetail;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.VehicleBrandDetailService;

@Service ("vehicleBrandDetailServiceImpl")
public class VehicleBrandServiceImpl extends
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
  public List<VehicleBrandDetail> findRoots (Integer count)
  {

    return vehicleBrandDetailDaoDao.findRoots (count);
  }
}