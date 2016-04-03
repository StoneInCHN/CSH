package com.csh.dao.impl; 

import org.springframework.stereotype.Repository;

import com.csh.dao.CarServiceRecordDao;
import com.csh.entity.CarServiceRecord;
import com.csh.framework.dao.impl.BaseDaoImpl;
@Repository("carServiceRecordDaoImpl")
public class CarServiceRecordDaoImpl extends  BaseDaoImpl<CarServiceRecord,Long> implements CarServiceRecordDao {

}