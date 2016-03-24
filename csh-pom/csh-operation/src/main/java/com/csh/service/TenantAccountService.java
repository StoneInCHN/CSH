package com.csh.service; 

import com.csh.entity.TenantAccount;
import com.csh.framework.service.BaseService;

public interface TenantAccountService extends BaseService<TenantAccount,Long>{
  /**
   * 判断用户名是否存在
   * 
   * @param username
   *            用户名(忽略大小写)
   * @return 用户名是否存在
   */
  boolean usernameExists(String username);
}