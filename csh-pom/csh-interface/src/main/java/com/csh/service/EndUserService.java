package com.csh.service;

import com.csh.entity.EndUser;
import com.csh.entity.commonenum.CommonEnum.AppPlatform;
import com.csh.framework.service.BaseService;

public interface EndUserService extends BaseService<EndUser, Long> {
  /**
   * 根据用户名查找终端用户
   * 
   * @param username 用户名
   * @return 终端用户，若不存在则返回null
   */
  EndUser findByUserName(String userName);

  /**
   * 根据手机号码查找终端用户
   * 
   * @param mobileNo 用户名
   * @return 终端用户，若不存在则返回null
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
   * 获取终端用户登录手机平台信息
   * 
   * @return
   */
  AppPlatform getEndUserAppPlatform(Long id);

  /**
   * 创建终端用户登录手机平台信息
   */
  AppPlatform createEndUserAppPlatform(AppPlatform appPlatform, Long id);

  /**
   * 删除终端用户登录token
   */
  void deleteEndUserToken(Long id);

  /**
   * 用户注册
   * 
   * @param userName
   * @param password
   * @return
   */
  EndUser userReg(String userName, String password);

}
