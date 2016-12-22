package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.AdvanceDeposits;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.AdvanceDepositsDao;
@Repository("advanceDepositsDaoImpl")
public class AdvanceDepositsDaoImpl extends  BaseDaoImpl<AdvanceDeposits,Long> implements AdvanceDepositsDao {

}