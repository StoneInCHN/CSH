package com.csh.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.csh.dao.AccountBalanceDao;
import com.csh.dao.CarServiceDao;
import com.csh.entity.AccountBalance;
import com.csh.entity.EndUser;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.AccountBalanceService;

@Service("accountBalanceServiceImpl")
public class AccountBalanceServiceImpl extends BaseServiceImpl<AccountBalance, Long> implements
    AccountBalanceService {


  @Resource(name = "accountBalanceDaoImpl")
  private AccountBalanceDao accountBalanceDao;

  @Resource(name = "carServiceDaoImpl")
  private CarServiceDao carServiceDao;

  @Resource(name = "accountBalanceDaoImpl")
  public void setBaseDao(AccountBalanceDao accountBalanceDao) {
    super.setBaseDao(accountBalanceDao);
  }

  @Override
  public BigDecimal getOfflineBalance(EndUser endUser) {
    BigDecimal totalAmount = new BigDecimal(0);
    List<Filter> filters = new ArrayList<Filter>();
    Filter userFilter = new Filter("endUser", Operator.eq, endUser);
    filters.add(userFilter);
    List<AccountBalance> list = accountBalanceDao.findList(null, null, filters, null);
    for (AccountBalance accountBalance : list) {
      totalAmount = totalAmount.add(accountBalance.getBalance());
    }
    return totalAmount;
  }

  @Override
  public AccountBalance getOfflineBalanceByTenant(EndUser endUser, Long tenantId) {
    // CarService carService = carServiceDao.find(serviceId);
    List<Filter> filters = new ArrayList<Filter>();
    Filter userFilter = new Filter("endUser", Operator.eq, endUser);
    Filter tenantFilter = new Filter("tenantID", Operator.eq, tenantId);
    filters.add(userFilter);
    filters.add(tenantFilter);
    List<AccountBalance> list = accountBalanceDao.findList(null, null, filters, null);
    if (!CollectionUtils.isEmpty(list) && list.size() == 1) {
      return list.get(0);
    }
    return null;
  }
}
