package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.GpsSwitchRecord;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.GpsSwitchRecordDao;
@Repository("gpsSwitchRecordDaoImpl")
public class GpsSwitchRecordDaoImpl extends  BaseDaoImpl<GpsSwitchRecord,Long> implements GpsSwitchRecordDao {

}