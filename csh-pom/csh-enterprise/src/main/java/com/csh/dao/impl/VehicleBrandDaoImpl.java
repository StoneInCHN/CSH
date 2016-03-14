package com.csh.dao.impl; 

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository; 

import com.csh.entity.VehicleBrand;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.VehicleBrandDao;
@Repository("vehicleBrandDaoImpl")
public class VehicleBrandDaoImpl extends  BaseDaoImpl<VehicleBrand,Long> implements VehicleBrandDao {

}