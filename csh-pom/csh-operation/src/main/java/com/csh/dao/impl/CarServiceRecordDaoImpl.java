package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.CarServiceRecord;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.CarServiceRecordDao;
@Repository("carServiceRecordDaoImpl")
public class CarServiceRecordDaoImpl extends  BaseDaoImpl<CarServiceRecord,Long> implements CarServiceRecordDao {

}