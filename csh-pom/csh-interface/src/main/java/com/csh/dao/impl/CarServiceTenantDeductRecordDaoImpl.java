package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.CarServiceTenantDeductRecord;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.CarServiceTenantDeductRecordDao;
@Repository("carServiceTenantDeductRecordDaoImpl")
public class CarServiceTenantDeductRecordDaoImpl extends  BaseDaoImpl<CarServiceTenantDeductRecord,Long> implements CarServiceTenantDeductRecordDao {

}