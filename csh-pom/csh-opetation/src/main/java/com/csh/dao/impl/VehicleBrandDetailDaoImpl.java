package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.VehicleBrandDetail;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.VehicleBrandDetailDao;
@Repository("vehicleBrandDetailDaoImpl")
public class VehicleBrandDetailDaoImpl extends  BaseDaoImpl<VehicleBrandDetail,Long> implements VehicleBrandDetailDao {

}