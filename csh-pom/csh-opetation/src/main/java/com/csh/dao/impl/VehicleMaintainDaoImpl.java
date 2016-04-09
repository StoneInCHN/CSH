package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.VehicleMaintain;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.VehicleMaintainDao;
@Repository("vehicleMaintainDaoImpl")
public class VehicleMaintainDaoImpl extends  BaseDaoImpl<VehicleMaintain,Long> implements VehicleMaintainDao {

}