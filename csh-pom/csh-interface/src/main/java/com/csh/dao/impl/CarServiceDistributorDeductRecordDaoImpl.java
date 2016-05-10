package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.CarServiceDistributorDeductRecord;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.CarServiceDistributorDeductRecordDao;
@Repository("carServiceDistributorDeductRecordDaoImpl")
public class CarServiceDistributorDeductRecordDaoImpl extends  BaseDaoImpl<CarServiceDistributorDeductRecord,Long> implements CarServiceDistributorDeductRecordDao {

}