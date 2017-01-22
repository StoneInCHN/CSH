package com.csh.service;

import java.util.List;

import com.csh.entity.MessageInfo;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.BaseService;

public interface MessageInfoService extends BaseService<MessageInfo, Long> {
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
   * 
   * @param userId
   * @return
   */
  List<MessageInfo> findMsgByUser(Long userId);

  /**
   * 极光推送消息
   * 
   * @param msg
   */
  void jpushMsg(MessageInfo msg);

  /**
   * 创建消息缓存
   * 
   * @return
   */
  String createMsgInfoCache(String deviceNo, String msgContent);

  /**
   * 获取缓存消息
   * 
   * @param deviceNo
   * @return
   */
  String getMsgInfoCache(String deviceNo);

  /**
   * 删除缓存消息
   * 
   * @param deviceNo
   * @param msgContent
   */
  void deleteMsgInfoCache(String deviceNo);
}
