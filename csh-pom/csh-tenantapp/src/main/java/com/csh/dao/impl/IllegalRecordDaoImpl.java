package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.IllegalRecord;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.IllegalRecordDao;
@Repository("illegalRecordDaoImpl")
public class IllegalRecordDaoImpl extends  BaseDaoImpl<IllegalRecord,Long> implements IllegalRecordDao {

}