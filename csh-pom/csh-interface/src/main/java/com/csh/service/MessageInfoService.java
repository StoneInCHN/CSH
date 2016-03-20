package com.csh.service; 

import java.util.List;

import com.csh.entity.MessageInfo;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.BaseService;

public interface MessageInfoService extends BaseService<MessageInfo,Long>{
	/**
	   * 根据终端用户获取消息
	   * 
	   * @param userId
	   * @param pageable
	   * @return
	   */
	  Page<MessageInfo> findMsgByUser(Long userId, Pageable pageable);
	  
	  /**
	   * 根据终端用户获取全部消息
	   * @param userId
	   * @return
	   */
	  List<MessageInfo> findMsgByUser(Long userId);
}