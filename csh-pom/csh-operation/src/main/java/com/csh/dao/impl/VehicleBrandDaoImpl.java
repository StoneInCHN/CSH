package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.VehicleBrandDao;
import com.csh.entity.VehicleBrand;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("vehicleBrandDaoImpl")
public class VehicleBrandDaoImpl extends BaseDaoImpl<VehicleBrand, Long> implements VehicleBrandDao {

}
