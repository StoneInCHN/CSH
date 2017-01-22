package com.csh.dao;

import java.util.List;

import com.csh.entity.MessageInfo;
import com.csh.framework.dao.BaseDao;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;


public interface MessageInfoDao extends BaseDao<MessageInfo, Long> {

  /**
   * 根据终端用户分页获取消息
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
   */
  void deleteMsgInfoCache(String deviceNo);
}
