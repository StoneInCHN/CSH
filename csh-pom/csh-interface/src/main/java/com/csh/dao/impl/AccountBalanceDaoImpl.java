package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.AccountBalance;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.AccountBalanceDao;
@Repository("accountBalanceDaoImpl")
public class AccountBalanceDaoImpl extends  BaseDaoImpl<AccountBalance,Long> implements AccountBalanceDao {

}