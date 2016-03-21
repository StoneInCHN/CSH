package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.MessageInfo;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.MessageInfoDao;
@Repository("messageInfoDaoImpl")
public class MessageInfoDaoImpl extends  BaseDaoImpl<MessageInfo,Long> implements MessageInfoDao {

}