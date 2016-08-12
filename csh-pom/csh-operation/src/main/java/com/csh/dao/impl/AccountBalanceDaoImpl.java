package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.AccountBalanceDao;
import com.csh.entity.AccountBalance;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("accountBalanceDaoImpl")
public class AccountBalanceDaoImpl extends BaseDaoImpl<AccountBalance, Long> implements
    AccountBalanceDao {

}
