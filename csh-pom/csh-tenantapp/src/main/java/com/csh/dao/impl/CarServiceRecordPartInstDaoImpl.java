package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.CarServiceRecordPartInst;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.CarServiceRecordPartInstDao;
@Repository("carServiceRecordPartInstDaoImpl")
public class CarServiceRecordPartInstDaoImpl extends  BaseDaoImpl<CarServiceRecordPartInst,Long> implements CarServiceRecordPartInstDao {

}