package com.csh.service.impl; 

import java.util.List;

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.MessageInfo;
import com.csh.dao.MessageInfoDao;
import com.csh.service.MessageInfoService;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("messageInfoServiceImpl")
public class MessageInfoServiceImpl extends BaseServiceImpl<MessageInfo,Long> implements MessageInfoService {
		
	  @Resource(name="messageInfoDaoImpl")
	  private MessageInfoDao messageInfoDao;
	  
      @Resource(name="messageInfoDaoImpl")
      public void setBaseDao(MessageInfoDao messageInfoDao) {
         super.setBaseDao(messageInfoDao);
      }

	@Override
	public Page<MessageInfo> findMsgByUser(Long userId, Pageable pageable) {
		return messageInfoDao.findMsgByUser(userId, pageable);
	}

	@Override
	public List<MessageInfo> findMsgByUser(Long userId) {
		return messageInfoDao.findMsgByUser(userId);
	}
}