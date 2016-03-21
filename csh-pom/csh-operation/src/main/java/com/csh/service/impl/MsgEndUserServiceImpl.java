package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.MsgEndUser;
import com.csh.dao.MsgEndUserDao;
import com.csh.service.MsgEndUserService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("msgEndUserServiceImpl")
public class MsgEndUserServiceImpl extends BaseServiceImpl<MsgEndUser,Long> implements MsgEndUserService {

      @Resource(name="msgEndUserDaoImpl")
      public void setBaseDao(MsgEndUserDao msgEndUserDao) {
         super.setBaseDao(msgEndUserDao);
  }
}