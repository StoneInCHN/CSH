package com.csh.service; 

import com.csh.entity.EndUser;
import com.csh.entity.MessageInfo;
import com.csh.entity.MsgEndUser;
import com.csh.framework.service.BaseService;

public interface MsgEndUserService extends BaseService<MsgEndUser,Long>{
	/**
	   * 根据用户和消息查询两者关系
	   * 
	   * @param endUser
	   * @param msg
	   * @return
	   */
	  MsgEndUser findMsgEndUserByUserAndMsg(EndUser endUser, MessageInfo msg);
}