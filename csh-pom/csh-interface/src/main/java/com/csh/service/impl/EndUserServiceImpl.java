package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csh.common.log.LogUtil;
import com.csh.dao.EndUserDao;
import com.csh.entity.EndUser;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.EndUserService;

@Service("endUserServiceImpl")
public class EndUserServiceImpl extends BaseServiceImpl<EndUser, Long> implements EndUserService {

  @Resource(name = "endUserDaoImpl")
  private EndUserDao endUserDao;

  @Resource(name = "endUserDaoImpl")
  public void setBaseDao(EndUserDao endUserDao) {
    super.setBaseDao(endUserDao);
  }

  @Transactional(readOnly = true)
  public EndUser findByUserName(String username) {
    return endUserDao.findByUserName(username);
  }


  @Transactional(readOnly = true)
  public EndUser findByUserMobile(String mobileNo) {
    return endUserDao.findByUserMobile(mobileNo);
  }

  @Override
  public String getEndUserToken(Long id) {
    EndUser endUser = endUserDao.find(id);
    if (AccountStatus.LOCKED.equals(endUser.getAccountStatus())
        || AccountStatus.DELETE.equals(endUser.getAccountStatus())) {
      if (LogUtil.isDebugEnabled(EndUserServiceImpl.class)) {
        LogUtil.debug(EndUserServiceImpl.class, "check EndUser Account Status",
            "The endUser is in disabled or delete status. endUser ID: %s, accountStatus: %s",
            id.toString(), endUser.getAccountStatus().toString());
      }
      return null;
    }
    return endUserDao.getEndUserToken(id);
  }


  @Override
  public String createEndUserToken(String token, Long id) {
    return endUserDao.createEndUserToken(token, id);
  }


  @Override
  public void deleteEndUserToken(Long id) {
	  endUserDao.deleteEndUserToken(id);
  }


}
