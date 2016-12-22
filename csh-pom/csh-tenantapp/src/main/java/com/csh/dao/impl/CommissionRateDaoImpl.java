package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.CommissionRate;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.CommissionRateDao;
@Repository("commissionRateDaoImpl")
public class CommissionRateDaoImpl extends  BaseDaoImpl<CommissionRate,Long> implements CommissionRateDao {

}