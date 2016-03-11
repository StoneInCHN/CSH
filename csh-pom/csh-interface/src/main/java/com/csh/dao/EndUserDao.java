package com.csh.dao;

import com.csh.entity.EndUser;
import com.csh.framework.dao.BaseDao;

public interface EndUserDao extends BaseDao<EndUser, Long> {
  /**
   * 根据用户名查找终端用户
   * 
   * @param username 用户名
   * @return 终端用户， 若不存在则返回null
   */
  EndUser findByUserName(String username);

  /**
   * 根据手机号码查找终端用户
   * 
   * @param mobileNo 手机号
   * @return 终端用户， 若不存在则返回null
   */
  EndUser findByUserMobile(String mobileNo);

  /**
   * 获取终端用户登录token
   * 
   * @return
   */
  String getEndUserToken(Long id);

  /**
   * 创建终端用户登录token
   */
  String createEndUserToken(String token, Long id);

  /**
   * 删除终端用户登录token
   */
  void deleteEndUserToken(Long id);
}
