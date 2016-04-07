package com.csh.service.impl; 

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.EndUserDao;
import com.csh.dao.MessageInfoDao;
import com.csh.dao.MsgEndUserDao;
import com.csh.entity.MessageInfo;
import com.csh.entity.MsgEndUser;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.MessageInfoService;
import com.csh.service.TenantAccountService;

@Service("messageInfoServiceImpl")
public class MessageInfoServiceImpl extends BaseServiceImpl<MessageInfo,Long> implements MessageInfoService {

      @Resource(name="endUserDaoImpl")
      private EndUserDao endUserDao;
      @Resource(name="messageInfoDaoImpl")
      private MessageInfoDao messageInfoDao;
      @Resource(name="msgEndUserDaoImpl")
      private MsgEndUserDao msgEndUserDao;
      @Resource(name = "tenantAccountServiceImpl")
      private TenantAccountService tenantAccountService;
      @Resource
      public void setBaseDao(MessageInfoDao messageInfoDao) {
         super.setBaseDao(messageInfoDao);
  }

      @Override
      @Transactional(propagation = Propagation.REQUIRED)
      public void saveMessage (MessageInfo messageInfo, Long[] ids)
      {
        messageInfo.setTenantID (tenantAccountService.getCurrentTenantID ());
        for (Long id : ids)
        {
          MsgEndUser msgEndUser = new MsgEndUser ();
          msgEndUser.setMessage (messageInfo);
          msgEndUser.setEndUser (endUserDao.find (id));
          msgEndUserDao.persist (msgEndUser);
        }
        messageInfoDao.persist (messageInfo);
      }
}